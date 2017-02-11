package GameState;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestKingOfTheHillGameState {

    private ModifiableGameState gameState;
    private Team teamA;
    private Team teamB;

    @Before
    public void setup(){
        this.gameState = new KingOfTheHillGameStateDecorator(new StandardGameState());
        this.teamA = gameState.getTeamA();
        this.teamB = gameState.getTeamB();
    }

    //Setup of new challenger
    @Test
    public void challengerIsInitializedTo0(){
        assertThat(gameState.getTeamB().getPoints(), is(0));
    }

    @Test
    public void swapsTeamsAtTeamBGettingPoints(){
        gameState.setTeamBPoints(1);

        //Has the teams been swapped?
        assertThat(gameState.getTeamA(), is(teamB));
        assertThat(gameState.getTeamB(), is(teamA));
    }

    @Test
    public void setsUpNewChallengerToZeroPoints(){
        gameState.setTeamAPoints(4);
        gameState.setTeamBPoints(2);

        //Is the new Team B at 0?
        assertThat(gameState.getTeamB().getPoints(), is(0));
        assertThat(gameState.getTeamB().getName(), is("Challenger"));
        assertThat(gameState.getTeamB().getAbbreviation(), is("?"));
    }

    @Test
    public void newKingKeepsThePointsAfterSwap(){
        gameState.setTeamAPoints(2);
        gameState.setTeamBPoints(1);

        assertThat(gameState.getTeamA().getPoints(), is(1));
    }

    //Game Number is TeamA.score + Accumulation of prior kings.score + 1
    @Test
    public void gameNumberIs5WhenTeamBKnocksOffTeamAAt3(){
        gameState.setTeamAPoints(3);
        gameState.setTeamBPoints(1);

        //Team A knocked off has 3 and Team B (Challenger) has 1 while it is the next game
        assertThat(gameState.getGameNumber(), is(3 + 1 + 1));
    }

    @Test
    public void gameNumberIs2WhenTeamBKnocksOffTeamAAt0(){
        gameState.setTeamAPoints(0);
        gameState.setTeamBPoints(1);

        assertThat(gameState.getGameNumber(), is(1 + 1));
    }

    @Test
    public void gameNumberIs9WhenChallengersKnockOfKingsAt3And4(){
        gameState.setTeamAPoints(3);
        gameState.setTeamBPoints(4);
        gameState.setTeamBPoints(1);

        assertThat(gameState.getGameNumber(), is(3 + 4 + 1 + 1));
    }
}
