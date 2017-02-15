package JFrameControllers;

import GameState.*;
import GameStateObserver.GameStateObserver;
import com.sun.media.sound.InvalidFormatException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * A GUI controller for the ShoutCast Utilities.
 */
public class StandardJFrameController implements JFrameController {

    //TODO: Clock will need a prototype

    //TODO: "Add Team" and "Add Map" buttons

    JFrame frame;
    ArrayList<JPanelTeamMapController> teamPanels;
    ArrayList<JPanelTeamMapController> mapPanels;

    public StandardJFrameController(){

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

        JPanel gameStatePanel = new JPanel(new BorderLayout());

        JPanel teamControllersPanel = new JPanel(new GridLayout(gameState.getTeamsAmount(), 1));

        teamPanels = new ArrayList<>();
        for(int i = 0; i < gameState.getTeamsAmount(); i++){
            JPanelTeamController tcPanel = new JPanelTeamController(i, gameState);
            teamPanels.add(tcPanel);
            teamControllersPanel.add(tcPanel.getPanel());
            tcPanel.onNameUpdate(gameState);
        }

        JPanel meta = new JPanel(new BorderLayout());

        JButton shiftButt = new JButton("Shift teams");
        shiftButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameState.shiftTeams();
            }
        });

        JButton commitTeam = new JButton("Commit Teams");
        commitTeam.addActionListener(createCommitActionListener(teamPanels));

        JPanel mapControllerPanel = new JPanel(new GridLayout(gameState.getMapsList().size(), 1));

        mapPanels = new ArrayList<>();
        int i = 0;
        for(Map m : gameState.getMapsList()){
            JPanelMapController mpc = new JPanelMapController(i, gameState);
            i++;

            mapPanels.add(mpc);
            mapControllerPanel.add(mpc.getPanel());
            mpc.onMapUpdate(gameState);
        }

        JButton addMap = new JButton("Commit Maps");
        addMap.addActionListener(createCommitActionListener(mapPanels));

        JPanel seriesPanel = new JPanel();
        seriesPanel.add(new JLabel("Best of"));
        JTextField seriesField = new JTextField(4);
        seriesPanel.add(seriesField);
        JButton seriesCommit = new JButton("Commit");
        seriesCommit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int length = Integer.parseInt(seriesField.getText());
                    gameState.setSeriesLength(length);
                } catch(Exception exception) {
                    seriesField.setText(0 + "");
                }
            }
        });
        seriesPanel.add(seriesCommit);

        JPanel pausePanel = new JPanel();
        pausePanel.add(new JLabel("Team ID: "));
        JTextField pauseIDField = new JTextField(3);
        pausePanel.add(pauseIDField);
        pausePanel.add(new JLabel("Reason: "));
        JTextField pauseReasonField = new JTextField(9);
        pausePanel.add(pauseReasonField);
        JButton unpauseButt = new JButton("Unpause");
        unpauseButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameState.unpause();
            }
        });
        pausePanel.add(unpauseButt);
        JButton pauseButt = new JButton("Pause");
        pauseButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int id = Integer.parseInt(pauseIDField.getText());
                    if(id < gameState.getTeamsAmount()){
                        String reason = null;
                        if(!pauseReasonField.getText().equals("")){
                            reason = pauseReasonField.getText();
                        }
                        gameState.setPauseTeam(id, reason);
                    }
                } catch (Exception exception) {
                    pauseIDField.setText(0 + "");
                }
            }
        });
        pausePanel.add(pauseButt);

        meta.add(seriesPanel, BorderLayout.NORTH);
        meta.add(shiftButt, BorderLayout.CENTER);
        meta.add(commitTeam, BorderLayout.EAST);
        meta.add(addMap, BorderLayout.WEST);
        meta.add(pausePanel, BorderLayout.SOUTH);

        gameStatePanel.add(teamControllersPanel, BorderLayout.NORTH);
        gameStatePanel.add(meta, BorderLayout.CENTER);
        gameStatePanel.add(mapControllerPanel, BorderLayout.SOUTH);

        frame.add(gameStatePanel, BorderLayout.CENTER);
        frame.pack();
    }

    private ActionListener createCommitActionListener(ArrayList<JPanelTeamMapController> list){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JPanelTeamMapController c : list)
                    c.listenToGameState(false);

                for(JPanelTeamMapController c : list)
                    c.commitInfo();

                for(JPanelTeamMapController c : list)
                    c.listenToGameState(true);
            }
        };
    }
}
