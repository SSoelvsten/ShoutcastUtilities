package GameState;

import Observer.GameStateObserver;

import java.util.List;

/**
 * Objects from which to read the state of the current game
 */
public interface GameState {

    /**
     * @precondition: teamIndex is a valid index for a team
     * @param teamIndex The identifying index for a team
     *                  (in [0, n] for n teams)
     */
    public Team getTeam(int teamIndex);

    /**
     * Get the amount of teams in this GameState.
     *   the max teamIndex = getTeamAmount()-1
     * @return Amount of teams
     */
    public int getTeamsAmount();

    /**
     * Creates a copy of immutable teams. This is to be
     * used to read all teams / find index of a specific team
     * @return A representation of the teams and their ordering
     */
    public List<Team> getTeamsList();

    /**
     * @precondition: mapIndex is a valid index for a map.
     *                Use the iterator if you do not yet know.
     * @param mapIndex The identifying index for a map
     */
    public Map getMap(int mapIndex);

    /**
     * Creates a copy of the maps list This is to be used to
     * read all maps / find index of a specific map
     * @return A representation of the maps and their ordering
     */
    public List<Map> getMapsList();

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
     * Lets a Observer subscribe to the GameState
     * @param o The observer subscribing
     */
    public void subscribe(GameStateObserver o);

}