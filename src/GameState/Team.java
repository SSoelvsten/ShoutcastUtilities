package GameState;

/**
 * A team which is immutable
 */
public interface Team {

    /**
     * @return The name of the team
     */
    public String getName();

    /**
     * @return The abbreviation (tag) of the team
     */
    public String getAbbreviation();

    /**
     * @return The amount of points/score the team has
     */
    public int getPoints();
}
