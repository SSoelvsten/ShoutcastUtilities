package GameState;

import GameStateObserver.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestStandardGameToObserverInteraction {

    ModifiableGameState gameState;
    ObserverSpy observer;

    @Before
    public void setup(){
        observer = new ObserverSpy();

        gameState = new StandardGameState();
        gameState.subscribe(observer);
    }

    @Test
    public void shouldCallWithItselfAsAnArgument(){
        //Swap teams definitely should call
        gameState.swapTeams();

        assertThat(observer.latestGameState, is(gameState));
    }

    @Test
    public void shouldCallOnTeamRename(){
        gameState.setTeamAIdentity("NewA", "A");
        assertThat(observer.onNameUpdateCalls, is(1));

        gameState.setTeamBIdentity("NewB", "B");
        assertThat(observer.onNameUpdateCalls, is(2));
    }

    @Test
    public void shouldCallOnScoreChange(){
        gameState.setTeamAPoints(3);
        assertThat(observer.onScoreUpdateCalls, is(1));

        gameState.setTeamBPoints(2);
        assertThat(observer.onScoreUpdateCalls, is(2));
    }

    @Test
    public void shouldCallOnPauseChanges(){
        gameState.setPauseTeamA(null);
        assertThat(observer.onPauseUpdateCalls, is(1));

        gameState.setPauseTeamB(null);
        assertThat(observer.onPauseUpdateCalls, is(2));

        gameState.unpause();
        assertThat(observer.onPauseUpdateCalls, is(3));
    }
}
