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
        String res = config.getString(StandardConfig.string_pre_game_number);
        res += " " + gs.getGameNumber();

        if(gs.getSeriesLength() != 0){
            res += " of " + gs.getSeriesLength();
        }
        return res;
    }

    @Override
    public String teamAName(GameState gs) {
        return gs.getTeamA().getName();
    }

    @Override
    public String teamAAbbreviation(GameState gs) {
        return gs.getTeamA().getAbbreviation();
    }

    @Override
    public String teamAScore(GameState gs) {
        return "" + gs.getTeamA().getPoints();
    }

    @Override
    public String teamBName(GameState gs) {
        return gs.getTeamB().getName();
    }

    @Override
    public String teamBAbbreviation(GameState gs) {
        return gs.getTeamB().getAbbreviation();
    }

    @Override
    public String teamBScore(GameState gs) {
        return "" + gs.getTeamB().getPoints();
    }
}
