package ikslorin.TeamManager;

import Config.Config;
import ikslorin.TXTManager;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates a window to input and quickly change two teams A and B
 */
public class TeamManager {
    //CFGConfig
    private Config conf;

    //Teams A and B
    private Team teamA;
    private Team teamB;

    //The panels for the teams A and B
    private JTextField teamNameA;
    private JTextField teamTagA;
    private JTextField teamScoreA;

    private JTextField teamNameB;
    private JTextField teamTagB;
    private JTextField teamScoreB;

    private JTextField gamesInSeries;

    /**
     * Constructs the team objects and the window
     */
    public TeamManager(Team teamA, Team teamB, int teamSize, int scoreSize) {
        this.teamA = teamA;
        this.teamB = teamB;

        this.conf = Config.getInstance();

        createWindow(teamSize, scoreSize);
    }

    /**
     * Applies the current text in the fields to the teams
     */
    public void updateTeams(){
        //Set teamnames
        teamA.setName(teamNameA.getText());
        teamB.setName(teamNameB.getText());

        teamA.setTag(teamTagA.getText());
        teamB.setTag(teamTagB.getText());

        //Set team score
        try{
            teamA.setScore(Integer.parseInt(teamScoreA.getText()));
            teamB.setScore(Integer.parseInt(teamScoreB.getText()));
        } catch(NumberFormatException e) {
            teamA.setScore(0);
            teamB.setScore(0);
        }

        //Update the BO X .txt file
        int game = teamA.getScore() + teamB.getScore() + 1;
        try{
            int bo = Integer.parseInt(gamesInSeries.getText());
            if(bo == 0){
                TXTManager.writeFullFile(conf.getString("file_game_number"), "Game " + game);
            } else {
                TXTManager.writeFullFile(conf.getString("file_game_number"), "Game " + game + " of " + bo);
            }
        } catch(NumberFormatException e) {
            TXTManager.writeFullFile(conf.getString("file_game_number"), "Game " + game);
        }

        //Update the victor .txt file
        if(teamA.getScore() < teamB.getScore()){
            TXTManager.writeFullFile(conf.getString("file_victor"), teamB.getName());
        } else if(teamA.getScore() == teamB.getScore()){
            TXTManager.writeFullFile(conf.getString("file_victor"), "draw");
        } else {
            TXTManager.writeFullFile(conf.getString("file_victor"), teamA.getName());
        }
    }

    /**
     * Resets the content to the last accepted value saved in the Team objects
     */
    public void reloadTeams() {
        teamNameA.setText(teamA.getName());
        teamTagA.setText(teamA.getTag());
        teamScoreA.setText("" + teamA.getScore());

        teamNameB.setText(teamB.getName());
        teamTagB.setText(teamB.getTag());
        teamScoreB.setText("" + teamB.getScore());
    }

    /**
     * Swaps the name, tag and score fields respectively between Team A and B
     */
    public void swapTeams() {
        //Save everything from A
        String aName = teamNameA.getText();
        String aTag = teamTagA.getText();
        String aScore = teamScoreA.getText();

        //Overwrite A with content from B
        teamNameA.setText(teamNameB.getText());
        teamTagA.setText(teamTagB.getText());
        teamScoreA.setText(teamScoreB.getText());

        //Use the backup to insert A onto B
        teamNameB.setText(aName);
        teamTagB.setText(aTag);
        teamScoreB.setText(aScore);
    }

    public Team getTeamA(){ return teamA; }

    public Team getTeamB(){ return teamB; }

    /**
     * Creates the window
     * @param teamSize The size of the name and tag fields
     * @param scoreSize The size of the score field
     */
    private void createWindow(int teamSize, int scoreSize){
        //Create the textfields
        teamNameA = new JTextField(teamSize);
        teamNameA.setEditable(true);

        teamTagA = new JTextField(teamSize);
        teamTagA.setEditable(true);

        teamScoreA = new JTextField(scoreSize);
        teamScoreA.setEditable(true);

        teamNameB = new JTextField(teamSize);
        teamNameB.setEditable(true);

        teamTagB = new JTextField(teamSize);
        teamTagB.setEditable(true);

        teamScoreB = new JTextField(scoreSize);
        teamScoreB.setEditable(true);

        reloadTeams();

        //Create the textpanel
        JPanel panelA = createTeamPanel(teamA, teamNameA, teamTagA, teamScoreA);
        JPanel panelB = createTeamPanel(teamB, teamNameB, teamTagB, teamScoreB);

        //Create the lower panel
        JPanel panelM = createLowerPanel();

        //Put everything into the final frame
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout(3, 3));

        frame.add(panelA, BorderLayout.WEST);
        frame.add(panelB, BorderLayout.EAST);
        frame.add(panelM, BorderLayout.SOUTH);

        //Give the window a title and an icon (though the latter doesn't want to work
        frame.setTitle("Team Manager");
        frame.setIconImage(new ImageIcon("icon.png").getImage());

