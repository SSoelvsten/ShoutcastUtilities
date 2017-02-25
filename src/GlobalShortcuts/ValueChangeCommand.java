package GlobalShortcuts;

import GameState.GameStateController;

/**
 * On an execution changes the amount of
 */
public class ValueChangeCommand implements Command {

    private GameStateController controller;
    private int teamIndex;
    private int change;

    public ValueChangeCommand(GameStateController controller, int teamIndex, int change){
        this.controller = controller;
        this.teamIndex = teamIndex;
        this.change = change;
    }

    @Override
    public void execute() {
        controller.changeTeamScoreBy(teamIndex, change);
    }
}
