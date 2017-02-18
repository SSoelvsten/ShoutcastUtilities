package Time;

import Observer.TimerObserver;

public class TimerSpyStub implements ModifiableTimer {

    private int hour = 0;
    private int minute = 0;
    private int second = 0;

    private Thread thread;

    public int setCalls = 0;
    public int ticks = 0;
    private TimerCalculatorStrategy strategy;

    @Override
    public void start() {
        //?
    }

    @Override
    public void stop() {
        thread.interrupt();
    }

    @Override
    public void set(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.setCalls++;
    }

    @Override
    public void setCalculationStrategy(TimerCalculatorStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void setTickrate(int msTickrate) {
        //?
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int getHour() {
        return hour;
    }

    @Override
    public int getMinute() {
        return minute;
    }

    @Override
    public int getSecond() {
        return second;
    }

    @Override
    public int getTickrate() {
        return 0;
    }

    @Override
    public void subscribe(TimerObserver o) {
        //No need to test this part?
    }
}
