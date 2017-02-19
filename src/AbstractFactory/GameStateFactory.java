package AbstractFactory;

import GameState.*;

/**
 * A factory to create all the stuff for a gameState
 */
public interface GameStateFactory {

    public ModifiableGameState createGameState();

    public ModifiableTeam createTeam(String name, String abbreviation, int score);

    public Map createMap(String mapName, String gameType, String note);
}
