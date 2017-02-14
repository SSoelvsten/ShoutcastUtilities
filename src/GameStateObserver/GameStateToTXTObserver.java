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
        int i = 0;
        for(Team t : gameState.getTeamsList()){
            String nameString = formatting.teamName(i, gameState);
            String abbreviationString = formatting.teamAbbreviation(i, gameState);

            i++;

            String nameFile = config.getString(ConfigKeys.folder_txt_dst) + "name" + i + ".txt";
            String abbreviationFile = config.getString(ConfigKeys.folder_txt_dst) + "abbreviation" + i + ".txt";
            writer.write(nameFile, nameString);
            writer.write(abbreviationFile, abbreviationString);
        }
    }

    @Override
    public void onPauseUpdate(GameState gameState) {
        writer.write(config.getString(ConfigKeys.folder_txt_dst) + "pause.txt",
                formatting.pause(gameState));
    }

    @Override
    public void onScoreUpdate(GameState gameState) {
        int i = 0;
        for(Team t : gameState.getTeamsList()){
            String scoreString = formatting.teamScore(i, gameState);

            i++;

            String filename = config.getString(ConfigKeys.folder_txt_dst) + "score" + i + ".txt";
            writer.write(filename, scoreString);
        }

        writer.write(config.getString(ConfigKeys.folder_txt_dst) + "game_number.txt", formatting.gameNumber(gameState));
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
