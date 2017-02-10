package Controller;

import GameState.GameState;

public interface GameStateController {

    /**
     * Print out the Game State to the files
     */
    public void printFiles();

    /**
     * Get the 'state of the game'
     * @return The GameState for this controller
     */
    public GameState getGameState();

    //Score manager
    public void resetScore();

    public void incrementTeamAScore();
    public void decrementTeamAScore();

    public void incrementTeamBScore();
    public void decrementTeamBScore();

    //Pausing
    public void pauseTeamA(String reason);
    public void pauseTeamB(String reason);
    public void unpause();

    //Other
    public void swapTeams();

    public void setTeamIdentities(String nameA, String abbreviationA,
                                  String nameB, String abbreviationB);
}
