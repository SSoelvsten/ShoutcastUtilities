package Config;

/**
 * Created by Steffan on 08-02-2017.
 */
public interface Config {

    public int getInteger(String key);

    public boolean getBoolean(String key);

    public String getString(String key);

    public void put(String key, String value);

    public void putIfAbsent(String key, String value);
}
