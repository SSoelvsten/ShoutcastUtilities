package Format;

import GameState.GameState;

/**
 * A strategy to format the information in a GameState into Strings
 */
public interface GameStateFormattingStrategy {

    public String teamAName(GameState gs);

    public String teamAAbbreviation(GameState gs);

    public String teamAScore(GameState gs);

    public String teamBName(GameState gs);

    public String teamBAbbreviation(GameState gs);

    public String teamBScore(GameState gs);

    public String gameNumber(GameState gs);

    public String winner(GameState gs);

    public String pause(GameState gs);
}
