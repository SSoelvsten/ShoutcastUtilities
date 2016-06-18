package ikslorin.config;

import java.util.Map;

/**
 * Created by Kristian on 6/18/2016.
 */

public class Config {

    private static Config config = null;

    private Map<String,String> settings;

    protected Config(){
        reparse();
    }

    public static Config getInstance(){
        if(config == null){
            config = new Config();
        }
        return config;
    }

    public void reparse() {
        Parser parser = new Parser("config.cfg");
        settings = parser.parse();
        validate();
    }

    private void validate(){
        //TODO validate that the settings are a-ok
        //like ensuring that all settings that require an integer actually is an integer
        //Also, if a setting is not set, maybe provide a default.
    }

    public int getInteger(String key){
        return Integer.parseInt(settings.get(key));
    }

    public boolean getBoolean(String key){
        return settings.get(key).equalsIgnoreCase("true");
    }

    public String getString(String key){
        return settings.get(key);
    }

}
