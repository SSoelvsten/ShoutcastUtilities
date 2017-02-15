package JFrameControllers;

import GameState.*;
import GameStateObserver.GameStateObserver;

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
    ArrayList<JPanelTeamController> teamPanels;
    ArrayList<JPanelMapController> mapPanels;

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
        commitTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JPanelTeamController tc : teamPanels) {
                    tc.commitNameFields();
                }
            }
        });

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
        addMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JPanelMapController mc : mapPanels){
                    mc.commitMapInfo();
                }
            }
        });

        meta.add(shiftButt, BorderLayout.CENTER);
        meta.add(commitTeam, BorderLayout.EAST);
        meta.add(addMap, BorderLayout.WEST);

        gameStatePanel.add(teamControllersPanel, BorderLayout.NORTH);
        gameStatePanel.add(meta, BorderLayout.CENTER);
        gameStatePanel.add(mapControllerPanel, BorderLayout.SOUTH);

        frame.add(gameStatePanel, BorderLayout.CENTER);
        frame.pack();
    }
}
