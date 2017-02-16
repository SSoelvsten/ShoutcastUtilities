package GameState;

public class StandardMap implements Map {

    String name;
    String gameType;
    String note;

    public StandardMap(String name, String gameType, String note){
        this.name = name;
        this.gameType = gameType;
        this.note = note;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGameType() {
        return gameType;
    }

    @Override
    public String getNote() {
        return note;
    }
}
