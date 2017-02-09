package GameState;

/**
 * Ok, the team was maybe not that immutable as I suggested at first.
 */
public interface ModifiableTeam extends Team{

    /**
     * Set the name of the team
     */
    public void setName(String name);

    /**
     * Set the abbreviation (tag) of the team
     */
    public void setAbbreviation(String abbreviation);

    /**
     * Set the amount of points/score the team has
     */
    public void setPoints(int points);
}
