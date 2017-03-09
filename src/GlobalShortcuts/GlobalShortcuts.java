package GlobalShortcuts;

import Config.*;

import GameState.GameStateController;
import GameState.ModifiableGameState;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.HashMap;

/**
 * A class handling the listening on all global shortcuts, such that you can commit changes,
 * without having to focus on the program. This is technically a keylogger, so take it as it is.
 *
 * Testing has to be done manually or the test cases would have to also 'type'
 */
public class GlobalShortcuts implements NativeKeyListener {

    //Flags for keys
    private int numberOfModifiers;

    private int modifier1;
    private boolean modifier1B;
    private int modifier2;
    private boolean modifier2B;

    //Commands assigned
    private HashMap<Integer, Command> commands = new HashMap<>();

    /**
     * Constructor setting up connections to the objects to control with the power of shortcuts
     * @param config Config to read shortcuts from
     */
    public GlobalShortcuts(Config config){
        super();

        numberOfModifiers = config.getInteger(ConfigKeys.number_modifiers);

        modifier1 = config.getInteger(ConfigKeys.modifier1_key);
        modifier1B = false;

        modifier2 = config.getInteger(ConfigKeys.modifier2_key);
        modifier2B = false;
    }

    public void addCommand(int key, Command command){
        commands.put(key, command);
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
        if ((numberOfModifiers == 2 && modifier1B && modifier2B)
                || (numberOfModifiers == 1 && (modifier1B || modifier2B))
                || (numberOfModifiers == 0)) {

            //Execute the command, if anything is assigned to that key
            if(commands.containsKey(e.getKeyCode()))
                commands.get(e.getKeyCode()).execute();
        }
    }

    /**
     * Resets a flag
     * @param e The key pressed, which is handled by the framework
     */
    public void nativeKeyReleased(NativeKeyEvent e){
        if (e.getKeyCode() == modifier1) { modifier1B = false; }
        if (e.getKeyCode() == modifier2) { modifier2B = false; }
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
