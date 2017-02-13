package GameState;

public class StandardMap implements Map {

    String name;
    String gameType;

    public StandardMap(String name, String gameType){
        this.name = name;
        this.gameType = gameType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGameType() {
        return gameType;
    }
}
