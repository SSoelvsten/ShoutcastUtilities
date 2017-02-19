package JFrameControllers;

import GameState.*;
import Observer.AbstractGameStateObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An object to create and manage a JPanel controlling one team of a GameState
 */
public class JPanelTeamController extends AbstractGameStateObserver implements JPanelTeamMapController {

    private int teamIndex;
    private GameStateController gameStateController;

    private int nameLength = 10;
    private int abbreviavtionLength = 4;
    private int scoreLength = 1;

    private JPanel panel = new JPanel();

    private JTextField nameTextField = new JTextField(nameLength);
    private JTextField abbreviationTextField = new JTextField(abbreviavtionLength);
    private JTextField scoreTextField = new JTextField(scoreLength);

    private boolean listen = true;

    public JPanelTeamController(int teamIndex, GameStateController gameStateController){
        this.teamIndex = teamIndex;
        this.gameStateController = gameStateController;
        gameStateController.subscribe(this);

        nameTextField.setEditable(true);
        abbreviationTextField.setEditable(true);
        scoreTextField.setEditable(false);

        panel.add(new JLabel("Team " + teamIndex + " :"));

        panel.add(new JLabel("Name"));
        panel.add(nameTextField);

        panel.add(new JLabel("Abbr."));
        panel.add(abbreviationTextField);

        panel.add(createScoreButton(-1));
        panel.add(scoreTextField);
        panel.add(createScoreButton(1));
        scoreTextField.setText(gameStateController.getGameState().getTeam(teamIndex).getPoints() + "");
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
        gameStateController.setTeamIdentity(teamIndex, nameTextField.getText(), abbreviationTextField.getText());
    }

    private JButton createScoreButton(int change){
        JButton button = new JButton("" + change);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameStateController.changeTeamScoreBy(teamIndex, change);
            }
        });

        return button;
    }

    @Override
    public void onShiftUpdate(GameState gameState) {
        onNameUpdate(gameState);
        onScoreUpdate(gameState);
    }

    @Override
    public void onNameUpdate(GameState gameState) {
        if(listen){
            nameTextField.setText(gameState.getTeam(teamIndex).getName());
            abbreviationTextField.setText(gameState.getTeam(teamIndex).getAbbreviation());
        }
    }

    @Override
    public void onScoreUpdate(GameState gameState) {
        if(listen)
            scoreTextField.setText(gameState.getTeam(teamIndex).getPoints() + "");
    }
}
