package JFrameControllers;

import GameState.*;
import GameStateObserver.AbstractGameStateObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An object to create and manage a JPanel controlling one team of a GameState
 */
public class JPanelTeamController extends AbstractGameStateObserver {

    private int teamIndex;
    private ModifiableGameState gameState;

    private int nameLength = 16;
    private int scoreLength = 1;

    private JPanel panel = new JPanel(new BorderLayout());

    private final JTextField nameTextField = new JTextField(nameLength);
    private final JTextField abbreviationTextField = new JTextField(nameLength);
    private final JTextField scoreTextField = new JTextField(scoreLength);

    public JPanelTeamController(int teamIndex, ModifiableGameState gameState){
        this.teamIndex = teamIndex;
        this.gameState = gameState;
        gameState.subscribe(this);

        nameTextField.setEditable(true);
        abbreviationTextField.setEditable(true);
        scoreTextField.setEditable(false);

        JPanel fieldPanel = new JPanel();
        fieldPanel.add(createNamePanel("Name", nameTextField));
        fieldPanel.add(createNamePanel("Abbr.", abbreviationTextField));

        fieldPanel.add(createScoreButton(-1));
        fieldPanel.add(scoreTextField);
        fieldPanel.add(createScoreButton(1));

        panel.add(fieldPanel, BorderLayout.NORTH);
    }

    public void commitNameFields(){
        gameState.setTeamIdentity(teamIndex, nameTextField.getText(), abbreviationTextField.getText());
    }

    private JPanel createNamePanel(String label, JTextField textField){
        JPanel panel = new JPanel();

        panel.add(new JLabel(label));
        panel.add(textField);

        return panel;
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
        nameTextField.setText(gameState.getTeam(teamIndex).getAbbreviation());
    }

    @Override
    public void onScoreUpdate(GameState gameState) {
        scoreTextField.setText(gameState.getTeam(teamIndex).getPoints() + "");
    }
}
