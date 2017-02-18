package Observer;

import Time.Timer;

/**
 * An observer listening to a timer
 */
public interface TimerObserver {

    /**
     * A timer calls these observers every time it ticks.
     * @param timer
     */
    public void notifyTick(Timer timer);
}
