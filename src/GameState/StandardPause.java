package GameState;

public class StandardPause implements Pause {

    private Team team;
    private String reason;

    public StandardPause(Team team, String reason){
        this.team = team;
        this.reason = reason;
    }

    @Override
    public Team getPausingTeam() {
        return this.team;
    }

    @Override
    public String getReason() {
        return this.reason;
    }
}
