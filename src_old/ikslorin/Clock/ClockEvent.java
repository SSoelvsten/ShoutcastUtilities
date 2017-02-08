package ikslorin.Clock;

/**
 * Created by Kristian on 6/20/2016.
 */
public abstract class ClockEvent {


    public abstract void onUpdate(Clock clock);

    public void onFinish(Clock clock){
        clock.stop();
    }

}
