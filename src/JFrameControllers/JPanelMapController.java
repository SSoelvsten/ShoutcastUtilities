package JFrameControllers;

import GameState.*;
import GameStateObserver.*;

import javax.swing.*;

public class JPanelMapController extends AbstractGameStateObserver {

    private int mapIndex;
    private ModifiableGameState gameState;

    private int nameLength = 8;
    private int gameTypeLength = 4;

    private JPanel panel = new JPanel();

    private JTextField nameTextField = new JTextField(nameLength);
    private JTextField gameTypeTextField = new JTextField(gameTypeLength);

    public JPanelMapController(int mapIndex, ModifiableGameState gameState){
        this.mapIndex = mapIndex;
        this.gameState = gameState;
        gameState.subscribe(this);

        nameTextField.setEditable(true);
        gameTypeTextField.setEditable(true);

        panel.add(new JLabel("Map"));
        panel.add(nameTextField);
        panel.add(new JLabel("Game type"));
        panel.add(gameTypeTextField);

    }

    public JPanel getPanel(){
        return panel;
    }

    public void commitMapInfo(){
        gameState.setMap(mapIndex,
                new StandardMap(nameTextField.getText(),
                                gameTypeTextField.getText()));
    }

    @Override
    public void onMapUpdate(GameState gameState) {
        Map map = gameState.getMap(mapIndex);
        nameTextField.setText(map.getName());
        gameTypeTextField.setText(map.getGameType());
    }
}
