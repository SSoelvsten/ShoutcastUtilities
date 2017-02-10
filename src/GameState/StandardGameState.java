package GameState;

/**
 * A standard implementation of a game state
 */
public class StandardGameState implements ModifiableGameState {

    private ModifiableTeam teamA = new StandardTeam("Team A", "A", 0);
    private ModifiableTeam teamB = new StandardTeam("Team B", "B", 0);

    private Pause pause;

    private int seriesLength;

    @Override
    public void swapTeams() {
        ModifiableTeam temp = this.teamB;
        this.teamB = this.teamA;
        this.teamA = temp;
    }

    @Override
    public Team getWinner() {
        if(teamA.getPoints() > teamB.getPoints()){
            return teamA;
        } else if(teamA.getPoints() < teamB.getPoints()) {
            return teamB;
        } else {
            return null;
        }
    }

    @Override
    public int getGameNumber() {
        return teamA.getPoints() + teamB.getPoints() + 1;
    }

    @Override
    public void setPauseTeamA(String reason) {
        this.pause = new StandardPause(teamA, reason);
    }

    @Override
    public void setPauseTeamB(String reason) {
        this.pause = new StandardPause(teamB, reason);
    }

    @Override
    public void unpause() {
        this.pause = null;
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
    public Pause getPause() {
        return this.pause;
    }

    @Override
    public void setSeriesLength(int length) {
        this.seriesLength = length;
    }

    @Override
    public int getSeriesLength() {
        return this.seriesLength;
    }

    @Override
    public void subscribe(GameStateObserver o) {
        //TODO: Yet no tests to require anything here. Will happen with GUI
    }
}
