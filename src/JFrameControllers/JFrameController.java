package JFrameControllers;

import GameState.ModifiableGameState;

public interface JFrameController {

    /**
     * Add a modifiable GameState to the Interface.
     * @param gameState The gameState to add controlling buttons
     *                  on the JFrame.
     */
    public void addGameState(ModifiableGameState gameState);

    //TODO: Clock will be added later in a addClock
}
