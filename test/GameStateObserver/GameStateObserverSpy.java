package GameStateObserver;

import GameState.GameState;

public class GameStateObserverSpy implements GameStateObserver {

    public int onUpdateCalls = 0;
    public int onPauseUpdateCalls = 0;
    private int onMapUpdateCalls = 0;
    public int onScoreUpdateCalls = 0;
    public int onNameUpdateCalls = 0;

    public GameState latestGameState = null;

    @Override
    public void onShiftUpdate(GameState gameState) {
        onUpdateCalls += 1;
        latestGameState = gameState;
    }

    @Override
    public void onPauseUpdate(GameState gameState) {
        onPauseUpdateCalls += 1;
        latestGameState = gameState;
    }

    @Override
    public void onMapUpdate(GameState gameState) {
        onMapUpdateCalls += 1;
        latestGameState = gameState;
    }

    @Override
    public void onScoreUpdate(GameState gameState) {
        onScoreUpdateCalls += 1;
        latestGameState = gameState;
    }

    @Override
    public void onNameUpdate(GameState gameState) {
        onNameUpdateCalls += 1;
        latestGameState = gameState;
    }
}
