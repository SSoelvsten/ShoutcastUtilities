package GlobalShortcuts;

import GameState.GameStateController;

public class UnpauseCommand implements Command {

    private GameStateController controller;

    public UnpauseCommand(GameStateController controller){
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.unpause();
    }
}
