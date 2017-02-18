package JFrameControllers;

import Observer.GameStateObserver;

import javax.swing.*;

/**
 * An object to set up and manage a panel for either a specific
 * team or map in a GameState
 */
public interface JPanelTeamMapController extends GameStateObserver {

    /**
     * The object will take the information from the panel
     * and set it into the GameState
     */
    public void commitInfo();

    /**
     * Returns the visual representation (JPanel) of the
     * control of the specific part of the GameState
     * @return The panel to place in the JFrame
     */
    public JPanel getPanel();

    /**
     * Lock the text fields in the current panel,
     * thereby disregarding the information from
     * the Observer
     */
    public void listenToGameState(boolean value);
}
