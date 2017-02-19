package GameState;

import AbstractFactory.GameStateFactory;
import Observer.GameStateObserver;

import java.util.Set;

public class StandardGameStateController implements GameStateController {

    private ModifiableGameState gs;

    private GameStateFactory factory;

    public StandardGameStateController(GameStateFactory factory){
        this.factory = factory;
        this.gs = factory.createGameState();
    }


    @Override
    public GameState getGameState() {
        return gs;
    }

    @Override
    public boolean setTeamScoreTo(int teamIndex, int newScore) {
        if(newScore >= 0){
            gs.setTeamPoints(teamIndex, newScore);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean changeTeamScoreBy(int teamIndex, int change) {
        int newScore = gs.getTeam(teamIndex).getPoints() + change;

        if(teamIndex < gs.getTeamsAmount() && newScore >= 0){
            gs.setTeamPoints(teamIndex, newScore);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean setSeriesLength(int length) {
        if(length >= 0){
            gs.setSeriesLength(length);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean setTeamIdentity(int teamIndex, String teamName, String teamAbbreviation) {
        if (teamIndex < gs.getTeamsAmount()){
            gs.setTeamIdentity(teamIndex, teamName, teamAbbreviation);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int createTeam(String name, String abbreviation, int score) {
        return gs.addTeam(factory.createTeam(name, abbreviation, score));
    }

    @Override
    public boolean removeTeam(int teamIndex) {
        if(teamIndex < gs.getTeamsAmount()){
            gs.removeTeam(teamIndex);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void shiftTeams() {
        gs.shiftTeams();
    }

    @Override
    public void startPause(int teamIndex, String reason) {
        if(teamIndex < gs.getTeamsAmount())
            gs.setPauseTeam(teamIndex, reason);
    }

    @Override
    public void unpause() {
        gs.unpause();
    }

    @Override
    public boolean setMap(int mapIndex, String name, String gameType, String note) {
        gs.setMap(mapIndex, factory.createMap(name, gameType, note));
        return true;
    }

    @Override
    public void subscribe(Set<GameStateObserver> observerSet) {
        for(GameStateObserver o : observerSet){
            subscribe(o);
        }
    }

    @Override
    public boolean subscribe(GameStateObserver observer) {
        gs.subscribe(observer);
        return true;
    }
}
