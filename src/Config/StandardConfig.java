package Config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A hard coded config.
 */
public class StandardConfig implements Config {

    private Map<String,String> settings = new HashMap<>();

    public StandardConfig(){
        //Setup
        settings.put(ConfigKeys.team_amount, "2");
        settings.put(ConfigKeys.map_amount, "3");

        //Folder
        settings.put(ConfigKeys.folder_map_src, "maps/");
        settings.put(ConfigKeys.folder_map_dst, "txt/");
        settings.put(ConfigKeys.folder_txt_dst, "txt/");

        //Files
        settings.put(ConfigKeys.file_map_unknown, "unknown.png");

        //Strings
        settings.put(ConfigKeys.string_pre_game_number, "Map");

        //Keybindings
        settings.put(ConfigKeys.enable_keybindings, "true");

        settings.put(ConfigKeys.number_modifiers, "1");
        settings.put(ConfigKeys.modifier1_key, "3640");
        settings.put(ConfigKeys.modifier2_key, "3613");

        settings.put(ConfigKeys.commit_key, "82");

        settings.put(ConfigKeys.team_a_increment_key, "71");
        settings.put(ConfigKeys.team_a_increment_key, "75");
        settings.put(ConfigKeys.team_b_increment_key, "73");
        settings.put(ConfigKeys.team_b_decrement_key, "77");
        settings.put(ConfigKeys.swap_teams_key, "72");
    }

    public Collection<String> getKeys(){
        return settings.keySet();
    }

    @Override
    public int getInteger(String key){
        return Integer.parseInt(settings.get(key));
    }

    @Override
    public boolean getBoolean(String key){
        return settings.get(key).equalsIgnoreCase("true");
    }

    @Override
    public String getString(String key){
        return settings.get(key);
    }

    @Override
    public void put(String key, String value) {
        //This is the standard setup, and it should not change
        //settings.put(key, value);
    }

    @Override
    public void putIfAbsent(String key, String value) {
        //This is the standard setup, and it should not change
        //settings.putIfAbsent(key, value);
    }
}
