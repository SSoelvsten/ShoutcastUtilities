package ikslorin.Clock;

import ikslorin.TXTManager;
import ikslorin.config.Config;

import java.util.Date;
import java.util.TimerTask;

/**
 * A program to create countdowns and show the current time.
 */
public class Countdown extends TimerTask {
    // The files to read and write
    private String countfile;

    //The manager
    ClockManager cm;

    //Fields set for the countdown
    int hours;
    int minutes;
    int seconds;

    public Countdown(ClockManager cm){
        Config conf = Config.getInstance();
        this.countfile = conf.getString("file_countdown");

        this.cm = cm;

        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }

    public void setCountDown(int hours, int minutes, int seconds){
        this.hours = hours;
        this.minutes = minutes % 60;
        this.seconds = seconds & 60;

        //Check for stupid input and convert correctly
        if(seconds > 59){
            minutes += seconds / 60;
            this.minutes = minutes;
        }
        if(minutes > 59){
            this.hours += minutes / 60;
        }
    }

    /**
     * Start the TimerTask, which is here starting the countdown. Currently it also
     * countdowns on hours, but it is not printed
     */
    public void run(){
        //Finished counting down?
        if(hours == 0 && minutes == 0 && seconds == 0){
            cancel();
        }
        //Count down
        else {
            if(seconds == 0){
                seconds = 59;
                minutes--;
            } else if(minutes == 0){
                minutes = 59;
                hours--;
            } else {
                seconds--;
            }
        }
        //Add in hours here easily...
        cm.reloadCounters();
        TXTManager.writeFullFile(countfile, minutes + ":" + seconds);
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
}
