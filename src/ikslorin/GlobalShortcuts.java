package ikslorin;

import ikslorin.config.Config;
import javafx.scene.input.KeyCode;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 * A class handling the listening on all global shortcuts, such that you can commit changes,
 * without having to focus on the program
 * This is technically a keylogger, so take it as it is.
 */
public class GlobalShortcuts implements NativeKeyListener {

    //The managers to send the shortcuts to.
    private ScoreManager sm;

    //Flags for keys
    private int modifier;   //Global modifier, that is necessary for the rest to run
    private boolean modifierB;

    private int update;

    private int incA;
    private int decA;
    private int incB;
    private int decB;
    private int swap;

    /**
     * Constructor setting up connections to the objects to control with the power of shortcuts
     * @param sm
     */
    public GlobalShortcuts(ScoreManager sm){
        super();

        //Connect to the managers
        this.sm = sm;

        //Set keybindings buttons

        Config conf = Config.getInstance();

        modifier = conf.getInteger("modifier_key");
        modifierB = false;

        update = conf.getInteger("commit_key");
        incA = conf.getInteger("team_a_increment_key");
        decA = conf.getInteger("team_a_decrement_key");
        incB = conf.getInteger("team_b_increment_key");
        decB = conf.getInteger("team_b_decrement_key");
        swap = conf.getInteger("switch_teams_key");
    }

    /**
     * For every button you press, it checks if it is one of the hardcoded shortcuts
     * @param e The key pressed, which is handled by the framework
     */
    public void nativeKeyPressed(NativeKeyEvent e){
        //Listen for all the keys we use and set flags
        if (modifier == e.getKeyCode()) { modifierB = true; }

        //Check if a combination is achieved
        if (modifierB) {
            if (incA == e.getKeyCode()) {
                sm.getTeamA().increaseScore(1);
                sm.reloadTeams();
            } else if (decA == e.getKeyCode()) {
                sm.getTeamA().increaseScore(-1);
                sm.reloadTeams();
            } else if (incB == e.getKeyCode()) {
                sm.getTeamB().increaseScore(1);
                sm.reloadTeams();
            } else if (decB == e.getKeyCode()) {
                sm.getTeamB().increaseScore(-1);
                sm.reloadTeams();
            } else if (swap == e.getKeyCode()) {
                sm.swapTeams();
            } else if (update == e.getKeyCode()) {
                sm.updateTeams();
            }
        }
    }

    /**
     * Resets a flag
     * @param e The key pressed, which is handled by the framework
     */
    public void nativeKeyReleased(NativeKeyEvent e){
        if (e.getKeyCode() == NativeKeyEvent.VC_ALT_L) { modifierB = false; }
    }

    /**
     * This has to be implemented, but I think it is not necessary. This is
     * for singular keys only pressed and released?
     * @param e The key pressed, which is handled by the framework
     */
    public void nativeKeyTyped(NativeKeyEvent e){
        //What is this one supposed to do?
    }
}
