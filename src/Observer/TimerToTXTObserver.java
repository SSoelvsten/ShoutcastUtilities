package Observer;

import Config.*;
import Format.TimerFormattingStrategy;
import InputOutput.ReadWriteStrategy;
import Time.Timer;

public class TimerToTXTObserver implements TimerObserver {

    private int id;

    private ReadWriteStrategy readwrite;
    private TimerFormattingStrategy format;
    private Config config;

    public TimerToTXTObserver(int id,
                              Config config,
                              TimerFormattingStrategy format,
                              ReadWriteStrategy readwrite){
        this.id = id;
        this.config = config;
        this.format = format;
        this.readwrite = readwrite;
    }

    @Override
    public void notifyTick(Timer timer) {
        readwrite.write(config.getString(ConfigKeys.folder_txt_dst) + "timer" + id + ".txt", format.time(timer));
    }
}
