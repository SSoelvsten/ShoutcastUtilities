package Observer;

import Config.*;
import GameState.ModifiableGameState;
import GameState.StandardGameState;
import GameState.StandardMap;
import InputOutput.FileHandlerSpy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TestGameStateMapThumbnailObserver {

    private ModifiableGameState gameState;
    private GameStateObserver observer;

    private FileHandlerSpy spy;
    private Config config;

    @Before
    public void setup(){
        spy = new FileHandlerSpy();
        config = new StandardConfig();
        observer = new GameStateMapThumbnailObserver(config, spy);
        gameState = new StandardGameState();
        gameState.subscribe(observer);
    }

    @Test
    public void onAddingFirstMapItIsWrittenToDisk(){
        gameState.setMap(0, new StandardMap("Name", "Type", null));
        assertThat(spy.filesCopied, is(1));
        assertThat(spy.files.get(config.getString(ConfigKeys.folder_map_dst)), is("map1.png"));
    }

    @Test
    public void onAddingTwoMapsBothAreWrittenToDiskInSeperateFiles(){
        gameState.setMap(0, new StandardMap("Name1", "Type", null)); //Copies one map
        gameState.setMap(1, new StandardMap("Name2", "Type", null)); //Copies two maps
        assertThat(spy.filesCopied, is(3));
        assertThat(spy.files.get(config.getString(ConfigKeys.folder_map_dst)), is("map2.png"));
    }

    //TODO: Do more testing of this.
    //   Does it handle unknown maps correctly?
}
