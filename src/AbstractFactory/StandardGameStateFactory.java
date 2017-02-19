package AbstractFactory;

import GameState.*;

public class StandardGameStateFactory implements GameStateFactory {
    @Override
    public ModifiableGameState createGameState() {
        return new StandardGameState();
    }

    @Override
    public ModifiableTeam createTeam(String name, String abbreviation, int score) {
        return new StandardTeam(name, abbreviation, score);
    }

    @Override
    public Map createMap(String mapName, String gameType, String note) {
        return new StandardMap(mapName, gameType, note);
    }
}
