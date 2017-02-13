package GameStateObserver;

import Format.GameStateFormattingStrategy;
import GameState.GameState;
import Config.*;
import ReadWrite.ReadWriteStrategy;

/**
 * On a change in the GameState, updates the corresponding txt files as
 * specified in the config and formatted with the formatting given
 */
public class GameStateToTXTObserver implements GameStateObserver {

    private final Config config;
    private final GameStateFormattingStrategy formatting;
    private final ReadWriteStrategy writer;

    public GameStateToTXTObserver(Config config,
                                  GameStateFormattingStrategy formattingStrategy,
                                  ReadWriteStrategy readWriteStrategy){
        this.config = config;
        this.formatting = formattingStrategy;
        this.writer = readWriteStrategy;
    }


    @Override
    public void onShiftUpdate(GameState gameState) {
        onNameUpdate(gameState);
        onScoreUpdate(gameState);
    }

    @Override
    public void onNameUpdate(GameState gameState) {
        writer.write(config.getString(ConfigKeys.file_A_name),
                formatting.teamAName(gameState));
        writer.write(config.getString(ConfigKeys.file_A_abbreviation),
                formatting.teamAAbbreviation(gameState));

        writer.write(config.getString(ConfigKeys.file_B_name),
                formatting.teamBName(gameState));
        writer.write(config.getString(ConfigKeys.file_B_abbreviation),
                formatting.teamBAbbreviation(gameState));
    }

    @Override
    public void onPauseUpdate(GameState gameState) {
        writer.write(config.getString(ConfigKeys.file_pause),
                formatting.pause(gameState));
    }

    @Override
    public void onScoreUpdate(GameState gameState) {
        writer.write(config.getString(ConfigKeys.file_A_score),
                formatting.teamAScore(gameState));
        writer.write(config.getString(ConfigKeys.file_B_score),
                formatting.teamBScore(gameState));

        writer.write(config.getString(ConfigKeys.file_game_number),
                formatting.gameNumber(gameState));
    }

    @Override
    public void onMapUpdate(GameState gameState) {
        //TODO: TDD this method
    }
}
