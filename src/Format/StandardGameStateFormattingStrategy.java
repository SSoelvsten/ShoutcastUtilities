package Format;

import Config.*;
import GameState.GameState;

/**
 * The normal output formatting used by twitch.tv/ikslorin
 */
public class StandardGameStateFormattingStrategy implements GameStateFormattingStrategy {

    private Config config;

    public StandardGameStateFormattingStrategy(Config config){
        this.config = config;
    }

    @Override
    public String pause(GameState gs) {
        String res = "";

        if(gs.getPause() != null){
            res = "Game Paused: " + gs.getPause().getPausingTeam().getName();

            if(gs.getPause().getReason() != null){
                res += ", " + gs.getPause().getReason();
            }
        }

        return res;
    }

    @Override
    public String winner(GameState gs) {
        if(gs.getWinner() != null){
            return gs.getWinner().getName();
        } else {
            return "draw";
        }
    }

    @Override
    public String gameNumber(GameState gs) {
        String res = config.getString(ConfigKeys.string_pre_game_number);
        res += " " + gs.getGameNumber();

        if(gs.getSeriesLength() != 0){
            res += " of " + gs.getSeriesLength();
        }
        return res;
    }

    @Override
    public String map(int mapIndex, GameState gs) {
        String res = gs.getMap(mapIndex).getName();
        if(gs.getMap(mapIndex).getGameType() != null)
            res += " (" + gs.getMap(mapIndex).getGameType() + ")";

        return res;
    }

    @Override
    public String teamName(int teamIndex, GameState gs) {
        return gs.getTeam(teamIndex).getName();
    }

    @Override
    public String teamAbbreviation(int teamIndex, GameState gs) {
        return gs.getTeam(teamIndex).getAbbreviation();
    }

    @Override
    public String teamScore(int teamIndex, GameState gs) {
        return "" + gs.getTeam(teamIndex).getPoints();
    }
}
