import GameState.*;
import JFrameControllers.JFrameController;
import JFrameControllers.StandardJFrameController;

/**
 * Sets up the whole software
 */
public class Main {

    public static void main(String[] args){
        ModifiableGameState gs = new StandardGameState();
        gs.addTeam(new StandardTeam("Team A", "A", 0));
        gs.addTeam(new StandardTeam("Team B", "B", 0));

        gs.setMap(0, new StandardMap("Map", "Type"));
        gs.setMap(1, new StandardMap("Map", "Type"));
        gs.setMap(2, new StandardMap("Map", "Type"));

        JFrameController frame = new StandardJFrameController();
        frame.addGameState(gs);
    }
}
