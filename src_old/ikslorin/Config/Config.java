package Config;

import java.util.Map;

/**
 * Created by Yurippe.
 * Containing the global settings as found in config.cfg
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
        //Set absent settings to default
        settings.putIfAbsent("file_A_name", "txt/A_name.txt");
        settings.putIfAbsent("file_A_abbreviation", "txt/A_tag.txt");
        settings.putIfAbsent("file_A_score", "txt/A_score.txt");

        settings.putIfAbsent("file_B_name", "txt/B_name.txt");
        settings.putIfAbsent("file_B_abbreviation", "txt/B_tag.txt");
        settings.putIfAbsent("file_B_score", "txt/B_score.txt");

        settings.putIfAbsent("file_game_number", "txt/game_number.txt");
        settings.putIfAbsent("file_victor", "txt/victor.txt");

        settings.putIfAbsent("file_pause", "txt/pause.txt");

        settings.putIfAbsent("file_countdown", "txt/clock_countdown.txt");
        settings.putIfAbsent("file_time", "txt/clock_time.txt");

        //Check keybindings settings existence
        settings.putIfAbsent("enable_keybindings", "true");

        settings.putIfAbsent("number_modifiers", "1");
        settings.putIfAbsent("modifier1_key", "3640");
        settings.putIfAbsent("modifier2_key", "3613");

        settings.putIfAbsent("commit_key", "82");

        settings.putIfAbsent("team_a_increment_key", "71");
        settings.putIfAbsent("team_a_decrement_key", "75");
        settings.putIfAbsent("team_b_increment_key", "73");
        settings.putIfAbsent("team_b_decrement_key", "77");
        settings.putIfAbsent("swap_teams_key", "72");

        //Check type of bindings
        checkBoolean("enable_keybindings", false);
        checkInteger("number_modifiers", 1);
        checkInteger("modifier1_key", 3640);
        checkInteger("modifier2_key", 3613);
        checkInteger("commit_key", 82);
        checkInteger("team_a_increment_key", 71);
        checkInteger("team_a_decrement_key", 75);
        checkInteger("team_b_increment_key", 73);
        checkInteger("team_b_decrement_key", 77);
        checkInteger("swap_teams_key", 72);
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

    private void checkBoolean(String key, boolean defBool){
        if( !(settings.get(key).equals("true") || settings.get(key).equals("false"))) {
            System.err.println("Incorrect keybinding: " + key + " (" + settings.get(key) +")");
            settings.put("enable_keybindings", "false");
        }
    }

    private void checkInteger(String key, int defInt){
        try{
            Integer.parseInt(settings.get(key));
        } catch(NumberFormatException e){
            System.err.println("Incorrect keybinding: " + key + " (" + settings.get(key) +")");
            settings.put(key, "" + defInt);
        }
    }
}
