package Time;

import Observer.TimerObserver;

public interface Timer {

    public boolean isRunning();

    public int getHour();
    public int getMinute();
    public int getSecond();

    public int getTickrate();

    /**
     * Observers can subscribe to the timer here. They will get a notification
     * every time the h:m:s is changed
     * @param o The observer subscribing
     */
    public void subscribe(TimerObserver o);
}