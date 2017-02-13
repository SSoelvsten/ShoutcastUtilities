package GameState;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestStandardGameState {

    private ModifiableGameState gameState;
    private int teamAIndex;
    private ModifiableTeam teamA;
    private int teamBIndex;
    private ModifiableTeam teamB;

    @Before
    public void setup() {
        gameState = new StandardGameState();

        this.teamA = new StandardTeam("Team A", "A", 0);
        teamAIndex = gameState.addTeam(teamA);

        this.teamB = new StandardTeam("Team B", "B", 0);
        teamBIndex = gameState.addTeam(teamB);
    }

    @Test
    public void shouldSwapTeamsOnShift() {
        gameState.shiftTeams();

        assertThat(gameState.getTeam(teamAIndex), is(teamB));
        assertThat(gameState.getTeam(teamBIndex), is(teamA));
    }

    @Test
    public void shouldShiftLeftWithMoreTeams(){
        ModifiableTeam teamC = new StandardTeam("Team C", "C", 0);
        int teamCIndex = gameState.addTeam(teamC);
        gameState.shiftTeams();

        assertThat(gameState.getTeam(teamAIndex), is(teamB));
        assertThat(gameState.getTeam(teamBIndex), is(teamC));
        assertThat(gameState.getTeam(teamCIndex), is(teamA));
    }

    @Test
    public void getWinnerShouldProvideNullOnDraw(){
        assertThat(gameState.getWinner(), IsNull.nullValue());
    }

    private void setPoints(int a, int b){
        gameState.setTeamPoints(teamAIndex, a);
        gameState.setTeamPoints(teamBIndex, b);
    }

    @Test
    public void getWinnerShouldProvideTeamAOn1To0(){
        setPoints(1,0);

        assertThat(gameState.getWinner(), is(gameState.getTeam(teamAIndex)));
    }

    @Test
    public void getWinnerShouldProvideTeamBOn1To2(){
        setPoints(1,2);

        assertThat(gameState.getWinner(), is(gameState.getTeam(teamBIndex)));
    }

    @Test
    public void gameNumberShouldBe1At0to0(){
        setPoints(0,0);

        assertThat(gameState.getGameNumber(), is(1));
    }

    @Test
    public void gameNumberShouldBe2At1to0(){
        setPoints(1,0);

        assertThat(gameState.getGameNumber(), is(2));
    }

    @Test
    public void gameNumberShouldBe3At1to1(){
        setPoints(1,1);

        assertThat(gameState.getGameNumber(), is(3));
    }

    @Test
    public void gameInitializesWithoutPause(){
        assertThat(gameState.getPause(), IsNull.nullValue());
    }

    @Test
    public void teamACanPauseWithoutReason(){
        gameState.setPauseTeam(teamAIndex, null);
        assertThat(gameState.getPause().getPausingTeam(), is(teamA));
        assertThat(gameState.getPause().getReason(), IsNull.nullValue());
    }

    @Test
    public void teamBCanPauseWithReason(){
        gameState.setPauseTeam(teamBIndex, "Potatoes");
        assertThat(gameState.getPause().getPausingTeam(), is(teamB));
        assertThat(gameState.getPause().getReason(), is("Potatoes"));
    }

    @Test
    public void shouldBeNullAfterUnpause(){
        gameState.setPauseTeam(0, null);
        gameState.unpause();
        assertThat(gameState.getPause(), IsNull.nullValue());
    }

    @Test
    public void shouldRenameTeamA(){
        gameState.setTeamIdentity(teamAIndex, "New Name", "Æ");
        assertThat(gameState.getTeam(teamAIndex).getName(), is("New Name"));
        assertThat(gameState.getTeam(teamAIndex).getAbbreviation(), is("Æ"));
    }

    @Test
    public void shouldRenameTeamB(){
        gameState.setTeamIdentity(teamBIndex, "Another name", "Q");
        assertThat(gameState.getTeam(teamBIndex).getName(), is("Another name"));
        assertThat(gameState.getTeam(teamBIndex).getAbbreviation(), is("Q"));
    }

    @Test
    public void shouldSetPointsA(){
        assertThat(gameState.getTeam(teamAIndex).getPoints(), is(0));
        gameState.setTeamPoints(teamAIndex, 42);
        assertThat(gameState.getTeam(teamAIndex).getPoints(), is(42));
    }

    @Test
    public void shouldSetPointsB(){
        assertThat(gameState.getTeam(teamBIndex).getPoints(), is(0));
        gameState.setTeamPoints(teamBIndex, 21);
        assertThat(gameState.getTeam(teamBIndex).getPoints(), is(21));
    }
}
