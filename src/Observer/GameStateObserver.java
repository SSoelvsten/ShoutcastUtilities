package Observer;

import GameState.GameState;

/**
 * An object listening on the current state of the game.
 */
public interface GameStateObserver {

    /**
     * I am still not sure, if it is necessary to also parse
     * the game state itself. With parsing it though, you are
     * not restricted to only listening to one game state
     * instance.
     * @param gameState The game state, that has changed and
     *                  wants to nofity its observers.
     */
    public void notify(GameState gameState);
}
