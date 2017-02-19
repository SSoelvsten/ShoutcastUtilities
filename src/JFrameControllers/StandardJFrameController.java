package JFrameControllers;

import GameState.*;
import GameState.Map;
import Time.ModifiableTimer;
import Time.NameCalcStrategyPair;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;
import java.util.List;

/**
 * A GUI controller for the ShoutCast Utilities.
 */
public class StandardJFrameController implements JFrameController {

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
    public void addGameState(GameStateController gameStateController) {
        //This will only manage one game state. So the old will
        //just be replaced with a new one

        GameState gameState = gameStateController.getGameState();

        JPanel gameStatePanel = new JPanel(new BorderLayout());

        JPanel teamControllersPanel = new JPanel(new GridLayout(gameState.getTeamsAmount(), 1));

        teamPanels = new ArrayList<>();
        for(int i = 0; i < gameState.getTeamsAmount(); i++){
            JPanelTeamController tc = new JPanelTeamController(i, gameStateController);
            teamPanels.add(tc);

            JPanel tcp = tc.getPanel();
            addBorder(tcp);
            teamControllersPanel.add(tc.getPanel());

            //Have it already be updated with the names
            tc.onNameUpdate(gameState);
        }

        JPanel meta = new JPanel(new BorderLayout());

        JButton shiftButt = new JButton("Shift teams");
        shiftButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameStateController.shiftTeams();
            }
        });

        JButton commitTeam = new JButton("Commit Teams");
        commitTeam.addActionListener(createCommitActionListener(teamPanels));

        JPanel mapControllerPanel = new JPanel(new GridLayout(gameState.getMapsList().size(), 1));

        mapPanels = new ArrayList<>();
        int i = 0;
        for(Map m : gameState.getMapsList()){
            JPanelMapController mc = new JPanelMapController(i, gameStateController);
            i++;

            mapPanels.add(mc);
            JPanel mcp = mc.getPanel();
            addBorder(mcp);
            mapControllerPanel.add(mcp);
            mc.onMapUpdate(gameState);
        }

        JPanel seriesPanel = new JPanel();
        seriesPanel.add(new JLabel("Best of"));
        JTextField seriesField = new JTextField(4);
        seriesField.setText(mapPanels.size() + "");
        seriesField.setEditable(false);
        seriesPanel.add(seriesField);

        JButton seriesCommit = new JButton("Commit Maps");
        seriesCommit.addActionListener(createCommitActionListener(mapPanels));
        seriesPanel.add(seriesCommit);

        JPanel pausePanel = new JPanel(new BorderLayout());

        JPanel pauseFieldsPanel = new JPanel(new FlowLayout());
        pauseFieldsPanel.add(new JLabel("Team ID: "));
        JTextField pauseIDField = new JTextField(3);
        pauseFieldsPanel.add(pauseIDField);
        pauseFieldsPanel.add(new JLabel("Pause Reason: "));
        JTextField pauseReasonField = new JTextField(16);
        pauseFieldsPanel.add(pauseReasonField);
        pauseFieldsPanel.add(pauseReasonField);

        pausePanel.add(pauseFieldsPanel, BorderLayout.NORTH);

        JButton unpauseButt = new JButton("Unpause");
        unpauseButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameStateController.unpause();
            }
        });
        pausePanel.add(unpauseButt, BorderLayout.WEST);
        JButton pauseButt = new JButton("Pause");
        pauseButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int id = Integer.parseInt(pauseIDField.getText());
                    String reason = null;
                    if(!pauseReasonField.getText().equals("")){
                        reason = pauseReasonField.getText();
                    }
                    gameStateController.startPause(id, reason);
                } catch (Exception exception) {
                    pauseIDField.setText(0 + "");
                }
            }
        });
        pausePanel.add(pauseButt, BorderLayout.EAST);

        JPanel metaButtons = new JPanel(new GridLayout(2,2));
        metaButtons.add(shiftButt);
        metaButtons.add(commitTeam);
        metaButtons.add(pauseButt);
        metaButtons.add(unpauseButt);

        meta.add(metaButtons, BorderLayout.NORTH);
        meta.add(pausePanel, BorderLayout.CENTER);
        meta.add(seriesPanel, BorderLayout.SOUTH);

        gameStatePanel.add(teamControllersPanel, BorderLayout.NORTH);
        gameStatePanel.add(meta, BorderLayout.CENTER);
        gameStatePanel.add(mapControllerPanel, BorderLayout.SOUTH);

        addBorder(gameStatePanel);

        frame.add(gameStatePanel, BorderLayout.CENTER);
        frame.pack();
    }

    public void addClock(ModifiableTimer timer,
                        List<NameCalcStrategyPair> calcStrats){
        JPanelTimerController tc = new JPanelTimerController(timer, calcStrats);

        JPanel tcp = tc.getPanel();
        addBorder(tcp);

        frame.add(tcp, BorderLayout.SOUTH);
        frame.pack();
    }

    public void addBorder(JPanel panel){
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
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
