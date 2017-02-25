package GameState;

import Observer.GameStateObserver;

import java.util.Set;

public class GameStateControllerSpy implements GameStateController {

    public int teamIndex = -1;
    public int teamScoreSet = -1;
    public int teamScoreChange = -1;
    public int seriesLength = -1;
    public String teamName = null;
    public String teamAbbreviation = null;

    public boolean shift;

    public String pauseReason;
    public boolean unpause;

    public int mapIndex;
    public String mapName;
    public String mapGameType;
    public String mapNote;

    @Override
    public GameState getGameState() {
        return null;
    }

    @Override
    public boolean setTeamScoreTo(int teamIndex, int newScore) {
        this.teamIndex = teamIndex;
        this.teamScoreSet = newScore;

        return true;
    }

    @Override
    public boolean changeTeamScoreBy(int teamIndex, int change) {
        this.teamIndex = teamIndex;
        this.teamScoreChange = change;

        return true;
    }

    @Override
    public boolean setSeriesLength(int length) {
        this.seriesLength = length;

        return true;
    }

    @Override
    public boolean setTeamIdentity(int teamIndex, String teamName, String teamAbbreviation) {
        this.teamIndex = teamIndex;
        this.teamName = teamName;
        this.teamAbbreviation = teamAbbreviation;

        return true;
    }

    @Override
    public int createTeam(String name, String abbreviation, int score) {
        this.teamName = name;
        this.teamAbbreviation = abbreviation;
        this.teamScoreSet = score;

        return 0;
    }

    @Override
    public boolean removeTeam(int teamIndex) {
        this.teamIndex = teamIndex;

        return true;
    }

    @Override
    public void shiftTeams() {
        this.shift = true;
    }

    @Override
    public void startPause(int teamIndex, String reason) {
        this.teamIndex = teamIndex;
        this.pauseReason = reason;
    }

    @Override
    public void unpause() {
        this.unpause = true;
    }

    @Override
    public boolean setMap(int mapIndex, String name, String gameType, String note) {
        this.mapIndex = mapIndex;
        this.mapName = name;
        this.mapGameType = gameType;
        this.mapNote = note;

        return true;
    }

    @Override
    public void subscribe(Set<GameStateObserver> observerSet) {

    }

    @Override
    public boolean subscribe(GameStateObserver observer) {
        return false;
    }
}
