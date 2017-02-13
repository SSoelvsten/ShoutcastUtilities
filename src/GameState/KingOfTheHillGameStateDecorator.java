package GameState;

import GameStateObserver.GameStateObserver;

/**
 * A king of the hill decorator, meaning that on the challenger
 * (Team B) gaining a point, the challenger is set to be the
 * King of the Hill (Team A), and a Team B becomes a new fresh challenger.
 */
public class KingOfTheHillGameStateDecorator implements ModifiableGameState {

    private final ModifiableGameState gameState;
    private int priorKingsStreakAccumulator;

    public KingOfTheHillGameStateDecorator(ModifiableGameState gameState){
        this.gameState = gameState;
        this.gameState.setTeamBPoints(0);
    }

    @Override
    public Team getTeamA() {
        return gameState.getTeamA();
    }

    @Override
    public Team getTeamB() {
        return gameState.getTeamB();
    }

    /**
     * Per definition, the current King of the Hill is Team A
     * @return The Winner (King of the Hill)
     */
    @Override
    public Team getWinner() {
        return getTeamA();
    }

    @Override
    public int getGameNumber() {
        return priorKingsStreakAccumulator + gameState.getGameNumber();
    }

    @Override
    public void setTeamAPoints(int points) {
        gameState.setTeamAPoints(points);
    }

    @Override
    public void setTeamBPoints(int points) {
        priorKingsStreakAccumulator += gameState.getTeamA().getPoints();
        gameState.setTeamBPoints(points);
        shiftTeams();
        gameState.setTeamBIdentity("Challenger", "?");
        gameState.setTeamBPoints(0);
    }

    @Override
    public void shiftTeams() {
        gameState.shiftTeams();
    }

    @Override
    public void setTeamAIdentity(String name, String abbreviation) {
        gameState.setTeamAIdentity(name, abbreviation);
    }

    @Override
    public void setTeamBIdentity(String name, String abbreviation) {
        gameState.setTeamBIdentity(name, abbreviation);
    }

    @Override
    public void setPauseTeamA(String reason) {
        gameState.setPauseTeamA(reason);
    }

    @Override
    public void setPauseTeamB(String reason) {
        gameState.setPauseTeamB(reason);
    }

    @Override
    public void unpause() {
        gameState.unpause();
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
