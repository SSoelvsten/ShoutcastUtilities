package GameState;

import Factory.GameStateFactoryStub;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestStandardGameStateController {

    private GameStateFactoryStub factory;
    private ModifiableGameState gameState;

    private StandardGameStateController controller;

    @Before
    public void setup(){
        gameState = new StandardGameState();

        factory = new GameStateFactoryStub();
        factory.gameState = gameState;

        controller = new StandardGameStateController(factory);

        factory.team = new StandardTeam("Team A", "A", 0);
        controller.createTeam("", "", 1); //That the team from above is used is tested

        factory.team = new StandardTeam("Team B", "B", 0);
        controller.createTeam("", "", 1);
    }

    @Test
    public void shouldAddTeamsFromFactory(){
        assertThat(gameState.getTeam(0).getName(), is("Team A"));
        assertThat(gameState.getTeam(1).getName(), is("Team B"));
    }

    @Test
    public void shouldNotSetForTeamsNotDefined(){
        //Call all methods with an index with a improper index
        controller.setTeamIdentity(2, "Team X", "X");
        controller.setTeamScoreTo(3, 2);
        controller.changeTeamScoreBy(2, -2);
        controller.removeTeam(3);
        controller.startPause(2, "Bananas");
    }

    @Test
    public void shouldNotDecrementBelow0From1(){
        assertThat(gameState.getTeam(0).getPoints(), is(0));
        controller.changeTeamScoreBy(0, -1);
        assertThat(gameState.getTeam(0).getPoints(), is(0));
    }

    @Test
    public void shouldNotDecemrentBelow0From2(){
        controller.setTeamScoreTo(0, 2);
        assertThat(gameState.getTeam(0).getPoints(), is(2));
        controller.changeTeamScoreBy(0, -3);
        assertThat(gameState.getTeam(0).getPoints(), is(2));
    }

    @Test
    public void shouldSetTeamScoreAbove0(){
        controller.setTeamScoreTo(0, 42);
        assertThat(gameState.getTeam(0).getPoints(), is(42));
        controller.setTeamScoreTo(0, 0);
        assertThat(gameState.getTeam(0).getPoints(), is(0));
    }

    @Test
    public void shouldNotSetTeamScoreBelow0(){
        controller.setTeamScoreTo(1, -2);
        assertThat(gameState.getTeam(1).getPoints(), is(0));
    }

    @Test
    public void shouldIncrement(){
        assertThat(gameState.getTeam(1).getPoints(), is(0));
        controller.changeTeamScoreBy(0, 1);
        assertThat(gameState.getTeam(0).getPoints(), is(1));
    }
}
