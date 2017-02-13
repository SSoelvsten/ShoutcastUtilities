package GameState;

import GameStateObserver.GameStateObserver;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A standard implementation of a game state
 */
public class StandardGameState implements ModifiableGameState {

    private ModifiableTeam teamA = new StandardTeam("Team A", "A", 0);
    private ModifiableTeam teamB = new StandardTeam("Team B", "B", 0);

    private Pause pause;

    private int seriesLength;
    private Set<GameStateObserver> observers = new HashSet<>();

    @Override
    public void shiftTeams() {
        ModifiableTeam temp = this.teamB;
        this.teamB = this.teamA;
        this.teamA = temp;

        callObserverUpdate();
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
    public void setPauseTeam(int teamIndex, String reason) {
        this.pause = new StandardPause(teamA, reason);

        callObserverPauseUpdate();
    }

    @Override
    public void unpause() {
        this.pause = null;

        callObserverPauseUpdate();
    }

    @Override
    public void setTeamIdentity(int teamIndex, String name, String abbreviation) {
        teamA.setName(name);
        teamA.setAbbreviation(abbreviation);

        callObserverNameUpdate();
    }

    @Override
    public void setTeamPoints(int teamIndex, int points) {
        teamA.setPoints(points);

        callObserverScoreUpdate();
    }


    @Override
    public Team getTeam(int teamIndex) {
        return this.teamA;
    }

    @Override
    public Iterator<Team> getTeamsIterator() {
        return null;
    }

    @Override
    public void setMap(int number, Map map) {
        //TODO: TDD this one possibly? Obivous implementation?
        //  Need to be sure, that maps are overwritten if asked
    }

    @Override
    public Map getMap(int mapIndex) {
        return null;
    }

    @Override
    public Iterator<Map> getMapsIterator() {
        return null;
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
        this.observers.add(o);
    }

    private void callObserverUpdate(){
        for(GameStateObserver o : observers){
            o.onShiftUpdate(this);
        }
    }

    private void callObserverNameUpdate(){
        for(GameStateObserver o : observers){
            o.onNameUpdate(this);
        }
    }

    private void callObserverScoreUpdate(){
        for(GameStateObserver o : observers){
            o.onScoreUpdate(this);
        }
    }

    private void callObserverPauseUpdate(){
        for(GameStateObserver o : observers){
            o.onPauseUpdate(this);
        }
    }
}
