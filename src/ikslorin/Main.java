package ikslorin;

public class Main {

    public static void main(String[] args) {
        //Create the two teams to manage
        Team tA = new Team("A");
        Team tB = new Team("B");
        setupTeams(tA, tB);

        // Start score manager
        ScoreManager sm = new ScoreManager(tA, tB, 12, 2);
    }

    /**
     * Loads in the values from the last session
     * @param tA Team A
     * @param tB Team B
     */
    private static void setupTeams(Team tA, Team tB){
        tA.setName(txtWriter.read("A_name.txt"));
        tB.setName(txtWriter.read("B_name.txt"));

        tA.setTag(txtWriter.read("A_tag.txt"));
        tB.setTag(txtWriter.read("B_tag.txt"));

        try{
            tA.setScore(Integer.parseInt(txtWriter.read("A_score.txt")));
            tB.setScore(Integer.parseInt(txtWriter.read("B_score.txt")));
        } catch(NumberFormatException e) {
            System.err.println("There was a noninteger in the teamScore field");
            tA.setScore(0);
            tB.setScore(0);
        }
    }
}
