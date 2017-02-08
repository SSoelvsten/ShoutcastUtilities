package Config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A hard coded config. Here you also find all the key/values for a standard setup
 */
public class StandardConfig implements Config {

    private Map<String,String> settings = new HashMap<>();

    public StandardConfig(){
        //Files
        settings.put(file_A_name, "txt/A_name.txt");
        settings.put(file_A_tag, "txt/A_tag.txt");
        settings.put(file_A_score, "txt/A_score.txt");

        settings.put(file_B_name, "txt/B_name.txt");
        settings.put(file_B_tag, "txt/B_tag.txt");
        settings.put(file_B_score, "txt/B_score.txt");

        settings.put(file_game_number, "txt/game_number.txt");
        settings.put(file_victor, "txt/victor.txt");

        settings.put(file_pause, "txt/pause.txt");

        settings.put(file_countdown, "txt/clock_countdown.txt");
        settings.put(file_time, "txt/clock_time.txt");

        //Keybindings
        settings.put(enable_keybindings, "true");

        settings.put(number_modifiers, "1");
        settings.put(modifier1_key, "3640");
        settings.put(modifier2_key, "3613");

        settings.put(commit_key, "82");

        settings.put(team_a_increment_key, "71");
        settings.put(team_a_increment_key, "75");
        settings.put(team_b_increment_key, "73");
        settings.put(team_b_decrement_key, "77");
        settings.put(swap_teams_key, "72");
    }

    public Collection<String> getKeys(){
        return settings.keySet();
    }

    public static final String file_A_name = "file_A_name";
    public static final String file_A_tag = "file_A_tag";
    public static final String file_A_score = "file_A_score";

    public static final String file_B_name = "file_B_name";
    public static final String file_B_tag = "file_B_tag";
    public static final String file_B_score = "file_B_score";

    public static final String file_game_number = "file_game_number";
    public static final String file_victor = "file_victor";

    public static final String file_pause = "file_pause";

    public static final String file_countdown = "file_countdown";
    public static final String file_time = "file_time";

    public static final String enable_keybindings = "enable_keybindings";

    public static final String number_modifiers = "number_modifiers";
    public static final String modifier1_key = "modifier1_key";
    public static final String modifier2_key = "modifier2_key";

    public static final String commit_key = "commit_key";

    public static final String team_a_increment_key = "team_a_increment_key";
    public static final String team_a_decrement_key = "team_a_decrement_key";
    public static final String team_b_increment_key = "team_b_increment_key";
    public static final String team_b_decrement_key = "team_b_decrement_key";
    public static final String swap_teams_key = "swap_teams_key";

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
