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
        checkInteger(toValidate, ConfigKeys.team_amount, toCompare);
        checkInteger(toValidate, ConfigKeys.map_amount, toCompare);

        checkInteger(toValidate, ConfigKeys.timer_tickrate, toCompare);

        checkBoolean(toValidate, ConfigKeys.enable_keybindings, toCompare);

        checkInteger(toValidate, ConfigKeys.number_modifiers, toCompare);
        checkInteger(toValidate, ConfigKeys.modifier1_key, toCompare);
        checkInteger(toValidate, ConfigKeys.modifier2_key, toCompare);

        checkInteger(toValidate, ConfigKeys.team_0_increment_key, toCompare);
        checkInteger(toValidate, ConfigKeys.team_0_decrement_key, toCompare);
        checkInteger(toValidate, ConfigKeys.team_1_increment_key, toCompare);
        checkInteger(toValidate, ConfigKeys.team_1_decrement_key, toCompare);
        checkInteger(toValidate, ConfigKeys.swap_teams_key, toCompare);
        checkInteger(toValidate, ConfigKeys.unpause_key, toCompare);
    }

    private void checkBoolean(Config config, String key, Config defaultConf){
        if( !(config.getString(key).equals("true") || config.getString(key).equals("false")) ) {
            //System.err.println("Not bool keybinding: " + key + " was " + config.getString(key) + ", set to: " + defBool);
            config.put(key, defaultConf.getString(key));
        }
    }

    private void checkInteger(Config config, String key, Config defaultConf){
        try{
            Integer.parseInt(config.getString(key));
        } catch(NumberFormatException e){
            //System.err.println("Not int keybinding: " + key + " was " + config.getString(key) + ", set to: " + defInt);
            config.put(key, defaultConf.getString(key));
        }
    }
}
