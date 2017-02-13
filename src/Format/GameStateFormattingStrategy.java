package Format;

import GameState.GameState;

/**
 * A strategy to format the information in a GameState into Strings
 */
public interface GameStateFormattingStrategy {

    public String teamName(int teamIndex, GameState gs);

    public String teamAbbreviation(int teamIndex, GameState gs);

    public String teamScore(int teamIndex, GameState gs);

    public String gameNumber(GameState gs);

    public String winner(GameState gs);

    public String pause(GameState gs);
}
