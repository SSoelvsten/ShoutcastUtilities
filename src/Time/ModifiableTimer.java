package Time;

/**
 * A modifiable timer, able to be set, started and stopped.
 */
public interface ModifiableTimer extends Timer {

    /**
     * Start ticking
     */
    public void start();

    /**
     * Stop the ticking
     */
    public void stop();

    /**
     * Set the time of the
     * @precondition: hour in [0;23], minute in [0;59], second in [0;59]
     * @postcondition: Observers will be notified when this is called
     * @param hour The hours
     * @param minute The minutes
     * @param second The seconds
     */
    public void set(int hour, int minute, int second);

    /**
     * Sets the calculating strategy.
     */
    public void setCalculationStrategy(TimerCalculatorStrategy strategy);

    /**
     * Sets the tick rate
     */
    public void setTickrate(int msTickrate);
}
