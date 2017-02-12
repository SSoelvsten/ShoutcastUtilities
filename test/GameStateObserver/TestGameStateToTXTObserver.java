package GameStateObserver;

import Config.*;
import Format.*;
import GameState.ModifiableGameState;

import GameState.StandardGameState;
import ReadWrite.ReadWriteStrategy;
import ReadWrite.ReadWriteStrategySpy2;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestGameStateToTXTObserver {

    private Config config;
    private GameStateFormattingStrategy formattingStrategy;
    private ReadWriteStrategy readWriteSpy;
    private GameStateObserver observer;
    private ModifiableGameState gameState;

    @Before
    public void setup(){
        this.config = new StandardConfig();
        this.formattingStrategy = new StandardGameStateFormattingStrategy(config);
        this.readWriteSpy = new ReadWriteStrategySpy2();
        this.observer = new GameStateToTXTObserver(config, formattingStrategy, readWriteSpy);
        this.gameState = new StandardGameState();
    }

    @Test
    public void ShouldPrintTeamANameFile(){
        observer.onNameUpdate(gameState);

        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_A_name)),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_A_name)),
                is(formattingStrategy.teamAName(gameState)));
    }

    @Test
    public void ShouldPrintTeamAAbbreviationFile(){
        observer.onNameUpdate(gameState);

        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_A_abbreviation)),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_A_abbreviation)),
                is(formattingStrategy.teamAAbbreviation(gameState)));
    }

    @Test
    public void ShouldPrintTeamAScoreFile(){
        observer.onScoreUpdate(gameState);

        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_A_score)),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_A_score)),
                is(formattingStrategy.teamAScore(gameState)));
    }

    @Test
    public void ShouldPrintTeamBNameFile(){
        observer.onNameUpdate(gameState);

        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_B_name)),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_B_name)),
                is(formattingStrategy.teamBName(gameState)));
    }

    @Test
    public void ShouldPrintTeamBAbbreviationFile(){
        observer.onNameUpdate(gameState);

        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_B_abbreviation)),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_B_abbreviation)),
                is(formattingStrategy.teamBAbbreviation(gameState)));
    }

    @Test
    public void ShouldPrintTeamBScoreFile(){
        observer.onScoreUpdate(gameState);

        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_B_score)),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_B_score)),
                is(formattingStrategy.teamBScore(gameState)));
    }

    @Test
    public void ShouldPrintGameNumber(){
        observer.onScoreUpdate(gameState);

        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_game_number)),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_game_number)),
                is(formattingStrategy.gameNumber(gameState)));
    }

    @Test
    public void ShouldPrintPause(){
        observer.onPauseUpdate(gameState);

        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_pause)),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_pause)),
                is(formattingStrategy.pause(gameState)));
    }

    @Test
    public void ShouldPrintEverythingOnGeneral(){
        observer.onUpdate(gameState);

        //A Name
        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_A_name)),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_A_name)),
                is(formattingStrategy.teamAName(gameState)));

        //B Abbreviation
        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_B_abbreviation)),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_B_abbreviation)),
                is(formattingStrategy.teamBAbbreviation(gameState)));

        //A Score
        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_A_score)),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_A_score)),
                is(formattingStrategy.teamAScore(gameState)));

        //Pause
        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_pause)),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(config.getString(ConfigKeys.file_pause)),
                is(formattingStrategy.pause(gameState)));
    }
}
