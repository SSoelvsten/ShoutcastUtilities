package JFrameControllers;

import GameState.*;
import Observer.*;

import javax.swing.*;

public class JPanelMapController extends AbstractGameStateObserver implements JPanelTeamMapController {

    private int mapIndex;
    private ModifiableGameState gameState;

    private int nameLength = 12;
    private int gameTypeLength = 6;

    private JPanel panel = new JPanel();

    private JTextField nameTextField = new JTextField(nameLength);
    private JTextField gameTypeTextField = new JTextField(gameTypeLength);
    private JTextField noteTextField = new JTextField(gameTypeLength);

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
        panel.add(new JLabel("Note"));
        panel.add(noteTextField);
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
                                gameTypeTextField.getText(),
                                noteTextField.getText()));
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
