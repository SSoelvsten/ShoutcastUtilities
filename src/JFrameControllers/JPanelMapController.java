package JFrameControllers;

import GameState.*;
import GameStateObserver.*;

import javax.swing.*;

public class JPanelMapController extends AbstractGameStateObserver implements JPanelTeamMapController {

    private int mapIndex;
    private ModifiableGameState gameState;

    private int nameLength = 8;
    private int gameTypeLength = 4;

    private JPanel panel = new JPanel();

    private JTextField nameTextField = new JTextField(nameLength);
    private JTextField gameTypeTextField = new JTextField(gameTypeLength);

    private boolean listen = true;

    public JPanelMapController(int mapIndex, ModifiableGameState gameState){
        this.mapIndex = mapIndex;
        this.gameState = gameState;
        gameState.subscribe(this);

        nameTextField.setEditable(true);
        gameTypeTextField.setEditable(true);

        panel.add(new JLabel("Map " + mapIndex + ":"));
        panel.add(nameTextField);
        panel.add(new JLabel("Game type"));
        panel.add(gameTypeTextField);

    }

    public JPanel getPanel(){
        return panel;
    }

    @Override
    public void listenToGameState(boolean value) {
        listen = value;
    }

    @Override
    public void commitInfo(){
        gameState.setMap(mapIndex,
                new StandardMap(nameTextField.getText(),
                                gameTypeTextField.getText()));
    }

    @Override
    public void onMapUpdate(GameState gameState) {
        if(listen){
            Map map = gameState.getMap(mapIndex);
            nameTextField.setText(map.getName());
            gameTypeTextField.setText(map.getGameType());
        }
    }
}
