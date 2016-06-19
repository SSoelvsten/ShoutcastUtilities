package ikslorin;

import java.util.Date;

/**
 * A program to create countdowns and show the current time.
 */
public class Clock {
    //Field for current time
    Date date;

    //Fields set for the countdown
    int hours;
    int minutes;
    int seconds;

    public Clock(){
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

    public int getHours(){
        return hours;
    }

    public int getMinutes(){
        return minutes;
    }

    public int getSeconds(){
        return seconds
    }
}
