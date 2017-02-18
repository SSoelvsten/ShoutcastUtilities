package Observer;

import GameState.*;
import Config.*;
import InputOutput.FileHandler;

import java.io.IOException;

/**
 * A GameState observer, which sets up pictures where specified
 * with the thumbnails of the maps from the GameState
 */
public class GameStateMapThumbnailObserver extends AbstractGameStateObserver {

    Config config;
    FileHandler fileHandler;

    public GameStateMapThumbnailObserver(Config config,
                                         FileHandler fileHandler){
        this.config = config;
        this.fileHandler = fileHandler;
    }

    @Override
    public void onMapUpdate(GameState gameState) {
        int i = 0;

        for(Map map : gameState.getMapsList()){
            i++;

            String fromFile = map.getName().toLowerCase();
            if(map.getGameType() != null && !map.getGameType().equals(""))
                fromFile += "_" + map.getGameType().toLowerCase();
            fromFile += ".png";

            String toFile = "map" + i + ".png";
            try{
                fileHandler.copyFile(config.getString(ConfigKeys.folder_map_src), fromFile,
                        config.getString(ConfigKeys.folder_map_dst), toFile);
            } catch (IOException e) {
                try{
                    fileHandler.copyFile(config.getString(ConfigKeys.folder_map_src), config.getString(ConfigKeys.file_map_unknown),
                            config.getString(ConfigKeys.folder_map_dst), toFile);
                } catch (IOException f){
                    //Give up
                }
            }
        }
    }
}
