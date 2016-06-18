package ikslorin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates a window to input and quickly change two teams A and B
 */
public class ScoreManager {
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

    /**
     * Constructs the team objects and the window
     */
    public ScoreManager(Team teamA, Team teamB, int teamSize, int scoreSize) {
        this.teamA = teamA;
        this.teamB = teamB;

        createWindow(teamSize, scoreSize);
    }

    /**
     * Applies the current text in the fields to the teams
     */
    public void updateTeams(){
        teamA.setName(teamNameA.getText());
        teamB.setName(teamNameB.getText());

        teamA.setTag(teamTagA.getText());
        teamB.setTag(teamTagB.getText());

        try{
            teamA.setScore(Integer.parseInt(teamScoreA.getText()));
            teamB.setScore(Integer.parseInt(teamScoreB.getText()));
        } catch(NumberFormatException e) {
            teamA.setScore(0);
            teamB.setScore(0);
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

        //Create the special buttonPanel
        JPanel panelG = createLowerPanel();

        //Put everything into the final frame
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout(3, 3));

        frame.add(panelA, BorderLayout.WEST);
        frame.add(panelB, BorderLayout.EAST);
        frame.add(panelG, BorderLayout.SOUTH);

        //Give the window a title and an icon (though the latter doesn't want to work
        frame.setTitle("Team Score Manager");
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

        finalPanel.add(new JLabel("Team: " + team.getIdentifier(), SwingConstants.CENTER), BorderLayout.NORTH);

        finalPanel.add(namingPanel, BorderLayout.CENTER);
        finalPanel.add(scorePanel, BorderLayout.SOUTH);

        return finalPanel;
    }

    /**
     * Constructs a panel with the buttons "Swap" "Apply" and "Reload"
     * @return A JPanel consisting of 3 buttons
     */
    private JPanel createLowerPanel() {
        //Create update button
        JButton updateButt = new JButton("Update");
        updateButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updateTeams();
            }
        });

        //Create swap button
        JButton swapButt = new JButton("Swap");
        swapButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                swapTeams();

                //The swap button could also immediately update, but this seems more safe in a life setting
                //updateTeams();
            }
        });

        JButton reloadButt = new JButton("Reload");
        reloadButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadTeams();
            }
        });

        //Create the final panel and add everything to it
        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new BorderLayout());

        finalPanel.add(swapButt, BorderLayout.WEST);
        finalPanel.add(updateButt, BorderLayout.CENTER);
        finalPanel.add(reloadButt, BorderLayout.EAST);

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
}
