import AbstractFactory.MainFactory;
import AbstractFactory.OBSStudioMainFactory;
import Config.Config;
import Config.ConfigKeys;
import GameState.GameStateController;
import Observer.*;
import GlobalShortcuts.*;
import InputOutput.ReadWriteStrategy;
import JFrameControllers.*;
import Time.*;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.List;
import java.util.logging.*;

/**
 * Sets up the whole software
 */
public class Main {

    public static void main(String[] args){
        //Initialize config and validate
        MainFactory factory = new OBSStudioMainFactory();

        Config config = factory.createConfig();
        (factory.createValidator()).ValidateConfig(config);

        //Initialize the Modifiable GameState
        GameStateController gsc = factory.createGameStateController();

        for(int i = 0; i < config.getInteger(ConfigKeys.team_amount); i++){
            gsc.createTeam("Team " + (i + 1), "" + (i + 1), 0);
        }

        for(int i = 0; i < config.getInteger(ConfigKeys.map_amount); i++){
            gsc.setMap(i, "Map" + (i + 1), "Type", null);
        }

        gsc.setSeriesLength(config.getInteger(ConfigKeys.map_amount));

        //Initialize the Timer and the strategies
        List<NameCalcStrategyPair> calcStrats = factory.createTimerCalculationStrategies();

        ModifiableTimer timer = factory.createTimer();

        //Initialize the various listeners
        ReadWriteStrategy output = factory.createReadWriteStrategy();

        for(GameStateObserver o : factory.createGameStateObservers()){
            gsc.subscribe(o);
        }

        for(TimerObserver o : factory.createTimerObserver()){
            timer.subscribe(o);
        }

        //Start JFrame Controller
        JFrameController frame = factory.createJFrameController();
        frame.addGameState(gsc);
        frame.addClock(timer, calcStrats);

        //Start Global shortcuts
        GlobalShortcuts shortcuts = null;
        if(config.getBoolean(ConfigKeys.enable_keybindings)
                && config.getInteger(ConfigKeys.team_amount) >= 2){
            Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.WARNING);
            try {
                GlobalScreen.registerNativeHook();
            } catch (NativeHookException e) {
                System.err.println("The global shortcuts could not be created");
            }
            GlobalScreen.addNativeKeyListener(new GlobalShortcuts(config, gsc, 0, 1));
        }
    }
}
