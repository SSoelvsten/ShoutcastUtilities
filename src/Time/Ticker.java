package Time;

import Observer.TickerObserver;

public interface Ticker extends Runnable {

    public Ticker clone();

    public void subscribe(TickerObserver timer);

    public void unsubscribe(TickerObserver timer);
}