        //Usual stuff
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Constructs a panel to write changes for a single team. Notice, that the textfields are global,
     * so they are not constructed here, but have to be arguments.
     * @param team The team to change
     * @param teamName The corresponding textfield to interact with
     * @param teamTag The corresponding textfield to interact with
     * @param teamScore The corresponding textfield to interact witw
     * @return A JPanel with inpuht for a single team
     */
    private JPanel createTeamPanel(Team team, JTextField teamName, JTextField teamTag, JTextField teamScore) {
        //Create teamName panel
        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Name: "));
        namePanel.add(teamName);

        //Create teamTag panel
        JPanel tagPanel = new JPanel();
        tagPanel.add(new JLabel("    Tag: "));

        tagPanel.add(teamTag);

        //Insert this into one panel beneath eachother
        JPanel namingPanel = new JPanel();
        namingPanel.setLayout(new BorderLayout());

        namingPanel.add(namePanel, BorderLayout.NORTH);
        namingPanel.add(tagPanel, BorderLayout.SOUTH);

        //Incrementing buttons
        JButton ButtScoreInc = createScoreIncrementer(teamScore, 1);
        JButton ButtScoreDec = createScoreIncrementer(teamScore, -1);

        JPanel scorePanel = new JPanel();
        scorePanel.add(new JLabel("Score: "));
        scorePanel.add(teamScore);
        scorePanel.add(ButtScoreDec);
        scorePanel.add(ButtScoreInc);

        //Let's collect all this together in one big panel!
        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new BorderLayout(2, 2));

        finalPanel.add(namingPanel, BorderLayout.CENTER);
        finalPanel.add(scorePanel, BorderLayout.SOUTH);

        finalPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        return finalPanel;
    }

    /**
     * Create a panel consisting of metakeys and pause
     * @return A JPanel
     */
    private JPanel createLowerPanel() {
        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new BorderLayout());

        finalPanel.add(createScoreMetaPanel(), BorderLayout.NORTH);
        finalPanel.add(createPausePanel(), BorderLayout.CENTER);

        return finalPanel;
    }

    /**
     * Constructs a panel with the buttons "Swap" "Apply" and "Reload" and the pause panel below
     * @return A JPanel consisting of 3 buttons
     */
    private JPanel createScoreMetaPanel() {
        //Create BO X Field
        gamesInSeries = new JTextField(3);
        gamesInSeries.setText("0");

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        topPanel.add(new JLabel("Games in series: ", SwingConstants.RIGHT), BorderLayout.WEST);
        topPanel.add(gamesInSeries, BorderLayout.CENTER);

        //Create update button
        JButton updateButt = new JButton("Update .txt files");
        updateButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updateTeams();
            }
        });

        //Create swap button
        JButton swapButt = new JButton("Swap teams");
        swapButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                swapTeams();

                //The swap button could also immediately update, but this seems more safe in a life setting
                //updateTeams();
            }
        });

        JButton reloadButt = new JButton("Reload teams");
        reloadButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadTeams();
            }
        });

        //Create the final panel and add everything to it
        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new BorderLayout());

        finalPanel.add(topPanel, BorderLayout.NORTH);

        finalPanel.add(swapButt, BorderLayout.WEST);
        finalPanel.add(updateButt, BorderLayout.CENTER);
        finalPanel.add(reloadButt, BorderLayout.EAST);

        finalPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        return finalPanel;
    }

    private JPanel createPausePanel(){
        JTextField reasonField = new JTextField(24);

        JButton aButt = new JButton("Pause: Team A");
        aButt.addActionListener(createPauseActionListener(teamA, reasonField));

        JButton bButt = new JButton("Pause: Team B");
        bButt.addActionListener(createPauseActionListener(teamB, reasonField));

        JButton resetButt = new JButton("No Pause");
        resetButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TXTManager.writeFullFile(conf.getString("file_pause"), "");
            }
        });

        //Always empty the pause file, when starting the software
        resetButt.doClick();

        //Create a panel with "Pause Reason: " tag and jlabel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        topPanel.add(new JLabel("Pause Notification: ", SwingConstants.RIGHT), BorderLayout.WEST);
        topPanel.add(reasonField, BorderLayout.EAST);

        //Create the final panel and add everything to it
        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new BorderLayout());

        finalPanel.add(topPanel, BorderLayout.NORTH);
        finalPanel.add(aButt, BorderLayout.WEST);
        finalPanel.add(resetButt, BorderLayout.CENTER);
        finalPanel.add(bButt, BorderLayout.EAST);

        finalPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        return finalPanel;
    }

    /**
     * Increments the value in the textfield by a specific value or resets it to 0, if it
     * doesn't contain integers.
     * @param teamScore The field containing the score
     * @param inc The incrementation (negative values allowed)
     * @return An actionlistener to be attached to a button
     */
    private JButton createScoreIncrementer(JTextField teamScore, int inc){
        JButton result = new JButton("" + inc);
        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int value = 0;
                try{
                    value = Integer.parseInt(teamScore.getText());
                    value += inc;
                } catch(NumberFormatException e) {
                    System.err.println("There was a noninteger in the teamScore field");
                    // Do nothing, this only resets the score then to 0
                }
                teamScore.setText("" + value);
            }
        });
        return result;
    }

    /**
     * Creates an ActionListener for the two pause buttons
     * @param team The team to write the pause
     * @param reasonField The reasonfield found in createPausePanel
     * @return
     */
    private ActionListener createPauseActionListener(Team team, JTextField reasonField){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                team.makePaused(reasonField.getText());
            }
        };
    }
}
