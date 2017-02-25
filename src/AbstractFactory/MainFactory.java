package AbstractFactory;

import Config.*;
import GameState.GameStateController;
import GlobalShortcuts.Command;
import InputOutput.ReadWriteStrategy;
import JFrameControllers.JFrameController;
import Observer.GameStateObserver;
import Observer.TimerObserver;
import Time.ModifiableTimer;
import Time.NameCalcStrategyPair;
import Time.TimerCalculatorStrategy;

import java.util.List;
import java.util.Set;

/**
 * A factory to create all the stuff handled by the initialization
 */
public interface MainFactory {

    public Config createConfig();

    public Validator createValidator();

    public ReadWriteStrategy createReadWriteStrategy();

    public GameStateController createGameStateController();

    public ModifiableTimer createTimer();

    public Set<GameStateObserver> createGameStateObservers();

    public List<NameCalcStrategyPair> createTimerCalculationStrategies();

    public Set<TimerObserver> createTimerObserver();

    public JFrameController createJFrameController();

    public Command createValueChangeCommand(GameStateController controller, int teamIndex, int change);

    public Command createUnpauseCommand(GameStateController controller);

    public Command createShiftCommand(GameStateController controller);
}
