package Config;

import java.util.Collection;

public interface Config {

    public int getInteger(String key);

    public boolean getBoolean(String key);

    public String getString(String key);

    public Collection<String> getKeys();

    public void put(String key, String value);

    public void putIfAbsent(String key, String value);
}
