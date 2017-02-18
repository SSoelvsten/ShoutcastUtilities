package Time;

import Observer.TimerObserver;

public class TickerTimerObserverSpy implements TimerObserver {

    public int notifications = 0;

    @Override
    public void notifyTick(Timer timer) {
        notifications++;
    }
}
