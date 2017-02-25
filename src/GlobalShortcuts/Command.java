package GlobalShortcuts;

/**
 * A command, executing something when clicked.
 */
public interface Command {

    /**
     * Executes the action associated with this command,
     * whatever it is. Hopefully this was not the button
     * you were not supposed to press...
     */
    public void execute();
}
