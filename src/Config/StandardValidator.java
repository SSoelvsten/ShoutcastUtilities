package Config;

/**
 * Validates to the standard setup found in StandardConfig
 */
public class StandardValidator implements Validator {

    private Config toCompare;

    public StandardValidator(Config toCompare){
        this.toCompare = toCompare;
    }

    @Override
    public void ValidateConfig(Config toValidate){
        //Check that all values of the Standard config are
        //in the given Config. Put in from Standard if absent
        for(String s : toCompare.getKeys()){
            toValidate.putIfAbsent(s, toCompare.getString(s));
        }

        //Check type of bindings
        checkInteger(toValidate, ConfigKeys.team_amount, toCompare.getInteger(ConfigKeys.team_amount));
        checkInteger(toValidate, ConfigKeys.map_amount, toCompare.getInteger(ConfigKeys.map_amount));

        checkBoolean(toValidate, ConfigKeys.enable_keybindings, toCompare.getBoolean(ConfigKeys.enable_keybindings));

        checkInteger(toValidate, ConfigKeys.number_modifiers, toCompare.getInteger(ConfigKeys.number_modifiers));
        checkInteger(toValidate, ConfigKeys.modifier1_key, toCompare.getInteger(ConfigKeys.modifier1_key));
        checkInteger(toValidate, ConfigKeys.modifier2_key, toCompare.getInteger(ConfigKeys.modifier2_key));

        checkInteger(toValidate, ConfigKeys.team_0_increment_key, toCompare.getInteger(ConfigKeys.team_0_increment_key));
        checkInteger(toValidate, ConfigKeys.team_0_decrement_key, toCompare.getInteger(ConfigKeys.team_1_decrement_key));
        checkInteger(toValidate, ConfigKeys.team_1_increment_key, toCompare.getInteger(ConfigKeys.team_1_increment_key));
        checkInteger(toValidate, ConfigKeys.team_1_decrement_key, toCompare.getInteger(ConfigKeys.team_1_decrement_key));
        checkInteger(toValidate, ConfigKeys.swap_teams_key, toCompare.getInteger(ConfigKeys.swap_teams_key));
        checkInteger(toValidate, ConfigKeys.unpause_key, toCompare.getInteger(ConfigKeys.unpause_key));
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
