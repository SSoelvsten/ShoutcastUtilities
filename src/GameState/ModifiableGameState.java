package GameState;

/**
 * Objects on which you also can change the current game state.
 */
public interface ModifiableGameState extends GameState {

    /**
     * Swaps the order of the (two) teams matched against each other.
     */
    public void swapTeams();

    /**
     * Sets the identifying values of a team to a different one.
     * This can be used, should this team be a different one
     * @param name The new name
     * @param abbreviation The new shorthand
     */
    public void setTeamAIdentity(String name, String abbreviation);

    public void setTeamBIdentity(String name, String abbreviation);

    /**
     * Sets the points for a team to a different value.
     * @param points The points to set it to
     */
    public void setTeamAPoints(int points);

    public void setTeamBPoints(int points);

    /**
     * Sets the game to be marked as paused.
     * @param reason The reason for the pause.
     *               Can be set to null
     */
    public void setPauseTeamA(String reason);

    public void setPauseTeamB(String reason);

    /**
     * Set the length of the series. 0 means no
     * precondition: length >= 0
     * @param length >= 0
     */
    public void setSeriesLength(int length);

    /**
     * Sets the Game State to unpaused
     */
    public void unpause();
}
