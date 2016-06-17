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
    Team teamA;
    Team teamB;

    //The panels for the teams A and B
    JTextField teamNameA;
    JTextField teamTagA;
    JTextField teamScoreA;

    JTextField teamNameB;
    JTextField teamTagB;
    JTextField teamScoreB;

    /**
     * Constructs the team objects and the window
     */
    public TeamManager() {
        //Create the two teams
        teamA = new Team("A");
        teamB = new Team("B");

        //Create the window
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout(3, 3));

        //Create the panel for Team A
        teamNameA = new JTextField(32);
        teamNameA.setText("Team A");

        teamTagA = new JTextField(32);
        teamTagA.setText("A");

        teamScoreA = new JTextField(8);
        teamScoreA.setText("0");

        JPanel panelA = createTeamPanel(teamA, teamNameA, teamTagA, teamScoreA);

        //Create the panel for Team B
        teamNameB = new JTextField(32);
        teamNameB.setText("Team B");

        teamTagB = new JTextField(32);
        teamTagB.setText("B");

        teamScoreB = new JTextField(8);
        teamScoreB.setText("0");

        JPanel panelB = createTeamPanel(teamB, teamNameB, teamTagB, teamScoreB);

        //Create the special buttonPanel
        JPanel panelG = createGlobalPanel();

        //Put everything into the final frame
        frame.add(new JLabel("-==| Live Team Manager |==-"), BorderLayout.NORTH)
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
        teamName = new JTextField(32);
        teamName.setEditable(true);

        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Name: "));
        namePanel.add(teamName);

        //Create teamTag panel
        teamTag = new JTextField(32);
        teamTag.setEditable(true);

        JPanel tagPanel = new JPanel();
        tagPanel.add(new JLabel("Tag: "));
        tagPanel.add(teamTag);

        //Insert this into one panel beneath eachother
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        leftPanel.add(namePanel, BorderLayout.NORTH);
        leftPanel.add(namePanel, BorderLayout.SOUTH);

        //The top of the score panel containing the current score
        teamScore = new JTextField(8);

        JPanel scorePanel = new JPanel();
        scorePanel.add(new JLabel("Score: "));
        scorePanel.add(teamScore);

        //Incrementing buttons
        JButton ButtScoreInc = createScoreIncrementer(teamScore, 1);
        JButton ButtScoreDec = createScoreIncrementer(teamScore, -1);

        //Add everything to the right side for the full score panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        rightPanel.add(scorePanel, BorderLayout.NORTH);
        rightPanel.add(ButtScoreDec, BorderLayout.WEST);
        rightPanel.add(ButtScoreInc, BorderLayout.EAST);

        //Let's collect all this together in one big panel!
        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new BorderLayout(2, 2));

        finalPanel.add(new JLabel("-- Team: " + team.getIdentifier() + " --"), BorderLayout.NORTH);

        finalPanel.add(leftPanel, BorderLayout.WEST);
        finalPanel.add(rightPanel, BorderLayout.EAST);

        return finalPanel;
    }

    private JPanel createGlobalPanel() {
        //Create update button
        JButton updateButt = new JButton("Update");
        updateButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTeams();
            }
        });

        //Create swap button
        JButton swapButt = new JButton("Swap");
        swapButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                //The swap button could also imidiatly update, but this seems more safe in a life setting
                //updateTeams();
            }
        });

        //Create the final panel and add everything to it
        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new BorderLayout());

        finalPanel.add(new JLabel("-- Global --", BorderLayout.NORTH);

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
            public void actionPerformed(ActionEvent e) {
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
