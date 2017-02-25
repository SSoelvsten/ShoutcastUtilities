package GlobalShortcuts;

import GameState.GameStateController;

public class ShiftCommand implements Command {

    private GameStateController controller;

    public ShiftCommand(GameStateController controller){
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.shiftTeams();
    }
}
