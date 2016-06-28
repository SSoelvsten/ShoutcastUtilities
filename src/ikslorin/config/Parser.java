package ikslorin.Config;

import ikslorin.TXTManager;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yurippe on 6/18/2016.
 */
public class Parser {

    private String configfilepath;
    private static final Pattern SETTING_PATTERN = Pattern.compile("^[ |\\t]*(\\w+)[ |\\t]*:[ |\\t]*([\\S][^\\n]*[\\S])[ |\\t]*$",Pattern.MULTILINE);
    private static final Pattern SETTING_VAL_LENGTH_1 = Pattern.compile("^[ |\\t]*(\\w+)[ |\\t]*:[ |\\t]*([\\S])[ |\\t]*$",Pattern.MULTILINE);
    public Parser(String configfile){
        configfilepath = configfile;
    }

    public Map<String, String> parse(){
        String content = stripComments(TXTManager.readFullFile(configfilepath));
        Map<String, String> outp = new HashMap<>();

        Matcher m = SETTING_PATTERN.matcher(content);
        while(m.find()){
            //System.out.println("\"" + m.group(1) + "\" \"" + m.group(2) + "\"");
            outp.put(m.group(1), m.group(2));
        }

        m = SETTING_VAL_LENGTH_1.matcher(content);
        while(m.find()){
            //System.out.println("\"" + m.group(1) + "\" \"" + m.group(2) + "\"");
            outp.put(m.group(1), m.group(2));
        }
        return outp;
    }
    /**
     * Creates a string, that is without any comments. Here it goes through all lines,
     * and stops appending the text when reaching a # until again hitting the next line.
     * @param input
     * @return
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

}
