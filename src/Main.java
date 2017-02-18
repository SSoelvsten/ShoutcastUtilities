import Config.*;
import Format.StandardGameStateFormattingStrategy;
import GameState.*;
import Observer.*;
import GlobalShortcuts.*;
import InputOutput.*;
import JFrameControllers.*;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.logging.*;

/**
 * Sets up the whole software
 */
public class Main {

    public static void main(String[] args){
        //Initialize config and validate
        Config config = new CFGConfig(new BasicReadWriteStrategy());
        (new StandardValidator(new StandardConfig())).ValidateConfig(config);

        //Initialize the ReadWrite object
        ReadWriteStrategy rws = new BasicReadWriteStrategy();

        //Initialize the Modifiable GameState
        ModifiableGameState gs = new StandardGameState();

        for(int i = 0; i < config.getInteger(ConfigKeys.team_amount); i++){
            gs.addTeam(new StandardTeam("Team " + (i + 1), "" + (i + 1), 0));
        }

        for(int i = 0; i < config.getInteger(ConfigKeys.map_amount); i++){
            gs.setMap(i, new StandardMap("Map" + (i + 1), "Type", null));
        }

        gs.setSeriesLength(config.getInteger(ConfigKeys.map_amount));

        //Initialize the various listeners
        GameStateObserver fileObserver = new GameStateToTXTObserver(config,
                new StandardGameStateFormattingStrategy(config),
                new PreSpaceReadWriteStrategyDecorator(rws));
        gs.subscribe(fileObserver);

        GameStateObserver thumbnailObserver = new GameStateMapThumbnailObserver(config, new BasicFileHandler());
        gs.subscribe(thumbnailObserver);

        //Add the controllers
        JFrameController frame = new StandardJFrameController();
        frame.addGameState(gs);

        GlobalShortcuts shortcuts = null;
        if(config.getBoolean(ConfigKeys.enable_keybindings)
                && config.getInteger(ConfigKeys.team_amount) >= 2){
            Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.WARNING);
            try {
                GlobalScreen.registerNativeHook();
            } catch (NativeHookException e) {
                System.err.println("The global shortcuts could not be created");
            }
            GlobalScreen.addNativeKeyListener(new GlobalShortcuts(config, gs, 0, 1));
        }
    }
}
