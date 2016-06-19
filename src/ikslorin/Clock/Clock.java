package ikslorin.Clock;

import ikslorin.TXTManager;
import ikslorin.config.Config;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimerTask;

/**
 * A program to create countdowns and show the current time.
 */
public class Clock extends TimerTask {
    // The files to read and write
    private String datefile;

    //Field for current time
    private Date date;

    private static final SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");

    public Clock(){
        Config conf = Config.getInstance();
        this.datefile = conf.getString("file_clock");

        this.date = new Date();
    }

    /**
     * Start the TimerTask, repeatedly writing to the file the current time
     */
    public void run(){
        TXTManager.writeFullFile(datefile, ft.format(date));
    }
}
