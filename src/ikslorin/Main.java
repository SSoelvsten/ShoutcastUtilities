package ikslorin;

import ikslorin.Clock.ClockManager;
import ikslorin.GlobalShortcuts.GlobalShortcuts;
import ikslorin.TeamScoreManager.ScoreManager;
import ikslorin.TeamScoreManager.Team;
import ikslorin.config.Config;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {


    public static void main(String[] args) {
        //Load the config file
        Config conf = Config.getInstance();

        //Create the two teams to manage
        Team tA = new Team(conf.getString("file_A_name"),
                conf.getString("file_A_tag"),
                conf.getString("file_A_score"));

        Team tB = new Team(conf.getString("file_B_name"),
                conf.getString("file_B_tag"),
                conf.getString("file_B_score"));

        setupTeams(tA, tB);

        //Setup score manager
        ScoreManager sm = new ScoreManager(tA, tB, 12, 2);

        //Setup Clockmanager
        ClockManager cm = new ClockManager();

        //Setup global shortcuts
        if(conf.getBoolean("enable_keybindings")) {
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
        Config conf = Config.getInstance();

        tA.setName(TXTManager.readFirstLine(conf.getString("file_A_name")));
        tB.setName(TXTManager.readFirstLine(conf.getString("file_B_name")));

        tA.setTag(TXTManager.readFirstLine(conf.getString("file_A_tag")));
        tB.setTag(TXTManager.readFirstLine(conf.getString("file_A_tag")));

        try{
            tA.setScore(Integer.parseInt(TXTManager.readFirstLine(conf.getString("file_A_score"))));
            tB.setScore(Integer.parseInt(TXTManager.readFirstLine(conf.getString("file_B_score"))));
        } catch(NumberFormatException e) {
            System.err.println("There was a noninteger in the teamScore field");
            tA.setScore(0);
            tB.setScore(0);
        }
    }
}
