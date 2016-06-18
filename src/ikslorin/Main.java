package ikslorin;

import ikslorin.config.Config;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {


    public static void main(String[] args) {
        Config conf = Config.getInstance(); //This makes sure that the config
        //file is loaded as the first thing the program does

        //Create the two teams to manage
        Team tA = new Team("A");
        Team tB = new Team("B");
        setupTeams(tA, tB);

        //Setup score manager
        ScoreManager sm = new ScoreManager(tA, tB, 12, 2);

        if(conf.getBoolean("enable_keybindings")) {
            //Setup global shortcuts
            Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.WARNING);
            try {
                GlobalScreen.registerNativeHook();
            } catch (NativeHookException e) {
                System.err.println("The global shortcuts could not be created");
            }
            GlobalScreen.addNativeKeyListener(new GlobalShortcuts(sm));
        }
    }

    /**
     * Loads in the values from the last session
     * @param tA Team A
     * @param tB Team B
     */
    private static void setupTeams(Team tA, Team tB){
        tA.setName(TXTManager.readFullFile("A_name.txt"));
        tB.setName(TXTManager.readFullFile("B_name.txt"));

        tA.setTag(TXTManager.readFullFile("A_tag.txt"));
        tB.setTag(TXTManager.readFullFile("B_tag.txt"));

        try{
            tA.setScore(Integer.parseInt(TXTManager.readFullFile("A_score.txt")));
            tB.setScore(Integer.parseInt(TXTManager.readFullFile("B_score.txt")));
        } catch(NumberFormatException e) {
            System.err.println("There was a noninteger in the teamScore field");
            tA.setScore(0);
            tB.setScore(0);
        }
    }
}
