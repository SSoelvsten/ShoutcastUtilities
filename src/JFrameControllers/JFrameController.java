package JFrameControllers;

import GameState.GameStateController;
import Time.ModifiableTimer;
import Time.NameCalcStrategyPair;

import java.util.List;

public interface JFrameController {

    /**
     * Add a modifiable GameState to the Interface.
     * @param gameState The gameState to add controlling buttons
     *                  on the JFrame.
     */
    public void addGameState(GameStateController gameState);

    /**
     * Add a modifiable Timer to the interface
     * @precondition: stratNames.size() = calcStrategies.size() > 0
     * @param timer The timer to control
     * @param calcStrats The (name, calculation) pairs to be used
     */
    public void addClock(ModifiableTimer timer,
                         List<NameCalcStrategyPair> calcStrats);
}
