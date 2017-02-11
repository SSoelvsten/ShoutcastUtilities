package Controller;

import AbstractFactory.GameStateControllerFactory;
import Format.GameStateFormattingStrategy;
import GameState.*;
import Config.*;
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
        //Team A
        readWriteStrategy.write(config.getString(ConfigKeys.file_A_name),
                formattingStrategy.teamAName(gameState));
        readWriteStrategy.write(config.getString(ConfigKeys.file_A_abbreviation),
                formattingStrategy.teamAAbbreviation(gameState));
        readWriteStrategy.write(config.getString(ConfigKeys.file_A_score),
                formattingStrategy.teamAScore(gameState));

        //Team B
        readWriteStrategy.write(config.getString(ConfigKeys.file_B_name),
                formattingStrategy.teamBName(gameState));
        readWriteStrategy.write(config.getString(ConfigKeys.file_B_abbreviation),
                formattingStrategy.teamBAbbreviation(gameState));
        readWriteStrategy.write(config.getString(ConfigKeys.file_B_score),
                formattingStrategy.teamBScore(gameState));

        //Pause
        readWriteStrategy.write(config.getString(ConfigKeys.file_pause),
                formattingStrategy.pause(gameState));

        //Game number
        readWriteStrategy.write(config.getString(ConfigKeys.file_game_number),
                formattingStrategy.gameNumber(gameState));
    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    @Override
    public void resetScore() {
        gameState.setTeamAPoints(0);
        gameState.setTeamBPoints(0);
    }

    @Override
    public void incrementTeamAScore() {
        gameState.setTeamAPoints(gameState.getTeamA().getPoints() + 1);
    }

    @Override
    public void decrementTeamAScore() {
        if(gameState.getTeamA().getPoints() != 0)
            gameState.setTeamAPoints(gameState.getTeamA().getPoints() - 1);
    }

    @Override
    public void incrementTeamBScore() {
        gameState.setTeamBPoints(gameState.getTeamB().getPoints() + 1);
    }

    @Override
    public void decrementTeamBScore() {
        if(gameState.getTeamB().getPoints() != 0)
            gameState.setTeamBPoints(gameState.getTeamB().getPoints() - 1);
    }

    @Override
    public void setSeriesLength(int length) {
        if(length >= 0)
            gameState.setSeriesLength(length);
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
