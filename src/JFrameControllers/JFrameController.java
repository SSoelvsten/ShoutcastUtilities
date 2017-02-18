package JFrameControllers;

import GameState.ModifiableGameState;
import Time.ModifiableTimer;
import Time.TimerCalculatorStrategy;

import java.util.ArrayList;

public interface JFrameController {

    /**
     * Add a modifiable GameState to the Interface.
     * @param gameState The gameState to add controlling buttons
     *                  on the JFrame.
     */
    public void addGameState(ModifiableGameState gameState);

    /**
     * Add a modifiable Timer to the interface
     * @precondition: stratNames.size() = calcStrategies.size() > 0
     * @param timer The timer to control
     * @param tickrate The tiokrate with which to change the values
     * @param stratNames The identifying names associated with the strategies
     * @param calcStrategies The strategies to use.
     */
    public void addClock(ModifiableTimer timer,
                         ArrayList<String> stratNames,
                         ArrayList<TimerCalculatorStrategy> calcStrategies);
}
