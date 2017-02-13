package GameState;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestKingOfTheHillGameState {

    private ModifiableGameState gameState;
    private int teamAIndex;
    private Team teamA;
    private int teamBIndex;
    private Team teamB;

    @Before
    public void setup(){
        ModifiableGameState decoratedGameState = new StandardGameState();
        teamAIndex = decoratedGameState.addTeam(new StandardTeam("Team A", "A", 0));
        teamA = decoratedGameState.getTeam(teamAIndex);
        teamBIndex = decoratedGameState.addTeam(new StandardTeam("Team B", "B", 0));
        teamB = decoratedGameState.getTeam(teamBIndex);

        this.gameState = new KingOfTheHillGameStateDecorator(decoratedGameState, teamAIndex);

    }

    @Test
    public void teamAIsStillKingWhenShifted(){
        gameState.shiftTeams();
        assertThat(gameState.getWinner(), is(teamA));
    }

    @Test
    public void teamBIsKingWhenGettingAPoint(){
        gameState.setTeamPoints(teamBIndex, 1);

        assertThat(gameState.getWinner(), is(gameState.getTeam(teamBIndex)));
    }

    private void setPoints(int a, int b){
        gameState.setTeamPoints(teamAIndex, a);
        gameState.setTeamPoints(teamBIndex, b);
    }

    @Test
    public void newKingKeepsThePointsAfterSwap(){
        setPoints(2,1);

        assertThat(gameState.getTeam(teamBIndex).getPoints(), is(1));
    }

    //Game Number is TeamA.score + Accumulation of prior kings.score + 1
    @Test
    public void gameNumberIs5WhenTeamBKnocksOffTeamAAt3(){
        setPoints(3, 1);

        //Team A knocked off has 3 and Team B (Challenger) has 1 while it is the next game
        assertThat(gameState.getGameNumber(), is(3 + 1 + 1));
    }

    @Test
    public void gameNumberIs2WhenTeamBKnocksOffTeamAAt0(){
        setPoints(0,1);

        assertThat(gameState.getGameNumber(), is(1 + 1));
    }

    @Test
    public void gameNumberIs9WhenChallengersKnockOfKingsAt3And4(){
        setPoints(3,4);
        int teamCIndex = gameState.addTeam(new StandardTeam("Team C", "C", 0));
        gameState.setTeamPoints(teamCIndex, 1);

        assertThat(gameState.getGameNumber(), is(3 + 4 + 1 + 1));
    }
}
