package ikslorin.Clock;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A program to create countdowns and show the current time.
 */
public class Clock {
    //Field for current time
    Date date;

    //Fields put for the countdown
    private int hours;
    private int minutes;
    private int seconds;
    private boolean negative;
    private boolean displayHours = false;

    private Timer timer;

    private ClockEvent event;

    public Clock(){
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
        this.negative = false;
    }

    public void setEventHandler(ClockEvent e){
        event = e;
    }

    public void startCountdown(){
        startCustom(new TimerTask(){
            @Override
            public void run(){
                tickDown();
            }
        });

    }


    public void startClock(){
        displayHours = true;

        startCustom(new TimerTask() {
            @Override
            public void run() {
                Date d = new Date();
                seconds = d.getSeconds();
                minutes = d.getMinutes();
                hours = d.getHours();
                update();
            }
        });
    }


    public void startCustom(TimerTask task){
        stop();
        timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void stop(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

    private void update(){
        if(event != null){
            event.onUpdate(this);
            if(seconds == 0 && minutes == 0 && hours == 0){
                event.onFinish(this);
            }
        }
    }


    public void setTime(int hours, int minutes, int seconds, boolean negative){
        this.negative = negative;
        setTime(hours,minutes,seconds);
    }
    public void setTime(int hours, int minutes, int seconds){
        this.hours = hours;
        this.minutes = minutes % 60;
        this.seconds = seconds % 60;

        //Check for stupid input and convert correctly
        if(seconds > 59){
            minutes += seconds / 60;
            this.minutes = minutes;
        }
        if(minutes > 59){
            this.hours += minutes / 60;
        }
    }

    public void tickDown(){

        if(negative){
            this.seconds += 1;
            if(this.seconds > 59) {
                this.seconds = 0;
                this.minutes += 1;
            }

            if(this.minutes > 59){
                this.minutes = 0;
                this.hours += 1;
            }

        } else {
            if (this.seconds > 0) {
                this.seconds -= 1;
            } else if (this.minutes > 0) {
                this.minutes -= 1;
                this.seconds = 59;
            } else if (this.hours > 0) {
                this.hours -= 1;
                this.minutes = 59;
                this.seconds = 59;
            } else {
                this.seconds = 1;
                this.minutes = 0;
                this.hours = 0;
                this.negative = true;
            }
        }

        update();

    }

    public int getHours(){
        return hours;
    }

    public int getMinutes(){
        return minutes;
    }

    public int getSeconds(){
        return seconds;
    }

    public void setHours(int hours){
        this.hours = hours;
    }

    public void setMinutes(int minutes){
        this.minutes = minutes;
    }

    public void setSeconds(int seconds){
        this.seconds = seconds;
    }

    public void setDisplayHours(boolean b){
        displayHours = b;
    }

    public String toString(){
        return (negative? "-":"") + (displayHours? hours  + ":" : "") + (minutes > 9? ""+minutes:"0" + minutes) + ":" + (seconds > 9? ""+seconds:"0" + seconds);
    }

    /*
        public static void main(String[] a){
        Clock c = new Clock();
        c.setCountDown(0, 2, 3);
        System.out.println("BEGIN");
        System.out.println(c);
        for(int i=0; i<124; i++){
            c.tickDown();
            System.out.println(c);
        }
    }
     */
}