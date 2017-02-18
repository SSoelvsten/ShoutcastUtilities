package Time;

import Observer.TickerObserver;

public class TickerStub implements Ticker {

    private TickerObserver observer;

    @Override
    public void run() {
        observer.onTick();
    }

    @Override
    public Ticker clone() {
        return this;
    }

    @Override
    public void subscribe(TickerObserver observer) {
        this.observer = observer;
    }

    @Override
    public void unsubscribe(TickerObserver observer) {
        this.observer = null;
    }
}
