package Factory;

import AbstractFactory.GameStateFactory;
import GameState.*;

public class GameStateFactoryStub implements GameStateFactory {

    public ModifiableGameState gameState;
    public ModifiableTeam team;
    public Map map;

    @Override
    public ModifiableGameState createGameState() {
        return gameState;
    }

    @Override
    public ModifiableTeam createTeam(String name, String abbreviation, int score) {
        return team;
    }

    @Override
    public Map createMap(String mapName, String gameType, String note) {
        return map;
    }
}
