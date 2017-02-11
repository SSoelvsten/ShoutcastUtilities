package GlobalShortcuts;

import Config.*;

import Controller.GameStateController;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 * A class handling the listening on all global shortcuts, such that you can commit changes,
 * without having to focus on the program. This is technically a keylogger, so take it as it is.
 *
 * Testing has to be done manually or the test cases would have to also 'type'
 */
public class GlobalShortcuts implements NativeKeyListener {

    //The managers to send the shortcuts to.
    private GameStateController controller;

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
     * @param config Config to read shortcuts from
     * @param controller The GameStateController to send the information to.
     */
    public GlobalShortcuts(Config config, GameStateController controller){
        super();

        //Connect to the managers
        this.controller = controller;

        numberOfModifiers = config.getInteger(ConfigKeys.number_modifiers);

        modifier1 = config.getInteger(ConfigKeys.modifier1_key);
        modifier1B = false;

        modifier2 = config.getInteger(ConfigKeys.modifier2_key);
        modifier2B = false;

        update = config.getInteger(ConfigKeys.commit_key);
        incA = config.getInteger(ConfigKeys.team_a_increment_key);
        decA = config.getInteger(ConfigKeys.team_a_decrement_key);
        incB = config.getInteger(ConfigKeys.team_b_increment_key);
        decB = config.getInteger(ConfigKeys.team_b_decrement_key);
        swap = config.getInteger(ConfigKeys.swap_teams_key);
    }

    /**
     * For every button you press, it checks if it is one of the shortcuts
     * @param e The key pressed, which is handled by the framework
     */
    public void nativeKeyPressed(NativeKeyEvent e){
        //Listen for all the keys we use and put flags
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
            controller.incrementTeamAScore();
        } else if (decA == keycode) {
            controller.decrementTeamAScore();
        } else if (incB == keycode) {
            controller.incrementTeamAScore();
        } else if (decB == keycode) {
            controller.decrementTeamBScore();
        } else if (swap == keycode) {
            controller.swapTeams();
        } else if (update == keycode) {
            controller.printFiles();
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
