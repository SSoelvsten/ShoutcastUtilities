package AbstractFactory;

import Config.*;
import Format.MinSecTimerFormattingStrategy;
import Format.StandardGameStateFormattingStrategy;
import GameState.GameStateController;
import GameState.StandardGameStateController;
import GlobalShortcuts.Command;
import GlobalShortcuts.ShiftCommand;
import GlobalShortcuts.UnpauseCommand;
import GlobalShortcuts.ValueChangeCommand;
import InputOutput.*;
import JFrameControllers.JFrameController;
import JFrameControllers.StandardJFrameController;
import Observer.*;
import Time.*;

import java.util.*;

/**
 * A factory setting up all features, but in their most general form
 * This should be the base for any other version.
 */
public class StandardMainFactory implements MainFactory {
    @Override
    public Config createConfig() {
        return new CFGConfig(new BasicReadWriteStrategy());
    }

    @Override
    public Validator createValidator() {
        return new StandardValidator(new StandardConfig());
    }

    @Override
    public ReadWriteStrategy createReadWriteStrategy() {
        return new BasicReadWriteStrategy();
    }

    @Override
    public GameStateController createGameStateController() {
        return new StandardGameStateController(new StandardGameStateFactory());
    }

    @Override
    public ModifiableTimer createTimer() {
        return new StandardTimer(1000, new StandardTimerCalculationStrategy(0,59,0,59,0,23));
    }

    @Override
    public Set<GameStateObserver> createGameStateObservers() {
        Set<GameStateObserver> observers = new HashSet<>();
        observers.add(new GameStateToTXTObserver(createConfig(),
                                                new StandardGameStateFormattingStrategy(createConfig()),
                                                createReadWriteStrategy()));

        observers.add(new GameStateMapThumbnailObserver(createConfig(),
                                                        new BasicFileHandler()));

        return observers;
    }

    @Override
    public List<NameCalcStrategyPair> createTimerCalculationStrategies() {
        ArrayList<NameCalcStrategyPair> res = new ArrayList<>();
        res.add(new NameCalcStrategyPair("Clock", new StandardTimerCalculationStrategy(0,59,0,59,0,23)));
        res.add(new NameCalcStrategyPair("Countdown", new StopAtZeroTimerCalculationStrategyDecorator(
                                                        new StandardTimerCalculationStrategy(59,0,59,0,23,0))));
        return res;
    }

    @Override
    public Set<TimerObserver> createTimerObserver() {
        Set<TimerObserver> observers = new HashSet<>();
        observers.add(new TimerToTXTObserver(1,
                                            createConfig(),
                                            new MinSecTimerFormattingStrategy(createConfig()),
                                            createReadWriteStrategy()));

        return observers;
    }

    @Override
    public JFrameController createJFrameController() {
        return new StandardJFrameController();
    }

    @Override
    public Command createValueChangeCommand(GameStateController controller, int teamIndex, int change) {
        return new ValueChangeCommand(controller, teamIndex, change);
    }

    @Override
    public Command createUnpauseCommand(GameStateController controller) {
        return new UnpauseCommand(controller);
    }

    @Override
    public Command createShiftCommand(GameStateController controller) {
        return new ShiftCommand(controller);
    }
}
