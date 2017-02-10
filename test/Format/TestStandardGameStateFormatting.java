package Format;

import Config.*;
import GameState.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestStandardGameStateFormatting {

    private ConfigStub config;
    StandardGameStateFormattingStrategy format;

    ModifiableGameState gameState;
    private Team teamA;
    private Team teamB;

    @Before
    public void setup(){
        //Set up the validated StubConfig
        this.config = new ConfigStub();
        Validator validator = new StandardValidator();
        validator.ValidateConfig(config);

        //Set up the format
        this.format = new StandardGameStateFormattingStrategy(config);

        //Set up the gamestate with shortcuts to the two teams
        this.gameState = new StandardGameState();
        this.teamA = gameState.getTeamA();
        this.teamB = gameState.getTeamB();
    }

    //Many of these things are 'obvious implementation'

    //So here are only the methods, which are not just returning
    //a value already contained within the game state

    @Test
    public void noPauseShouldResultInEmptyString(){
        assertThat(format.pause(gameState), is(""));
    }

    @Test
    public void pauseFromAWithNoReasonShouldResultInOnlyTeamName(){
        gameState.setPauseTeamA(null);
        assertThat(format.pause(gameState), is("Game Paused: " + teamA.getName()));
    }

    @Test
    public void pauseFromBWithReasonShouldResultInLongerPauseMessage(){
        gameState.setPauseTeamB("Tomatoes");
        assertThat(format.pause(gameState),
                is("Game Paused: " + teamB.getName() + ", Tomatoes"));
    }

    private void setGameScore(int a, int b){
        gameState.setTeamAPoints(a);
        gameState.setTeamBPoints(b);
    }

    @Test
    public void noWinnerShouldResultInDraw(){
        setGameScore(1,1);
        assertThat(format.winner(gameState), is("draw"));
    }

    @Test
    public void winningTeamHasNameWritten(){
        setGameScore(1,0);
        assertThat(format.winner(gameState), is(teamA.getName()));
    }

    @Test
    public void noSeriesLengthWith0To0ResultsInMap1(){
        setGameScore(0,0);
        gameState.setSeriesLength(0);

        assertThat(format.gameNumber(gameState),
                is("Map 1"));
    }

    @Test
    public void noSeriesLengthWith1To0ResultsInMap2(){
        setGameScore(0,1);
        gameState.setSeriesLength(0);

        assertThat(format.gameNumber(gameState),
            is("Map 2"));
    }

    @Test
    public void prefixCanBeChangedThroughTheConfig(){
        config.put(ConfigKeys.string_pre_game_number, "Tomato");

        setGameScore(1,1);
        gameState.setSeriesLength(0);

        assertThat(format.gameNumber(gameState),
                is("Tomato 3"));
    }

    @Test
    public void SeriesLengthOf3ShouldResultInOf3Suffix(){
        setGameScore(1,0);
        gameState.setSeriesLength(3);

        assertThat(format.gameNumber(gameState),
                is("Map 2 of 3"));
    }

    @Test
    public void SeriesLengthOf5ShouldResultInOf5Suffix(){
        setGameScore(2,1);
        gameState.setSeriesLength(5);

        assertThat(format.gameNumber(gameState),
                is("Map 4 of 5"));
    }
}
