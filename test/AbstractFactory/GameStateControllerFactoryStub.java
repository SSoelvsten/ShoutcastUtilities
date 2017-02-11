package AbstractFactory;

import Config.Config;
import Format.GameStateFormattingStrategy;
import GameState.ModifiableGameState;
import ReadWrite.ReadWriteStrategy;

public class GameStateControllerFactoryStub implements GameStateControllerFactory {

    private ModifiableGameState gameState;
    private Config config;
    private ReadWriteStrategy readWriteStrategy;
    private GameStateFormattingStrategy gameStateFormattingStrategy;

    public GameStateControllerFactoryStub(ModifiableGameState gameState,
                                          Config config,
                                          ReadWriteStrategy readWriteStrategy,
                                          GameStateFormattingStrategy gameStateFormattingStrategy){
        this.gameState = gameState;
        this.config = config;
        this.readWriteStrategy = readWriteStrategy;
        this.gameStateFormattingStrategy = gameStateFormattingStrategy;
    }

    public void setGameState(ModifiableGameState gameState){
        this.gameState = gameState;
    }

    @Override
    public ModifiableGameState getGameState() {
        return gameState;
    }

    public void setValidatedConfig(Config config){
        this.config = config;
    }

    @Override
    public Config getValidatedConfig() {
        return this.config;
    }

    public void setReadWriteStrategy(ReadWriteStrategy readWriteStrategy){
        this.readWriteStrategy = readWriteStrategy;
    }

    @Override
    public ReadWriteStrategy getReadWriteStrategy() {
        return readWriteStrategy;
    }

    public void setGameStateFormattingStrategy(GameStateFormattingStrategy gameStateFormattingStrategy){
        this.gameStateFormattingStrategy = gameStateFormattingStrategy;
    }

    @Override
    public GameStateFormattingStrategy getFormattingStrategy() {
        return gameStateFormattingStrategy;
    }
}
