package Controller;

import AbstractFactory.GameStateControllerFactory;
import AbstractFactory.GameStateControllerFactoryStub;
import Config.StandardConfig;
import Format.StandardGameStateFormattingStrategy;
import GameState.*;
import ReadWrite.ReadWriteStrategySpy2;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TestStandardGameStateController {

    private ModifiableGameState gameState;
    private Team teamA;
    private Team teamB;

    private GameStateControllerFactory factory;
    private ReadWriteStrategySpy2 readWriteSpy;

    private GameStateController controller;
    private StandardConfig config;

    @Before
    public void setup(){
        this.gameState = new StandardGameState();
        this.teamA = gameState.getTeamA();
        this.teamB = gameState.getTeamB();

        this.readWriteSpy = new ReadWriteStrategySpy2();
        this.config = new StandardConfig();

        this.factory = new GameStateControllerFactoryStub(gameState,
                config,
                readWriteSpy,
                new StandardGameStateFormattingStrategy(config));

        this.controller = new StandardGameStateController(factory);
    }

    @Test
    public void OnResetScoreIs0to0(){
        gameState.setTeamAPoints(2);
        gameState.setTeamBPoints(1);

        controller.resetScore();

        assertThat(controller.getGameState().getTeamA().getPoints(), is(0));    //Fail with was: 2
        assertThat(controller.getGameState().getTeamB().getPoints(), is(0));    //Fail with was: 1
    }

    //The increment/decrement tests are so close to 'obvious implementation',
    //that I'm going to break the 'Test in Isolation' principle
    @Test
    public void ShouldIncrementTeamAFrom0To1To2(){
        gameState.setTeamAPoints(0);

        controller.incrementTeamAScore();
        assertThat(controller.getGameState().getTeamA().getPoints(), is(1));

        controller.incrementTeamAScore();
        assertThat(controller.getGameState().getTeamA().getPoints(), is(2));
    }

    @Test
    public void ShouldDecrementTeamAFrom2To1To0(){
        gameState.setTeamAPoints(2);

        controller.decrementTeamAScore();
        assertThat(controller.getGameState().getTeamA().getPoints(), is(1));

        controller.decrementTeamAScore();
        assertThat(controller.getGameState().getTeamA().getPoints(), is(0));
    }

    @Test
    public void ShouldNotDecrementTeamABelow0(){
        gameState.setTeamAPoints(0);

        controller.decrementTeamAScore();
        assertThat(controller.getGameState().getTeamA().getPoints(), is(0));
    }

    //Let us have the same for Team B
    @Test
    public void ShouldIncrementTeamBFrom0To1To2(){
        gameState.setTeamBPoints(0);

        controller.incrementTeamBScore();
        assertThat(controller.getGameState().getTeamB().getPoints(), is(1));

        controller.incrementTeamBScore();
        assertThat(controller.getGameState().getTeamB().getPoints(), is(2));
    }

    @Test
    public void ShouldDecrementTeamBFrom2To1To2(){
        gameState.setTeamBPoints(2);

        controller.decrementTeamBScore();
        assertThat(controller.getGameState().getTeamB().getPoints(), is(1));

        controller.decrementTeamBScore();
        assertThat(controller.getGameState().getTeamB().getPoints(), is(0));
    }

    @Test
    public void ShouldNotDecrementTeamBBelow0(){
        gameState.setTeamBPoints(0);

        controller.decrementTeamBScore();
        assertThat(controller.getGameState().getTeamB().getPoints(), is(0));
    }
}
