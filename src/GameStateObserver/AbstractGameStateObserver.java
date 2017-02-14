package GameStateObserver;

import GameState.GameState;

/**
 * A do nothing observer.
 */
public abstract class AbstractGameStateObserver implements GameStateObserver {
    @Override
    public void onShiftUpdate(GameState gameState) {
        //Do nothing
    }

    @Override
    public void onMapUpdate(GameState gameState) {
        //Do nothing
    }

    @Override
    public void onPauseUpdate(GameState gameState) {
        //Do nothing
    }

    @Override
    public void onScoreUpdate(GameState gameState) {
        //Do nothing
    }

    @Override
    public void onNameUpdate(GameState gameState) {
        //Do nothing
    }
}
