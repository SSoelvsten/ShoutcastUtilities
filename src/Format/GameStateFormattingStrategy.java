package Format;

import GameState.*;

/**
 * A strategy to format the information in a GameState into Strings
 */
public interface GameStateFormattingStrategy {

    public String teamName(int teamIndex, GameState gs);
    public String teamName(Team team);

    public String teamAbbreviation(int teamIndex, GameState gs);
    public String teamAbbreviation(Team team);

    public String teamScore(int teamIndex, GameState gs);
    public String teamScore(Team team);

    public String gameNumber(GameState gs);

    public String map(int mapIndex, GameState gs);
    public String map(Map map);

    public String winner(GameState gs);

    public String pause(GameState gs);
}
