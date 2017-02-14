package Config;


import java.util.Collection;
import java.util.HashMap;

public class ConfigStub implements Config{

    private HashMap<String, String> settings;

    public ConfigStub(){
        settings = new HashMap<>();
        put(ConfigKeys.enable_keybindings, "tomato");   //Should NOT stay after verification (not bool)
        put(ConfigKeys.folder_map_src, "tomato/");       //Should stay after verification
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
    public Collection<String> getKeys() {
        return settings.keySet();
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
