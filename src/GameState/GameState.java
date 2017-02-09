package GameState;

import Observer.GameStateObserver;

/**
 * Objects from which to read the state of the current game
 */
public interface GameState {

    public Team getTeamA();

    public Team getTeamB();

    public void subscribe(GameStateObserver o);
}
