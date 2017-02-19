package GlobalShortcuts;

import Config.*;

import GameState.GameStateController;
import GameState.ModifiableGameState;
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
    private GameStateController gameStateController;
    int team0Index;
    int team1Index;

    //Flags for keys
    private int numberOfModifiers;

    private int modifier1;
    private boolean modifier1B;
    private int modifier2;
    private boolean modifier2B;

    private int incA;
    private int decA;
    private int incB;
    private int decB;
    private int swap;
    private int unpause;

    /**
     * Constructor setting up connections to the objects to control with the power of shortcuts
     * @param config Config to read shortcuts from
     * @param gameStateController The GameState to modify.
     */
    public GlobalShortcuts(Config config, GameStateController gameStateController,
                           int team0Index, int team1Index){
        super();

        //Connect to the managers
        this.gameStateController = gameStateController;
        this.team0Index = team0Index;
        this.team1Index = team1Index;

        numberOfModifiers = config.getInteger(ConfigKeys.number_modifiers);

        modifier1 = config.getInteger(ConfigKeys.modifier1_key);
        modifier1B = false;

        modifier2 = config.getInteger(ConfigKeys.modifier2_key);
        modifier2B = false;

        incA = config.getInteger(ConfigKeys.team_0_increment_key);
        decA = config.getInteger(ConfigKeys.team_0_decrement_key);
        incB = config.getInteger(ConfigKeys.team_1_increment_key);
        decB = config.getInteger(ConfigKeys.team_1_decrement_key);
        swap = config.getInteger(ConfigKeys.swap_teams_key);
        unpause = config.getInteger(ConfigKeys.unpause_key);
    }

    /**
     * For every button you press, it checks if it is one of the shortcuts
     * @param e The key pressed, which is handled by the framework
     */
    public void nativeKeyPressed(NativeKeyEvent e){
        //Listen for all the keys we use and put flags
        if (e.getKeyCode() == modifier1) { modifier1B = true; }
        if (e.getKeyCode() == modifier2) { modifier2B = true; }

        //Check if enough modifiers are currently pressed
        if (numberOfModifiers == 2 && modifier1B && modifier2B
                || numberOfModifiers == 1 && modifier1B
                || numberOfModifiers == 0) {
            executeShortcut(e.getKeyCode());
        }
    }

    /**
     * Check if the key pressed corresponds to an action
     * @precondition: Enough modifier keys are pressed
     * @param keycode The key pressed, which is handled by the framework
     */
    private void executeShortcut(int keycode){
        if (keycode == incA) {
            gameStateController.changeTeamScoreBy(team0Index, 1);
        } else if (keycode == decA) {
            gameStateController.changeTeamScoreBy(team0Index, -1);
        } else if (keycode == incB) {
            gameStateController.changeTeamScoreBy(team1Index, 1);
        } else if (keycode == decB) {
            gameStateController.changeTeamScoreBy(team1Index, -1);
        } else if (keycode == swap) {
            gameStateController.shiftTeams();
        } else if (keycode == unpause){
            gameStateController.unpause();
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
