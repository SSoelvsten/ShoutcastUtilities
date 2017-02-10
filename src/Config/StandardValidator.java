package Config;

/**
 * Validates to the standard setup found in StandardConfig
 */
public class StandardValidator implements Validator {

    @Override
    public void ValidateConfig(Config toValidate){
        //Check that all values of the Standard config are
        //in the given Config. Put in from Standard if absent
        StandardConfig sc = new StandardConfig();
        for(String s : sc.getKeys()){
            toValidate.putIfAbsent(s, sc.getString(s));
        }

        //Check type of bindings
        checkBoolean(toValidate, sc.enable_keybindings, sc.getBoolean(sc.enable_keybindings));
        checkInteger(toValidate, sc.number_modifiers, sc.getInteger(sc.number_modifiers));
        checkInteger(toValidate, sc.modifier1_key, sc.getInteger(sc.modifier1_key));
        checkInteger(toValidate, sc.modifier2_key, sc.getInteger(sc.modifier2_key));
        checkInteger(toValidate, sc.commit_key, sc.getInteger(sc.commit_key));
        checkInteger(toValidate, sc.team_a_increment_key, sc.getInteger(sc.team_a_increment_key));
        checkInteger(toValidate, sc.team_a_decrement_key, sc.getInteger(sc.team_b_decrement_key));
        checkInteger(toValidate, sc.team_b_increment_key, sc.getInteger(sc.team_b_increment_key));
        checkInteger(toValidate, sc.team_b_decrement_key, sc.getInteger(sc.team_b_decrement_key));
        checkInteger(toValidate, sc.swap_teams_key, sc.getInteger(sc.swap_teams_key));
    }

    private void checkBoolean(Config config, String key, boolean defBool){
        if( !(config.getString(key).equals("true") || config.getString(key).equals("false")) ) {
            //System.err.println("Not bool keybinding: " + key + " was " + config.getString(key) + ", set to: " + defBool);
            config.put(key, "" + defBool);
        }
    }

    private void checkInteger(Config config, String key, int defInt){
        try{
            Integer.parseInt(config.getString(key));
        } catch(NumberFormatException e){
            //System.err.println("Not int keybinding: " + key + " was " + config.getString(key) + ", set to: " + defInt);
            config.put(key, "" + defInt);
        }
    }
}
