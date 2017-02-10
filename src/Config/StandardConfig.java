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
        //Files
        settings.put(ConfigKeys.file_A_name, "txt/A_name.txt");
        settings.put(ConfigKeys.file_A_tag, "txt/A_tag.txt");
        settings.put(ConfigKeys.file_A_score, "txt/A_score.txt");

        settings.put(ConfigKeys.file_B_name, "txt/B_name.txt");
        settings.put(ConfigKeys.file_B_tag, "txt/B_tag.txt");
        settings.put(ConfigKeys.file_B_score, "txt/B_score.txt");

        settings.put(ConfigKeys.file_game_number, "txt/game_number.txt");
        settings.put(ConfigKeys.file_victor, "txt/victor.txt");

        settings.put(ConfigKeys.file_pause, "txt/pause.txt");

        settings.put(ConfigKeys.file_countdown, "txt/clock_countdown.txt");
        settings.put(ConfigKeys.file_time, "txt/clock_time.txt");

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
