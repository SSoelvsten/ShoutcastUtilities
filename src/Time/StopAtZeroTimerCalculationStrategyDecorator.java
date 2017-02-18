package Time;


public class StopAtZeroTimerCalculationStrategyDecorator implements TimerCalculatorStrategy {

    private final TimerCalculatorStrategy strategy;

    public StopAtZeroTimerCalculationStrategyDecorator(TimerCalculatorStrategy strategy){
        this.strategy = strategy;
    }

    @Override
    public void setNextTime(ModifiableTimer timer) {
        if(timer.getHour() > 0 || timer.getMinute() > 0 || timer.getSecond() > 0){
            strategy.setNextTime(timer);
        }
    }
}
