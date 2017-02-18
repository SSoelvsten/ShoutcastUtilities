package Time;

public class TimerCalculationStratSpy implements TimerCalculatorStrategy {

    public int calls;
    public ModifiableTimer timer;

    @Override
    public void setNextTime(ModifiableTimer timer) {
        this.calls++;
        this.timer = timer;
    }
}
