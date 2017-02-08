package Config;

/**
 * Validates to the standard setup
 */
public class ValidatorImpl implements Validator {

    @Override
    public void ValidateConfig(Config config){
        //Set absent settings to default
        config.putIfAbsent("file_A_name", "txt/A_name.txt");
        config.putIfAbsent("file_A_tag", "txt/A_tag.txt");
        config.putIfAbsent("file_A_score", "txt/A_score.txt");

        config.putIfAbsent("file_B_name", "txt/B_name.txt");
        config.putIfAbsent("file_B_tag", "txt/B_tag.txt");
        config.putIfAbsent("file_B_score", "txt/B_score.txt");

        config.putIfAbsent("file_game_number", "txt/game_number.txt");
        config.putIfAbsent("file_victor", "txt/victor.txt");

        config.putIfAbsent("file_pause", "txt/pause.txt");

        config.putIfAbsent("file_countdown", "txt/clock_countdown.txt");
        config.putIfAbsent("file_time", "txt/clock_time.txt");

        //Check keybindings settings existence
        config.putIfAbsent("enable_keybindings", "true");

        config.putIfAbsent("number_modifiers", "1");
        config.putIfAbsent("modifier1_key", "3640");
        config.putIfAbsent("modifier2_key", "3613");

        config.putIfAbsent("commit_key", "82");

        config.putIfAbsent("team_a_increment_key", "71");
        config.putIfAbsent("team_a_decrement_key", "75");
        config.putIfAbsent("team_b_increment_key", "73");
        config.putIfAbsent("team_b_decrement_key", "77");
        config.putIfAbsent("swap_teams_key", "72");

        //Check type of bindings
        checkBoolean(config, "enable_keybindings", false);
        checkInteger(config, "number_modifiers", 1);
        checkInteger(config, "modifier1_key", 3640);
        checkInteger(config, "modifier2_key", 3613);
        checkInteger(config, "commit_key", 82);
        checkInteger(config, "team_a_increment_key", 71);
        checkInteger(config, "team_a_decrement_key", 75);
        checkInteger(config, "team_b_increment_key", 73);
        checkInteger(config, "team_b_decrement_key", 77);
        checkInteger(config, "swap_teams_key", 72);
    }

    private void checkBoolean(Config config, String key, boolean defBool){
        if( !(config.getString(key).equals("true") || config.getString(key).equals("false"))) {
            System.err.println("Incorrect keybinding: " + key + " (" + config.getString(key) +")");
            config.put(key, "" + defBool);
        }
    }

    private void checkInteger(Config config, String key, int defInt){
        try{
            Integer.parseInt(config.getString(key));
        } catch(NumberFormatException e){
            System.err.println("Incorrect keybinding: " + key + " (" + config.getString(key) +")");
            config.put(key, "" + defInt);
        }
    }
}
