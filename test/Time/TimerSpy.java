package Time;

import Observer.TimerObserver;

public class TimerSpy implements ModifiableTimer {

    private int hour = 0;
    private int minute = 0;
    private int second = 0;

    private Thread thread;
    private Ticker ticker;

    public int set = 0;
    private TimerCalculatorStrategy strategy;

    @Override
    public void start() {
        thread = new Thread(ticker);
        thread.start();
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
        this.set++;
    }

    @Override
    public void setCalculationStrategy(TimerCalculatorStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

    @Override
    public Ticker getTicker() {
        return ticker;
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
    public void subscribe(TimerObserver o) {
        //No need to test this part?
    }

    @Override
    public void onTick() {
        strategy.setNextTime(this);
    }
}
