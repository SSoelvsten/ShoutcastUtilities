package Config;


import junit.framework.TestFailure;

import java.util.HashMap;

public class ConfigValidationSpy implements Config{

    private HashMap<String, String> settings;

    public ConfigValidationSpy(){
        settings = new HashMap<>();
        put(StandardConfig.enable_keybindings, "tomato");   //Should NOT stay after verification (not bool)
        put(StandardConfig.file_B_tag, "tomato");           //Should stay after verification
    }

    @Override
    public int getInteger(String key) {
        return Integer.parseInt(settings.get(key));
    }

    @Override
    public boolean getBoolean(String key) {
        return settings.get(key).equalsIgnoreCase("true");
    }

    @Override
    public String getString(String key) {
        return settings.get(key);
    }

    @Override
    public void put(String key, String value) {
        settings.put(key, value);
    }

    @Override
    public void putIfAbsent(String key, String value) {
        settings.putIfAbsent(key, value);
    }
}
