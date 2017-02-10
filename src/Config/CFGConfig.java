package Config;

import ReadWrite.ReadWriteStrategy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kristian 'Yurippe' Gausel. Modified for compositional
 * design by Steffan 'Ikslorin' Sølvsten.
 * Based on regular expression finds all the settings in config.cfg,
 * and then contains the global settings as found in config.cfg.
 */

public class CFGConfig implements Config {

    private Map<String,String> settings = new HashMap<String,String>();
    private String configfile = "config.cfg";

    private static final Pattern SETTING_PATTERN = Pattern.compile("^[ |\\t]*(\\w+)[ |\\t]*:[ |\\t]*([\\S][^\\n]*[\\S])[ |\\t]*$",Pattern.MULTILINE);
    private static final Pattern SETTING_VAL_LENGTH_1 = Pattern.compile("^[ |\\t]*(\\w+)[ |\\t]*:[ |\\t]*([\\S])[ |\\t]*$",Pattern.MULTILINE);

    public CFGConfig(ReadWriteStrategy rws) {
        String content = stripComments(rws.read(configfile));

        //Regular Expression magic... Ask Yurippe for more
        Matcher m = SETTING_PATTERN.matcher(content);
        while(m.find()){
            settings.put(m.group(1), m.group(2));
        }

        m = SETTING_VAL_LENGTH_1.matcher(content);
        while(m.find()){
            settings.put(m.group(1), m.group(2));
        }
    }

    /**
     * Creates a string, that is without any comments. Here it goes through all lines,
     * and stops appending the text when reaching a # until again hitting the next line.
     * @param input A piece of text
     * @return The input without any comments (starting with #)
     */
    private static String stripComments(String input){
        char[] inp = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int c = 0;
        boolean w = false;

        while(i < inp.length){
            //Currently reading through a comment
            if(w){
                //Just hit the end or still reading?
                if(inp[i] == '\n'){
                    w = false;
                    c = i;
                } else {
                    i++;
                }
            }
            //Have we just begun reading a comment?
            else if(inp[i] == '#'){
                sb.append(input.substring(c,i));
                i++;
                w = true;
            }
            //We are just merrily reading
            else {
                i++;
            }

        }
        //Strings only got appended, when a comment is found, but possibly it ends with no comment
        if(c < i && !w){
            sb.append(input.substring(c,i));
        }
        return sb.toString();
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
    public Collection<String> getKeys() {
        return settings.keySet();
    }

    @Override
    public void put(String key, String value) {
        settings.put(key, value);
    }

    @Override
    public void putIfAbsent(String key, String value) {
        settings.putIfAbsent(key, value);
    }
}
