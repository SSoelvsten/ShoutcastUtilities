package Format;

import Config.*;
import Time.TimerSpyStub;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;

public class TestMinSecTimerFormattingStrategy {

    private TimerSpyStub timerSpy;
    private Config config;
    private TimerFormattingStrategy formattingStrat;

    @Before
    public void setup(){
        this.config = new StandardConfig();
        this.timerSpy = new TimerSpyStub();
        this.formattingStrat = new MinSecTimerFormattingStrategy(config);
    }

    @Test
    public void shouldNotShowHour(){
        timerSpy.set(12,23,34);
        assertThat(formattingStrat.time(timerSpy), is("23:34"));
    }

    @Test
    public void shouldAddExtraZeroOn0Seconds(){
        timerSpy.set(8,11,0);
        assertThat(formattingStrat.time(timerSpy), is("11:00"));
    }

    @Test
    public void shouldAddExtraZeroOn9Seconds(){
        timerSpy.set(4,54,9);
        assertThat(formattingStrat.time(timerSpy), is("54:09"));
    }

    @Test
    public void shouldAddExtraZeroOn0Minutes(){
        timerSpy.set(0,0,15);
        assertThat(formattingStrat.time(timerSpy), is("00:15"));
    }

    @Test
    public void shouldAddExtraZeroOn9Minutes(){
        timerSpy.set(0,9,45);
        assertThat(formattingStrat.time(timerSpy), is("09:45"));
    }

}
