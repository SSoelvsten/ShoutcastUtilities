package GameState;

/**
 * The standard implementaiton of a team
 */
public class StandardTeam implements ModifiableTeam{

    private String name;
    private String abbreviation;
    private int points;

    public StandardTeam(String name,
                        String abbreviation,
                        int points){
        this.name = name;
        this.abbreviation = abbreviation;
        this.points = points;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getAbbreviation() {
        return this.abbreviation;
    }

    @Override
    public int getPoints() {
        return this.points;
    }
}
