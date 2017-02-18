package Time;

import Observer.TickerObserver;
import Observer.TimerObserver;

public class TickerTimerObserverSpy implements TimerObserver, TickerObserver {

    public int notifications = 0;

    @Override
    public void notifyTick(Timer timer) {
        notifications++;
    }

    @Override
    public void onTick() {
        notifications++;
    }
}
