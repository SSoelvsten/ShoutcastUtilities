package GameState;

public interface Pause {

    /**
     * @return The team setting the pause
     */
    public Team getPausingTeam();

    /**
     * @return A string if reason is set, null otherwise
     */
    public String getReason();
}
