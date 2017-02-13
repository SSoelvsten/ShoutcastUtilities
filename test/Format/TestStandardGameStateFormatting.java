package Format;

import Config.*;
import GameState.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestStandardGameStateFormatting {

    private ConfigStub config;
    private StandardGameStateFormattingStrategy format;

    private ModifiableGameState gameState;
    private int teamAIndex;
    private Team teamA;
    private int teamBIndex;
    private Team teamB;

    @Before
    public void setup(){
        //Set up the validated StubConfig
        config = new ConfigStub();
        Validator validator = new StandardValidator(new StandardConfig());
        validator.ValidateConfig(config);

        //Set up the format
        format = new StandardGameStateFormattingStrategy(config);

        //Set up the gamestate with shortcuts to the two teams
        gameState = new StandardGameState();
        teamAIndex = gameState.addTeam(new StandardTeam("Team A", "A", 0));
        teamA = gameState.getTeam(teamAIndex);
        teamBIndex = gameState.addTeam(new StandardTeam("Team B", "B", 0));
        teamB = gameState.getTeam(teamBIndex);
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
        gameState.setPauseTeam(teamAIndex, null);
        assertThat(format.pause(gameState), is("Game Paused: " + teamA.getName()));
    }

    @Test
    public void pauseFromBWithReasonShouldResultInLongerPauseMessage(){
        gameState.setPauseTeam(teamBIndex, "Tomatoes");
        assertThat(format.pause(gameState),
                is("Game Paused: " + teamB.getName() + ", Tomatoes"));
    }

    private void setGameScore(int a, int b){
        gameState.setTeamPoints(teamAIndex, a);
        gameState.setTeamPoints(teamBIndex, b);
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
    public void seriesLengthOf3ShouldResultInOf3Suffix(){
        setGameScore(1,0);
        gameState.setSeriesLength(3);

        assertThat(format.gameNumber(gameState),
                is("Map 2 of 3"));
    }

    @Test
    public void seriesLengthOf5ShouldResultInOf5Suffix(){
        setGameScore(2,1);
        gameState.setSeriesLength(5);

        assertThat(format.gameNumber(gameState),
                is("Map 4 of 5"));
    }

    @Test
    public void prefixChangedInConfigApplies(){
        config.put(ConfigKeys.string_pre_game_number, "Game");
        setGameScore(1,0);

        assertThat(format.gameNumber(gameState),
                is("Game 2"));
    }

    @Test
    public void mapWithoutTypeShouldBeNameAlone(){
        gameState.setMap(0, new StandardMap("MapName", null));
        assertThat(format.map(0, gameState), is("MapName"));
    }

    @Test
    public void mapWithTypeShouldBePutInParantheses(){
        gameState.setMap(0, new StandardMap("MapName", "Type"));
        assertThat(format.map(0, gameState), is("MapName (Type)"));
    }
}
