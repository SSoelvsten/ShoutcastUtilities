package Config;

/**
 * Validates to the standard setup found in StandardConfig
 */
public class StandardValidator implements Validator {

    @Override
    public void ValidateConfig(Config config){
        //Check that all values of the Standard config are
        //in the given Config. Put in from Standard if absent
        StandardConfig sc = new StandardConfig();
        for(String s : sc.getKeys()){
            config.putIfAbsent(s, sc.getString(s));
        }

        //Check type of bindings
        checkBoolean(config, StandardConfig.enable_keybindings, false);
        checkInteger(config, StandardConfig.number_modifiers, 1);
        checkInteger(config, StandardConfig.modifier1_key, 3640);
        checkInteger(config, StandardConfig.modifier2_key, 3613);
        checkInteger(config, StandardConfig.commit_key, 82);
        checkInteger(config, StandardConfig.team_a_increment_key, 71);
        checkInteger(config, StandardConfig.team_a_decrement_key, 75);
        checkInteger(config, StandardConfig.team_b_increment_key, 73);
        checkInteger(config, StandardConfig.team_b_decrement_key, 77);
        checkInteger(config, StandardConfig.swap_teams_key, 72);
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
