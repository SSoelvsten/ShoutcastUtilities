package ikslorin;

import org.jnativehook.*;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.lang.annotation.Native;

/**
 * A class handling the listening on all global shortcuts, such that you can commit changes,
 * without having to focus on the program
 * This is technically a keylogger, so take it as it is.
 */
public class GlobalShortcuts implements NativeKeyListener {

    //The managers to send the shortcuts to.
    private ScoreManager sm;

    //Flags for keys
    private boolean alt;
    private boolean f1;
    private boolean f2;

    /**
     * Constructor setting up connections to the objects to control with the power of shortcuts
     * @param sm
     */
    public GlobalShortcuts(ScoreManager sm){
        super();

        //Connect to the managers
        this.sm = sm;

        //Set initial flags on buttons
        alt = false;
        f1 = false;
        f2 = false;
    }

    /**
     * For every button you press, it checks if it is one of the hardcoded shortcuts
     * @param e The key pressed, which is handled by the framework
     */
    public void nativeKeyPressed(NativeKeyEvent e){
        //Listen for all the keys we use and set flags
        if (e.getKeyCode() == NativeKeyEvent.VC_ALT_L) { alt = true; }
        if (e.getKeyCode() == NativeKeyEvent.VC_F1){ f1 = true; }
        if (e.getKeyCode() == NativeKeyEvent.VC_F2) { f2 = true; }

        //Check if a combination is achieved
        if (alt) {
            if (f1) {
                sm.swapTeams();
            } else if (f2) {
                sm.updateTeams();
            }
        }
    }

    /**
     * Resets a flag
     * @param e The key pressed, which is handled by the framework
     */
    public void nativeKeyReleased(NativeKeyEvent e){
        if (e.getKeyCode() == NativeKeyEvent.VC_ALT_L) { alt = false; }
        if (e.getKeyCode() == NativeKeyEvent.VC_F1){ f1 = false; }
        if (e.getKeyCode() == NativeKeyEvent.VC_F2) { f2 = false; }
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
