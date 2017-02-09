package GameState;

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
    public void ShouldSwapTeams() {
        gameState.swapTeams();

        //TODO: This test requires the swap to happen with swapping the objects, rather than just the data.
        //   It can be discussed, if it should rather just be comparing names
        assertThat(gameState.getTeamA(), is(teamB));
        assertThat(gameState.getTeamB(), is(teamA));
    }

    @Test
    public void ShouldRenameTeamA(){
        gameState.setTeamAIdentity("New Name", "Æ");
        assertThat(gameState.getTeamA().getName(), is("New Name"));
        assertThat(gameState.getTeamA().getAbbreviation(), is("Æ"));
    }

    @Test
    public void ShouldRenameTeamB(){
        gameState.setTeamBIdentity("Another name", "Q");
        assertThat(gameState.getTeamB().getName(), is("Another name"));
        assertThat(gameState.getTeamB().getAbbreviation(), is("Q"));
    }

    @Test
    public void ShouldSetPointsA(){
        assertThat(gameState.getTeamA().getPoints(), is(0));
        gameState.setTeamAPoints(42);
        assertThat(gameState.getTeamA().getPoints(), is(42));
    }

    @Test
    public void ShouldSetPointsB(){
        assertThat(gameState.getTeamB().getPoints(), is(0));
        gameState.setTeamBPoints(21);
        assertThat(gameState.getTeamB().getPoints(), is(21));
    }
}
