package Time;

import Observer.TickerObserver;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Ticks an observer every second
 */
public class SecondTicker implements Ticker {

    ArrayList<TickerObserver> observers = new ArrayList<>();

    public SecondTicker(){
        //Nothing to do?
    }

    private SecondTicker(Collection<TickerObserver> observers){
        observers.addAll(observers);
    }

    @Override
    public Ticker clone() {
        return new SecondTicker(observers);
    }

    @Override
    public void subscribe(TickerObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(TickerObserver timer) {
        observers.remove(timer);
    }

    @Override
    public void run() {
        for(TickerObserver o : observers){
            o.onTick();
        }

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}