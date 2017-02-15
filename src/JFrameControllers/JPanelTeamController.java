package JFrameControllers;

import GameState.*;
import GameStateObserver.AbstractGameStateObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An object to create and manage a JPanel controlling one team of a GameState
 */
public class JPanelTeamController extends AbstractGameStateObserver {

    private int teamIndex;
    private ModifiableGameState gameState;

    private int nameLength = 16;
    private int abbreviavtionLength = 6;
    private int scoreLength = 1;

    private JPanel panel = new JPanel();

    private JTextField nameTextField = new JTextField(nameLength);
    private JTextField abbreviationTextField = new JTextField(abbreviavtionLength);
    private JTextField scoreTextField = new JTextField(scoreLength);

    public JPanelTeamController(int teamIndex, ModifiableGameState gameState){
        this.teamIndex = teamIndex;
        this.gameState = gameState;
        gameState.subscribe(this);

        nameTextField.setEditable(true);
        abbreviationTextField.setEditable(true);
        scoreTextField.setEditable(false);

        panel.add(new JLabel("Name"));
        panel.add(nameTextField);

        panel.add(new JLabel("Abbr."));
        panel.add(abbreviationTextField);

        panel.add(createScoreButton(-1));
        panel.add(scoreTextField);
        panel.add(createScoreButton(1));
    }

    public JPanel getPanel(){
        return panel;
    }

    public void commitNameFields(){
        gameState.setTeamIdentity(teamIndex, nameTextField.getText(), abbreviationTextField.getText());
    }

    private JButton createScoreButton(int change){
        JButton button = new JButton("" + change);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currScore = gameState.getTeam(teamIndex).getPoints();

                if((change < 0 && currScore > 0)
                        || change >= 0){
                    gameState.setTeamPoints(teamIndex, currScore + change);
                }
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
        nameTextField.setText(gameState.getTeam(teamIndex).getName());
        abbreviationTextField.setText(gameState.getTeam(teamIndex).getAbbreviation());
    }

    @Override
    public void onScoreUpdate(GameState gameState) {
        scoreTextField.setText(gameState.getTeam(teamIndex).getPoints() + "");
    }
}
