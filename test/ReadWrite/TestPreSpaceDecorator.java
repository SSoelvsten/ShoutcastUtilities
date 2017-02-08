package ReadWrite;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestPreSpaceDecorator {

    private ReadWriteStrategy testSpy;
    private ReadWriteStrategy uut;

    @Before
    public void setup(){
        this.testSpy = new ReadWriteStrategyStub();
        this.uut = new PreSpaceReadWriteStrategyDecorator(testSpy);
    }

    @Test
    public void ShouldAddSpaceOnOneLiner(){
        String in = "TestContent";
        uut.write("", in);
        assertThat(testSpy.read(""), is(" " + in));
    }

    @Test
    public void ShouldAddSpaceOnEveryLine(){
        String in = "TestContent" + "\r\n" + "AnotherLine";
        String expected = " " + "TestContent" + "\r\n" + " " + "AnotherLine";
        uut.write("", in);
        assertThat(testSpy.read(""), is(expected));
    }

    @Test
    public void ShouldRemoveSpaceFromFirstLine(){
        String in = " " + "TestContent";
        testSpy.write("", in);

        String expected = "TestContent";
        assertThat(uut.read(""), is(expected));
    }

    @Test
    public void ShouldRemoveSpaceFromEveryLine(){
        String in = " " + "TestContent" + "\r\n" + " " + "AnotherLine";
        testSpy.write("", in);
        String expected = "TestContent" + "\r\n" + "AnotherLine";
        assertThat(uut.read(""), is(expected));
    }
}
