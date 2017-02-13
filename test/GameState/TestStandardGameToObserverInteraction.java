package GameState;

import GameStateObserver.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestStandardGameToObserverInteraction {

    ModifiableGameState gameState;
    GameStateObserverSpy observer;
    private int teamAIndex;
    private int teamBIndex;

    @Before
    public void setup(){
        observer = new GameStateObserverSpy();

        gameState = new StandardGameState();
        teamAIndex = gameState.addTeam(new StandardTeam("Team A", "A", 0));
        teamBIndex = gameState.addTeam(new StandardTeam("Team B", "B", 0));

        gameState.subscribe(observer);
    }

    @Test
    public void shouldCallWithItselfAsAnArgument(){
        //Swap teams definitely should call
        gameState.shiftTeams();

        assertThat(observer.latestGameState, is(gameState));
    }

    @Test
    public void shouldCallOnTeamRename(){
        gameState.setTeamIdentity(teamAIndex, "NewA", "A");
        assertThat(observer.onNameUpdateCalls, is(1));

        gameState.setTeamIdentity(teamBIndex, "NewB", "B");
        assertThat(observer.onNameUpdateCalls, is(2));
    }

    @Test
    public void shouldCallOnScoreChange(){
        gameState.setTeamPoints(teamAIndex, 3);
        assertThat(observer.onScoreUpdateCalls, is(1));

        gameState.setTeamPoints(teamBIndex, 2);
        assertThat(observer.onScoreUpdateCalls, is(2));
    }

    @Test
    public void shouldCallOnMapChange(){
        gameState.setMap(0, new StandardMap("MapName", "CTF"));
        assertThat(observer.onMapUpdateCalls, is(1));
    }

    @Test
    public void shouldCallOnPauseChanges(){
        gameState.setPauseTeam(teamAIndex, null);
        assertThat(observer.onPauseUpdateCalls, is(1));

        gameState.setPauseTeam(teamBIndex, null);
        assertThat(observer.onPauseUpdateCalls, is(2));

        gameState.unpause();
        assertThat(observer.onPauseUpdateCalls, is(3));
    }
}
