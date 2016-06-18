package ikslorin.config;

import ikslorin.TXTManager;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kristian on 6/18/2016.
 */
public class Parser {

    private String configfilepath;
    private static final Pattern SETTING_PATTERN = Pattern.compile("^[ |\\t]*(\\w+)[ |\\t]*:[ |\\t]*(\\w[\\w| |\\t]*\\w)[ |\\t]*$",Pattern.MULTILINE);

    public Parser(String configfile){
        configfilepath = configfile;
    }

    public Map<String, String> parse(){
        String content = TXTManager.readFullFile(configfilepath);
        Map<String, String> outp = new HashMap<String, String>();

        Matcher m = SETTING_PATTERN.matcher(stripComments(content));
        while(m.find()){
            outp.put(m.group(1), m.group(2));
        }

        return outp;

    }

    private static String stripComments(String input){
        char[] inp = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int c = 0;
        boolean w = false;

        while(i < inp.length){
            if(w){
                if(inp[i] == '\n'){
                    w = false;
                    c = i;
                    continue;
                }
            }

            if(inp[i] == '#'){
                sb.append(input.substring(c,i));
                i++;
                w = true;
                continue;
            }

            i++;
        }

        if(c < i && !w){
            sb.append(input.substring(c,i));
        }

        return sb.toString();
    }

}
