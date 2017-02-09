package GameState;

import Observer.GameStateObserver;

/**
 * A standard implementation of a game state
 */
public class StandardGameState implements ModifiableGameState {

    private ModifiableTeam teamA = new StandardTeam("Team A", "A", 0);
    private ModifiableTeam teamB = new StandardTeam("Team B", "B", 0);

    @Override
    public void swapTeams() {
        ModifiableTeam backup = this.teamB;
        this.teamB = this.teamA;
        this.teamA = backup;
    }

    @Override
    public void setTeamAIdentity(String name, String abbreviation) {
        teamA.setName(name);
        teamA.setAbbreviation(abbreviation);
    }

    @Override
    public void setTeamBIdentity(String name, String abbreviation) {
        teamB.setName(name);
        teamB.setAbbreviation(abbreviation);
    }

    @Override
    public void setTeamAPoints(int points) {
        teamA.setPoints(points);
    }

    @Override
    public void setTeamBPoints(int points) {
        teamB.setPoints(points);
    }

    @Override
    public Team getTeamA() {
        return this.teamA;
    }

    @Override
    public Team getTeamB() {
        return this.teamB;
    }

    @Override
    public void subscribe(GameStateObserver o) {

    }
}
