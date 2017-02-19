package GameState;

import Observer.GameStateObserver;

import java.util.Set;

/**
 * A facade / controller, taking care of preconditions and
 * decoupling the Modifiable GameState from the rest, minimizing
 * the amount of coupling in all of the software
 */
public interface GameStateController {

    public GameState getGameState();

    public boolean setTeamScoreTo(int teamIndex, int newScore);
    public boolean changeTeamScoreBy(int teamIndex, int change);

    public boolean setSeriesLength(int length);

    public boolean setTeamIdentity(int teamIndex, String teamName, String teamAbbreviation);

    public int createTeam(String name, String abbreviation, int score);
    public boolean removeTeam(int teamIndex);

    public void shiftTeams();

    public void startPause(int teamIndex, String reason);
    public void unpause();

    public boolean setMap(int mapIndex, String name, String gameType, String note);

    public void subscribe(Set<GameStateObserver> observerSet);
    public boolean subscribe(GameStateObserver observer);
}
