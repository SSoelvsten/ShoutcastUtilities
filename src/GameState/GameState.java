package GameState;

import GameStateObserver.GameStateObserver;

import java.util.Iterator;

/**
 * Objects from which to read the state of the current game
 */
public interface GameState {

    /**
     * @precondition: teamIndex is a valid index for a team
     * @param teamIndex The identifying index for a team
     */
    public Team getTeam(int teamIndex);

    public Iterator<Team> getTeamsIterator();

    /**
     * @precondition: mapIndex is a valid index for a map.
     *                Use the iterator if you do not yet know.
     * @param mapIndex The identifying index for a map
     */
    public Map getMap(int mapIndex);

    public Iterator<Map> getMapsIterator();

    /**
     * Get the current team in the lead.
     * @return The team currently in the lead or null on a draw
     */
    public Team getWinner();

    /**
     * Get the game number
     * @return The game number
     */
    public int getGameNumber();

    /**
     * Get the amount of games for this series.
     * A length of 0 means that it has no length
     * @return A number >= 0
     */
    public int getSeriesLength();

    /**
     * Get the current pause
     * @return A Pause-object if paused, null otherwise
     */
    public Pause getPause();

    /**
     * Lets a GameStateObserver subscribe to the GameState
     * @param o The observer subscribing
     */
    public void subscribe(GameStateObserver o);

}