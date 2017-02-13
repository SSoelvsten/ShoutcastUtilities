package GameState;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestStandardGameState {

    private ModifiableGameState gameState;
    private Team teamA;
    private Team teamB;

    @Before
    public void setup() {
        this.gameState = new StandardGameState();
        this.teamA = gameState.getTeamA();
        this.teamB = gameState.getTeamB();
    }

    @Test
    public void shouldSwapTeams() {
        gameState.shiftTeams();

        assertThat(gameState.getTeamA(), is(teamB));
        assertThat(gameState.getTeamB(), is(teamA));
    }

    @Test
    public void getWinnerShouldProvideNullOnDraw(){
        assertThat(gameState.getWinner(), IsNull.nullValue());
    }

    @Test
    public void getWinnerShouldProvideTeamAOn1To0(){
        gameState.setTeamAPoints(1);
        gameState.setTeamBPoints(0);

        assertThat(gameState.getWinner(), is(teamA));
    }

    @Test
    public void getWinnerShouldProvideTeamBOn1To2(){
        gameState.setTeamAPoints(1);
        gameState.setTeamBPoints(2);

        assertThat(gameState.getWinner(), is(teamB));
    }

    @Test
    public void gameNumberShouldBe1At0to0(){
        gameState.setTeamAPoints(0);
        gameState.setTeamBPoints(0);

        assertThat(gameState.getGameNumber(), is(1));
    }

    @Test
    public void gameNumberShouldBe2At1to0(){
        gameState.setTeamAPoints(1);
        gameState.setTeamBPoints(0);

        assertThat(gameState.getGameNumber(), is(2));
    }

    @Test
    public void gameNumberShouldBe3At1to1(){
        gameState.setTeamAPoints(1);
        gameState.setTeamBPoints(1);

        assertThat(gameState.getGameNumber(), is(3));
    }

    @Test
    public void gameInitializesWithoutPause(){
        assertThat(gameState.getPause(), IsNull.nullValue());
    }

    @Test
    public void teamACanPauseWithoutReason(){
        gameState.setPauseTeamA(null);
        assertThat(gameState.getPause().getPausingTeam(), is(teamA));
        assertThat(gameState.getPause().getReason(), IsNull.nullValue());
    }

    @Test
    public void teamACanPauseWithReason(){
        gameState.setPauseTeamA("Tomatoes");
        assertThat(gameState.getPause().getPausingTeam(), is(teamA));
        assertThat(gameState.getPause().getReason(), is("Tomatoes"));
    }

    @Test
    public void teamBCanPauseWithReason(){
        gameState.setPauseTeamB("Potatoes");
        assertThat(gameState.getPause().getPausingTeam(), is(teamB));
        assertThat(gameState.getPause().getReason(), is("Potatoes"));
    }

    @Test
    public void shouldBeNullAfterUnpause(){
        gameState.setPauseTeamA(null);
        gameState.unpause();
        assertThat(gameState.getPause(), IsNull.nullValue());
    }

    @Test
    public void shouldRenameTeamA(){
        gameState.setTeamAIdentity("New Name", "Æ");
        assertThat(gameState.getTeamA().getName(), is("New Name"));
        assertThat(gameState.getTeamA().getAbbreviation(), is("Æ"));
    }

    @Test
    public void shouldRenameTeamB(){
        gameState.setTeamBIdentity("Another name", "Q");
        assertThat(gameState.getTeamB().getName(), is("Another name"));
        assertThat(gameState.getTeamB().getAbbreviation(), is("Q"));
    }

    @Test
    public void shouldSetPointsA(){
        assertThat(gameState.getTeamA().getPoints(), is(0));
        gameState.setTeamAPoints(42);
        assertThat(gameState.getTeamA().getPoints(), is(42));
    }

    @Test
    public void shouldSetPointsB(){
        assertThat(gameState.getTeamB().getPoints(), is(0));
        gameState.setTeamBPoints(21);
        assertThat(gameState.getTeamB().getPoints(), is(21));
    }
}
