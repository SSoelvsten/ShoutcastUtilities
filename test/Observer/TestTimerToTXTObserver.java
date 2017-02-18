package Observer;

import Config.*;
import Format.MinSecTimerFormattingStrategy;
import Format.TimerFormattingStrategy;
import InputOutput.ReadWriteStrategySpy2;
import Time.TimerSpyStub;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestTimerToTXTObserver {

    private Config config;
    private TimerFormattingStrategy formattingStrategy;
    private ReadWriteStrategySpy2 readWriteSpy;

    private TimerSpyStub timerSpyStub;
    private TimerObserver observer;

    @Before
    public void setup(){
        this.timerSpyStub = new TimerSpyStub();

        this.config = new StandardConfig();
        this.formattingStrategy = new MinSecTimerFormattingStrategy(config);
        this.readWriteSpy = new ReadWriteStrategySpy2();

        this.observer = new TimerToTXTObserver(1, config, formattingStrategy, readWriteSpy);
    }

    @Test
    public void shouldCreateTimerFileWithFormattedString(){
        timerSpyStub.set(1,13,15);
        observer.notifyTick(timerSpyStub);

        String file = "txt/timer1.txt";
        assertThat(readWriteSpy.read(file),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(file),
                is(formattingStrategy.time(timerSpyStub)));
    }
}
