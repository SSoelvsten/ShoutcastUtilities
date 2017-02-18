package Time;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TestStandardTimer {

    private ModifiableTimer timer;

    private TickerStub tickerStub;

    private TickerTimerObserverSpy observer;
    private TimerCalculationStratSpy strategySpy;

    @Before
    public void setup(){
        this.tickerStub = new TickerStub();
        this.strategySpy = new TimerCalculationStratSpy();

        this.timer = new StandardTimer(tickerStub, strategySpy);

        this.observer = new TickerTimerObserverSpy();
        timer.subscribe(observer);
    }

    @Test
    public void onTickShouldCallCalculationStrategy(){
        assertThat(strategySpy.calls, is(0));
        timer.onTick();
        assertThat(strategySpy.calls, is(1));
        assertThat(strategySpy.timer, is(timer));
    }

    @Test
    public void shouldNotifyObserverOnSetTime(){
        assertThat(observer.notifications, is(0));
        timer.set(1,2,3);
        assertThat(observer.notifications, is(1));
    }
}
