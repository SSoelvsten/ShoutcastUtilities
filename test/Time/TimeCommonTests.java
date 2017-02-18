package Time;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Help methods for the various tests in the test package
 */
public class TimeCommonTests {

    public static void assertTime(Timer timer, int hour, int minute, int second){
        assertThat(timer.getSecond(), is(second));
        assertThat(timer.getMinute(), is(minute));
        assertThat(timer.getHour(), is(hour));
    }

    //TODO: How do you make automatic unit testing for threaded systems?

    public static void testSingleTick(ModifiableTimer timer,
                                int rHour, int rMinute, int rSecond){
        timer.start();
        await().atLeast(1200, TimeUnit.MILLISECONDS)
                .atMost(1400, TimeUnit.MILLISECONDS);
        timer.stop();
        assertTime(timer, rHour, rMinute, rSecond);
    }
}
