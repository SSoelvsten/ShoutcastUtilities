package JFrameControllers;

import GameState.ModifiableGameState;

import javax.swing.*;
import java.awt.*;

public class StandardJFrameController implements JFrameController {

    //TODO: Clock will need a prototype

    JFrame frame;

    int nameTextFieldSize;
    int scoreTextFieldSize;

    public StandardJFrameController(int nameTextFieldSize, int scoreTextFieldSize){
        this.nameTextFieldSize = nameTextFieldSize;
        this.scoreTextFieldSize = scoreTextFieldSize;

        frame = new JFrame();
        frame.setLayout(new BorderLayout());

        //NORTH is currently reserved to settings.
        //CENTER is for the game state
        //SOUTH is reserved to clock

        frame.setTitle("ShoutcastUtilities");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void addGameState(ModifiableGameState gameState) {
        //This will only manage one game state. So the old will
        //just be replaced with a new one

        //TODO; Create panels without
        //      Solution: Create an object in between?

        frame.pack();
    }
}
