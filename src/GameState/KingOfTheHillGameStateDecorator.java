package GameState;

import GameStateObserver.GameStateObserver;

import java.util.Iterator;

/**
 * A king of the hill decorator, meaning that on the challenger
 * gaining a point, the challenger is set to be the King of the Hill
 */
public class KingOfTheHillGameStateDecorator implements ModifiableGameState {

    private final ModifiableGameState gameState;
    private int kingIndex;

    public KingOfTheHillGameStateDecorator(ModifiableGameState gameState,
                                           int kingIndex){
        this.gameState = gameState;
        this.kingIndex = kingIndex;
    }

    @Override
    public Team getTeam(int teamIndex) {
        return gameState.getTeam(teamIndex);
    }

    @Override
    public int addTeam(ModifiableTeam team) {
        return gameState.addTeam(team);
    }

    @Override
    public void removeTeam(int teamIndex) {
        gameState.removeTeam(teamIndex);
    }

    @Override
    public Iterator<Team> getTeamsIterator() {
        return null;
    }

    /**
     * Per definition, the current King of the Hill is Team A
     * @return The Winner (King of the Hill)
     */
    @Override
    public Team getWinner() {
        return getTeam(kingIndex);
    }

    @Override
    public int getGameNumber() {
        return gameState.getGameNumber();
    }

    @Override
    public void setTeamPoints(int teamIndex, int points) {
        this.kingIndex = teamIndex;
        gameState.setTeamPoints(teamIndex, points);
    }

    @Override
    public void shiftTeams() {
        gameState.shiftTeams(); //This does mess up the kingIndex!
    }

    @Override
    public void setTeamIdentity(int teamIndex, String name, String abbreviation) {
        gameState.setTeamIdentity(teamIndex, name, abbreviation);
    }

    @Override
    public void setPauseTeam(int teamIndex, String reason) {
        gameState.setPauseTeam(teamIndex, reason);
    }

    @Override
    public void unpause() {
        gameState.unpause();
    }

    @Override
    public void setMap(int number, Map map) {
        gameState.setMap(number, map);
    }

    @Override
    public Map getMap(int mapIndex) {
        return gameState.getMap(mapIndex);
    }

    @Override
    public Iterator<Map> getMapsIterator() {
        return gameState.getMapsIterator();
    }

    @Override
    public Pause getPause() {
        return gameState.getPause();
    }

    @Override
    public void setSeriesLength(int length) {
        gameState.setSeriesLength(length);
    }

    @Override
    public int getSeriesLength() {
        return gameState.getSeriesLength();
    }

    @Override
    public void subscribe(GameStateObserver o) {
        gameState.subscribe(o);
    }
}
