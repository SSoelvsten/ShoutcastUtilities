package ikslorin.GlobalShortcuts;

import ikslorin.TeamManager.TeamManager;
import ikslorin.Config.Config;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 * A class handling the listening on all global shortcuts, such that you can commit changes,
 * without having to focus on the program
 * This is technically a keylogger, so take it as it is.
 */
public class GlobalShortcuts implements NativeKeyListener {

    //The managers to send the shortcuts to.
    private TeamManager sm;

    //Flags for keys
    private int numberOfModifiers;

    private int modifier1;
    private boolean modifier1B;
    private int modifier2;
    private boolean modifier2B;

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
    public GlobalShortcuts(TeamManager sm){
        super();

        //Connect to the managers
        this.sm = sm;

        //Set keybindings buttons
        Config conf = Config.getInstance();

        numberOfModifiers = conf.getInteger("number_modifiers");

        modifier1 = conf.getInteger("modifier1_key");
        modifier1B = false;
        modifier2 = conf.getInteger("modifier2_key");
        modifier2B = false;

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
        if (modifier1 == e.getKeyCode()) { modifier1B = true; }
        if (modifier2 == e.getKeyCode()) { modifier2B = true; }

        //Check if enough modifiers are currently pressed
        if (numberOfModifiers == 2 && modifier1B && modifier2B
                || numberOfModifiers == 1 && modifier1B
                || numberOfModifiers == 0) {
            executeShortcut(e.getKeyCode());
        }
    }

    /**
     *
     * @param keycode
     */
    private void executeShortcut(int keycode){
        if (incA == keycode) {
            sm.getTeamA().increaseScore(1);
            sm.reloadTeams();
        } else if (decA == keycode) {
            sm.getTeamA().increaseScore(-1);
            sm.reloadTeams();
        } else if (incB == keycode) {
            sm.getTeamB().increaseScore(1);
            sm.reloadTeams();
        } else if (decB == keycode) {
            sm.getTeamB().increaseScore(-1);
            sm.reloadTeams();
        } else if (swap == keycode) {
            sm.swapTeams();
        } else if (update == keycode) {
            sm.updateTeams();
        }
    }

    /**
     * Resets a flag
     * @param e The key pressed, which is handled by the framework
     */
    public void nativeKeyReleased(NativeKeyEvent e){
        if (e.getKeyCode() == NativeKeyEvent.VC_ALT_L) { modifier1B = false; }
        if (e.getKeyCode() == NativeKeyEvent.VC_ALT_L) { modifier2B = false; }
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
