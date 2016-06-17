package ikslorin;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates a window to input and quickly change two teams A and B
 */
public class TeamManager {
    //Teams A and B
    private Team teamA;
    private Team teamB;

    //Size for the text fields
    private int teamSize;
    private int scoreSize;

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
    public TeamManager(int teamSize, int scoreSize) {
        //Create the two teams
        teamA = new Team("A");
        teamB = new Team("B");

        //Save the team sizes in the variables - this is only for not hardcoding sizes several times
        this.teamSize = teamSize;
        this.scoreSize = scoreSize;

        //Create the team panels
        teamNameA = new JTextField(teamSize);
        teamNameA.setText(txtWriter.read("A_name.txt"));
        teamNameA.setEditable(true);

        teamTagA = new JTextField(teamSize);
        teamTagA.setText(txtWriter.read("A_tag.txt"));
        teamTagA.setEditable(true);

        teamScoreA = new JTextField(scoreSize);
        teamScoreA.setText(txtWriter.read("A_score.txt"));
        teamScoreA.setEditable(true);

        JPanel panelA = createTeamPanel(teamA, teamNameA, teamTagA, teamScoreA);

        teamNameB = new JTextField(teamSize);
        teamNameB.setText(txtWriter.read("B_name.txt"));
        teamNameB.setEditable(true);

        teamTagB = new JTextField(teamSize);
        teamTagB.setText(txtWriter.read("B_tag.txt"));
        teamTagB.setEditable(true);

        teamScoreB = new JTextField(scoreSize);
        teamScoreB.setText(txtWriter.read("B_score.txt"));
        teamScoreB.setEditable(true);

        JPanel panelB = createTeamPanel(teamB, teamNameB, teamTagB, teamScoreB);

        //Create the special buttonPanel
        JPanel panelG = createGlobalPanel();

        //Put everything into the final frame
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout(3, 3));

        frame.add(new JLabel("|===========| Live Team Manager |===========|",
                SwingConstants.CENTER), BorderLayout.NORTH);
        frame.add(panelA, BorderLayout.WEST);
        frame.add(panelB, BorderLayout.EAST);
        frame.add(panelG, BorderLayout.SOUTH);

        //Usual stuff
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Constructs a panel for a team
     * @param team
     * @return
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

    private JPanel createGlobalPanel() {
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

        //Create the final panel and add everything to it
        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new BorderLayout());

        //finalPanel.add(new JLabel("Global", SwingConstants.CENTER), BorderLayout.NORTH);

        finalPanel.add(swapButt, BorderLayout.WEST);
        finalPanel.add(updateButt, BorderLayout.EAST);

        return finalPanel;
    }

    private void updateTeams(){
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

    private void swapTeams() {
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
