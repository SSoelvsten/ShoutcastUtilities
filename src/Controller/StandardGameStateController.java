package Controller;

import AbstractFactory.GameStateControllerFactory;
import Format.GameStateFormattingStrategy;
import GameState.*;
import Config.Config;
import ReadWrite.ReadWriteStrategy;

public class StandardGameStateController implements GameStateController {

    private ModifiableGameState gameState;
    private Config config;
    private GameStateFormattingStrategy formattingStrategy;
    private ReadWriteStrategy readWriteStrategy;

    public StandardGameStateController(GameStateControllerFactory factory){
        this.gameState = factory.getGameState();
        this.config = factory.getValidatedConfig();
        this.formattingStrategy = factory.getFormattingStrategy();
        this.readWriteStrategy = factory.getReadWriteStrategy();
    }

    @Override
    public void printFiles() {
        //TODO: For later
    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    @Override
    public void resetScore() {
        //TODO: For later
    }

    @Override
    public void incrementTeamAScore() {
        //TODO: For later
    }

    @Override
    public void decrementTeamAScore() {
        //TODO: For later
    }

    @Override
    public void incrementTeamBScore() {
        //TODO: For later
    }

    @Override
    public void decrementTeamBScore() {
        //TODO: For later
    }

    @Override
    public void pauseTeamA(String reason) {
        this.gameState.setPauseTeamA(reason);
    }

    @Override
    public void pauseTeamB(String reason) {
        this.gameState.setPauseTeamB(reason);
    }

    @Override
    public void unpause() {
        this.gameState.unpause();
    }

    @Override
    public void swapTeams() {
        this.gameState.swapTeams();
    }

    @Override
    public void setTeamIdentities(String nameA, String abbreviationA,
                                  String nameB, String abbreviationB) {
        this.gameState.setTeamAIdentity(nameA, abbreviationA);
        this.gameState.setTeamAIdentity(nameB, abbreviationB);
    }
}
