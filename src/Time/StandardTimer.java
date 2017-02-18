package Time;

import Observer.TimerObserver;

import java.util.ArrayList;

/**
 * A standard timer, ticking with normal seconds.
 */
public class StandardTimer implements ModifiableTimer {

    private int hour;
    private int minute;
    private int second;

    private ArrayList<TimerObserver> observerList = new ArrayList<>();

    private Ticker ticker;

    private Thread thread;
    private TimerCalculatorStrategy strategy;

    public StandardTimer(Ticker ticker, TimerCalculatorStrategy strategy){
        this.ticker = ticker;
        ticker.subscribe(this);

        this.strategy = strategy;

        set(0, 0, 0);
    }

    @Override
    public void start() {
        stop();
        thread = new Thread(ticker);
        thread.start();
    }

    @Override
    public void stop() {
        if(thread != null && thread.isAlive()){
            thread.interrupt();
        }
    }

    @Override
    public void set(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;

        for(TimerObserver o : observerList){
            o.notifyTick(this);
        }
    }

    @Override
    public void setCalculationStrategy(TimerCalculatorStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void setTicker(Ticker ticker) {
        this.ticker.unsubscribe(this);
        this.ticker = ticker;
        ticker.subscribe(this);
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
        observerList.add(o);
    }

    @Override
    public void onTick() {
        this.strategy.setNextTime(this);
    }
}
