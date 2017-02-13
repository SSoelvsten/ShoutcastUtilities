package GameStateObserver;

import Format.GameStateFormattingStrategy;
import GameState.*;
import Config.*;
import InputOutput.ReadWriteStrategy;

/**
 * On a change in the GameState, updates the corresponding txt files as
 * specified in the config and formatted with the formatting given
 */
public class GameStateToTXTObserver implements GameStateObserver {

    private final Config config;
    private final GameStateFormattingStrategy formatting;
    private final ReadWriteStrategy writer;

    private int teamAIndex;
    private int teamBIndex;

    public GameStateToTXTObserver(Config config,
                                  GameStateFormattingStrategy formattingStrategy,
                                  ReadWriteStrategy readWriteStrategy,
                                  int teamAIndex, int teamBIndex){
        this.config = config;
        this.formatting = formattingStrategy;
        this.writer = readWriteStrategy;

        this.teamAIndex = teamAIndex;
        this.teamBIndex = teamBIndex;
    }


    @Override
    public void onShiftUpdate(GameState gameState) {
        onNameUpdate(gameState);
        onScoreUpdate(gameState);
    }

    @Override
    public void onNameUpdate(GameState gameState) {
        writer.write(config.getString(ConfigKeys.file_A_name),
                formatting.teamName(teamAIndex, gameState));
        writer.write(config.getString(ConfigKeys.file_A_abbreviation),
                formatting.teamAbbreviation(teamAIndex, gameState));

        writer.write(config.getString(ConfigKeys.file_B_name),
                formatting.teamName(teamBIndex, gameState));
        writer.write(config.getString(ConfigKeys.file_B_abbreviation),
                formatting.teamAbbreviation(teamBIndex, gameState));
    }

    @Override
    public void onPauseUpdate(GameState gameState) {
        writer.write(config.getString(ConfigKeys.file_pause),
                formatting.pause(gameState));
    }

    @Override
    public void onScoreUpdate(GameState gameState) {
        writer.write(config.getString(ConfigKeys.file_A_score),
                formatting.teamScore(teamAIndex, gameState));
        writer.write(config.getString(ConfigKeys.file_B_score),
                formatting.teamScore(teamBIndex, gameState));

        writer.write(config.getString(ConfigKeys.file_game_number),
                formatting.gameNumber(gameState));
    }

    @Override
    public void onMapUpdate(GameState gameState) {
        int i = 0;
        for(Map map : gameState.getMapsList()){
            String mapString = formatting.map(i, gameState);
            i++;
            String filename = config.getString(ConfigKeys.folder_map_dst) + "map" + i + ".txt";
            writer.write(filename, mapString);
        }
    }
}
