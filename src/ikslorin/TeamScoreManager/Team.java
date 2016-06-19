package ikslorin.TeamScoreManager;

import ikslorin.TXTManager;

/**
 * A class to keep track of the names of all
 */
public class Team {

    // The files to read and write
    private String namefile;
    private String tagfile;
    private String scorefile;

    // Full name of team
    private String name;

    // Abbreviation or tag
    private String tag;

    //Current score
    private int score;

    public Team(String namefile, String tagfile, String scorefile){
        this.namefile = namefile;
        this.tagfile = tagfile;
        this.scorefile = scorefile;

        name = namefile;
        tag = tagfile;
        score = 0;
    }

    public String getName(){
        return name;
    }

    /**
     * Sets the name parametre and updates the corresponding file
     * @param name The new name
     */
    public void setName(String name){
        this.name = name;
        TXTManager.writeFullFile(namefile, name);
    }

    public String getTag() { return tag; }

    /**
     * Sets the tag parametre and updates the corresponding file
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag;
        TXTManager.writeFullFile(tagfile, tag);
    }

    public int getScore() { return score; }

    /**
     * Sets the score parametre and updates the corresponding file
     * @param score
     */
    public void setScore(int score){
        this.score = score;
        TXTManager.writeFullFile(scorefile, "" + score);
    }

    public void increaseScore(int i) {
        setScore(this.score + i);
    }
}
