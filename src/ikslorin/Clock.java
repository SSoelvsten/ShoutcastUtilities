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
    boolean negative;

    public Clock(){
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
        this.negative = false;
    }
    public void setCountDown(int hours, int minutes, int seconds, boolean negative){
        this.negative = negative;
        setCountDown(hours,minutes,seconds);
    }
    public void setCountDown(int hours, int minutes, int seconds){
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

    public String toString(){
        return (negative? "-":"") + hours + ":" + minutes + ":" + seconds;
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
