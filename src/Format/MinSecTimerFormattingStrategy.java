package Format;

import Config.Config;
import Time.Timer;

public class MinSecTimerFormattingStrategy implements TimerFormattingStrategy {

    private Config config;

    public MinSecTimerFormattingStrategy(Config config){
        this.config = config;
    }

    @Override
    public String time(Timer timer) {
        String res = "";

        if(timer.getMinute() < 10){
            res += "0";
        }
        res += timer.getMinute() + ":";

        if(timer.getSecond() < 10){
            res += "0";
        }
        res += timer.getSecond();

        return res;
    }
}
