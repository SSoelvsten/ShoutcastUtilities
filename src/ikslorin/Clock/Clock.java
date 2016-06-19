package ikslorin.Clock;

import ikslorin.TXTManager;
import ikslorin.config.Config;

import java.util.Date;
import java.util.TimerTask;

/**
 * A program to create countdowns and show the current time.
 */
public class Clock extends TimerTask {
    // The files to read and write
    private String datefile;

    // Printtype
    public enum Clocktype {DATE, HMS, MS};
    Clocktype clocktype;

    //Field for current time
    Date date;

    public Clock(){
        Config conf = Config.getInstance();
        this.datefile = conf.getString("file_clock");

        this.date = new Date();

        this.clocktype = Clocktype.HMS;
    }

    /**
     * Start the TimerTask, repeatedly writing to the file the current time
     */
    public void run(){
        if(clocktype == Clocktype.HMS){
            printHMS();
        } else if(clocktype == Clocktype.DATE) {
            printDate();
        } else {
            printMS();
        }
    }

    /**
     * Prints out the current time to the datefile.
     */
    public void printDate(){
        TXTManager.writeFullFile(datefile, date.toString());
    }

    /**
     * Prints out the current time to the datefile. This is ONLY hours:minutes:seconds
     */
    public void printHMS(){
        TXTManager.writeFullFile(datefile, date.toString().substring(12, 19));
    }

    /**
     * Prints out the current time to the datefile. This is ONLY minutes:seconds
     */
    public void printMS(){
        TXTManager.writeFullFile(datefile, date.toString().substring(15, 19));
    }
}
