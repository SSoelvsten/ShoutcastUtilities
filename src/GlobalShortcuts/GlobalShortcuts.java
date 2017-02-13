package GlobalShortcuts;

import Config.*;

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
    private ModifiableGameState gameState;
    int teamAIndex;
    int teamBIndex;

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
     * @param gameState The GameState to modify.
     */
    public GlobalShortcuts(Config config, ModifiableGameState gameState,
                           int teamAIndex, int teamBIndex){
        super();

        //Connect to the managers
        this.gameState = gameState;
        this.teamAIndex = teamAIndex;
        this.teamBIndex = teamBIndex;

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
            gameState.setTeamPoints(teamAIndex, gameState.getTeam(teamAIndex).getPoints() + 1);
        } else if (keycode == decA) {
            if(gameState.getTeam(teamBIndex).getPoints() > 0)
                gameState.setTeamPoints(teamAIndex, gameState.getTeam(teamAIndex).getPoints() - 1);
        } else if (keycode == incB) {
            gameState.setTeamPoints(teamBIndex, gameState.getTeam(teamBIndex).getPoints() + 1);
        } else if (keycode == decB) {
            if(gameState.getTeam(teamBIndex).getPoints() > 0)
                gameState.setTeamPoints(teamBIndex, gameState.getTeam(teamBIndex).getPoints() - 1);
        } else if (keycode == swap) {
            gameState.shiftTeams();
        } /*else if (update == keycode) {
            //Committing is now not a thing anymore
        }*/
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
