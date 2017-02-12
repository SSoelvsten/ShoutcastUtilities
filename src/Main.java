import GameState.StandardGameState;
import JFrameControllers.JFrameController;
import JFrameControllers.StandardJFrameController;

/**
 * Sets up the whole software
 */
public class Main {

    public static void main(String[] args){
        JFrameController frame = new StandardJFrameController(12, 2);
        frame.addGameState(new StandardGameState());
    }
}
