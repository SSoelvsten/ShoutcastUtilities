package GameStateObserver;

import Config.*;
import Format.*;
import GameState.*;

import InputOutput.ReadWriteStrategySpy2;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestGameStateToTXTObserver {

    private Config config;
    private GameStateFormattingStrategy formattingStrategy;
    private ReadWriteStrategySpy2 readWriteSpy;
    private GameStateObserver observer;
    private ModifiableGameState gameState;
    private int teamAIndex;
    private int teamBIndex;

    @Before
    public void setup(){
        this.gameState = new StandardGameState();
        this.teamAIndex = gameState.addTeam(new StandardTeam("Team A", "A", 0));
        this.teamBIndex = gameState.addTeam(new StandardTeam("Team B", "B", 0));

        this.config = new StandardConfig();
        this.formattingStrategy = new StandardGameStateFormattingStrategy(config);
        this.readWriteSpy = new ReadWriteStrategySpy2();

        this.observer = new GameStateToTXTObserver(config, formattingStrategy, readWriteSpy);
    }

    @Test
    public void shouldPrintTeam1NameFile(){
        observer.onNameUpdate(gameState);

        String file = "txt/name1.txt";
        assertThat(readWriteSpy.read(file),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(file),
                is(formattingStrategy.teamName(teamAIndex, gameState)));
    }

    @Test
    public void shouldPrintTeam1AbbreviationFile(){
        observer.onNameUpdate(gameState);

        String file = "txt/abbreviation1.txt";
        assertThat(readWriteSpy.read(file),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(file),
                is(formattingStrategy.teamAbbreviation(teamAIndex, gameState)));
    }

    @Test
    public void shouldPrintTeam1ScoreFile(){
        observer.onScoreUpdate(gameState);

        String file = "txt/score1.txt";
        assertThat(readWriteSpy.read(file),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(file),
                is(formattingStrategy.teamScore(teamAIndex, gameState)));
    }

    @Test
    public void shouldPrintTeam2NameFile(){
        observer.onNameUpdate(gameState);

        String file = "txt/name2.txt";
        assertThat(readWriteSpy.read(file),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(file),
                is(formattingStrategy.teamName(teamBIndex, gameState)));
    }

    @Test
    public void shouldPrintTeam2AbbreviationFile(){
        observer.onNameUpdate(gameState);

        String file = "txt/abbreviation2.txt";
        assertThat(readWriteSpy.read(file),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(file),
                is(formattingStrategy.teamAbbreviation(teamBIndex, gameState)));
    }

    @Test
    public void shouldPrintTeam2ScoreFile(){
        observer.onScoreUpdate(gameState);

        String file = "txt/score2.txt";
        assertThat(readWriteSpy.read(file),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(file),
                is(formattingStrategy.teamScore(teamBIndex, gameState)));
    }

    @Test
    public void shouldPrintGameNumber(){
        observer.onScoreUpdate(gameState);

        String file = "txt/game_number.txt";
        assertThat(readWriteSpy.read(file),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(file),
                is(formattingStrategy.gameNumber(gameState)));
    }

    @Test
    public void shouldPrintPause(){
        observer.onPauseUpdate(gameState);

        String file = "txt/pause.txt";
        assertThat(readWriteSpy.read(file),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(file),
                is(formattingStrategy.pause(gameState)));
    }

    @Test
    public void shouldPrint1MapFileFor1MapInGameState(){
        gameState.setMap(0, new StandardMap("FirstMap", "CTF", null));
        observer.onMapUpdate(gameState);

        //With only one map, it should be written to map1.txt
        String filename = config.getString(ConfigKeys.folder_map_dst) + "map1.txt";
        assertThat(readWriteSpy.read(filename),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(filename),
                is(formattingStrategy.map(0, gameState)));
    }

    @Test
    public void shouldPrintTeamAndScoreOnShift(){
        observer.onShiftUpdate(gameState);

        //A Name
        String file = "txt/name1.txt";
        assertThat(readWriteSpy.read(file),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(file),
                is(formattingStrategy.teamName(teamAIndex, gameState)));

        //B Abbreviation
        file = "txt/abbreviation2.txt";
        assertThat(readWriteSpy.read(file),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(file),
                is(formattingStrategy.teamAbbreviation(teamBIndex, gameState)));

        //A Score
        file = "txt/score1.txt";
        assertThat(readWriteSpy.read(file),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(file),
                is(formattingStrategy.teamScore(teamAIndex, gameState)));
    }

    @Test
    public void shouldPrintVictor(){
        observer.onScoreUpdate(gameState);

        String file = "txt/winner.txt";
        assertThat(readWriteSpy.read(file),
                IsNull.notNullValue());
        assertThat(readWriteSpy.read(file),
                is(formattingStrategy.winner(gameState)));
    }
}
