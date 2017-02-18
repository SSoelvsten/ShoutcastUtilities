package Time;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TestStandardTimer {

    private ModifiableTimer timer;


    private TickerTimerObserverSpy observer;
    private TimerCalculationStratSpy strategySpy;

    @Before
    public void setup(){
        this.strategySpy = new TimerCalculationStratSpy();

        this.timer = new StandardTimer(1000, strategySpy);

        this.observer = new TickerTimerObserverSpy();
        timer.subscribe(observer);

        timer.start();
    }


    @Test
    public void shouldNotifyObserverOnSetTime(){
        assertThat(observer.notifications, is(0));
        timer.set(1,2,3);
        assertThat(observer.notifications, is(1));
    }

    @Test
    public void shouldTickAfter1000AndCallStrategy(){
        await().atLeast(1200, TimeUnit.MILLISECONDS).atMost(1300, TimeUnit.MILLISECONDS);
        assertThat(strategySpy.calls, is(1));
    }

    @Test
    public void shouldTickMoreThanOnce(){
        await().atLeast(2200, TimeUnit.MILLISECONDS).atMost(2300, TimeUnit.MILLISECONDS);
        assertThat(strategySpy.calls, is(2));
    }

    @After
    public void stop(){
        timer.stop();
    }
}
