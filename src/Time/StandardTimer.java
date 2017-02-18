package Time;

import Observer.TimerObserver;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A standard timer, ticking with normal seconds.
 */
public class StandardTimer implements ModifiableTimer {

    private final ModifiableTimer thisTimer = this;

    private int hour;
    private int minute;
    private int second;

    private ArrayList<TimerObserver> observerList = new ArrayList<>();

    private int tickrate;

    private Timer timer;

    private TimerCalculatorStrategy strategy;

    public StandardTimer(int msTickRate, TimerCalculatorStrategy strategy){
        this.tickrate = msTickRate;
        this.strategy = strategy;

        set(0, 0, 0);
    }

    @Override
    public void start() {
        stop();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run(){
                strategy.setNextTime(thisTimer);
            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, tickrate);
    }

    @Override
    public void stop() {
        if(timer != null){
            timer.cancel();
            timer = null;
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
    public void setTickrate(int msTickrate) {
        this.tickrate = msTickrate;
    }

    @Override
    public boolean isRunning() {
        return timer != null;
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
        return tickrate;
    }

    @Override
    public void subscribe(TimerObserver o) {
        observerList.add(o);
    }
}
