package Time;

import Observer.TickerObserver;
import Observer.TimerObserver;

public interface Timer extends TickerObserver {

    /**
     * Mainly for testing purposes we expose this
     * @return The current state
     */
    public Ticker getTicker();

    public int getHour();
    public int getMinute();
    public int getSecond();

    /**
     * Observers can subscribe to the timer here. They will get a notification
     * every time the h:m:s is changed
     * @param o The observer subscribing
     */
    public void subscribe(TimerObserver o);
}