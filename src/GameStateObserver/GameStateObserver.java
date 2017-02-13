package GameStateObserver;

import GameState.GameState;

/**
 * An object listening on the current state of games.
 *
 * I am not sure, as to how well this would work when
 * listening to more than one game state at the same time.
 */
public interface GameStateObserver {

    /**
     * A complete update of the gamestate, covering all the cases.
     * When calling this method, it should be regarded as a clean sweep.
     * @param gameState The game state, that has changed and
     *                  wants to nofity its observers.
     */
    public void onShiftUpdate(GameState gameState);

    /**
     * Is called, when a team changes name and/or abbreviation
     * @param gameState The game state, that has changed and
     *                  wants to nofity its observers.
     */
    public void onNameUpdate(GameState gameState);

    /**
     * Is called, when the gameState has updated the score
     * @param gameState The game state, that has changed and
     *                  wants to nofity its observers.
     */
    public void onScoreUpdate(GameState gameState);

    /**
     * Is called, when a gameState is paused or unpaused
     * @param gameState The game state, that has (un)paused and
     *                  wants to nofity its observers.
     */
    public void onPauseUpdate(GameState gameState);

    /**
     * Is called, when a gameState changes the maps chosen to
     * be played on.
     * @param gameState The game state, that has its maps changed
     */
    public void onMapUpdate(GameState gameState);
}
