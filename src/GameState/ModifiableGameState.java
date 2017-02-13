package GameState;

/**
 * Objects on which you also can change the current game state.
 */
public interface ModifiableGameState extends GameState {

    /**
     * Add a new Team to the game state
     * @param team The team to be added
     * @return The identifying index for the team currently.
     *         This can change when the teams are 'shifted'
     */
    public int addTeam(ModifiableTeam team);

    /**
     * Remove a team from the game state
     * @param teamIndex The identifying index of the team
     *                  (in [0, n] for n teams)
     */
    public void removeTeam(int teamIndex);

    /**
     * Sets the identifying values of a team to a different one.
     * This can be used, should this team be a different one
     * @precondition: teamIndex is a valid index for a team
     * @param teamIndex The identifying index for a team
     *                  (in [0, n] for n teams)
     * @param name The new name
     * @param abbreviation The new shorthand
     */
    public void setTeamIdentity(int teamIndex, String name, String abbreviation);

    /**
     * Sets the points for a team to a different value.
     * @precondition: points >= 0
     *                teamIndex is a valid index for a team
     * @param teamIndex The identifying index for a team
     *                  (in [0, n] for n teams)
     * @param points The points to set it to
     */
    public void setTeamPoints(int teamIndex, int points);

    /**
     * Shifts the order of the teams matched against each other.
     */
    public void shiftTeams();

    /**
     * Sets the game to be marked as paused.
     * @param teamIndex The identifying index for a team
     *                  (in [0, n] for n teams)
     * @param reason The reason for the pause.
     *               Can be set to null
     */
    public void setPauseTeam(int teamIndex, String reason);

    /**
     * Set the length of the series. 0 means no
     * @precondition: length >= 0
     * @param length >= 0
     */
    public void setSeriesLength(int length);

    /**
     * Sets the Game State to unpaused
     */
    public void unpause();

    /**
     * Sets a map in the map list, overwriting any map currently at that number.
     * @param number The number in the series. Will overwrite any others at this index
     * @param map The map to be put in
     */
    public void setMap(int number, Map map);
}
