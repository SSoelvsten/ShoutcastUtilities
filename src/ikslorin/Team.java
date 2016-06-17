package ikslorin;

/**
 * A class to keep track of the names of all
 */
public class Team {

    // The txtWriter to use
    private txtWriter writer;

    // The identifier to use
    private String identifier;

    // Full name of team
    private String name;

    // Abbreviation or tag
    private String tag;

    //Current score
    private int score;

    public Team(String identifier){
        this.identifier = identifier;
        this.writer = new txtWriter();
    }

    public Team(String identifier, txtWriter writer){
        this.identifier = identifier;
        this.writer = writer;
    }

    public String getIdentifier() { return identifier; }

    public String getName(){
        return name;
    }

    /**
     * Sets the name parametre and updates the corresponding file
     * @param name The new name
     */
    public void setName(String name){
        this.name = name;
        this.writer.write(identifier + "_name.txt", name);
    }

    public String getTag() { return tag; }

    /**
     * Sets the tag parametre and updates the corresponding file
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag;
        this.writer.write(identifier + "_tag.txt", tag);
    }

    public int getScore() { return score; }

    /**
     * Sets the score parametre and updates the corresponding file
     * @param score
     */
    public void setScore(int score){
        this.score = score;
        this.writer.write(identifier + "_score.txt", "" + score);
    }

    public void increaseScore() {
        setScore(this.score + 1);
    }

    public void decreaseScore() {
        setScore(this.score - 1);
    }
}
