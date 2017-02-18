package Time;

public interface TimerCalculatorStrategy {

    /**
     * Given a ModifiableTimer it will calculate and
     * set the timer correctly for the next time.
     * @param timer
     */
    public void setNextTime(ModifiableTimer timer);
}
