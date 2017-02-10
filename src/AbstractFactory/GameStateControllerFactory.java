package AbstractFactory;

import Config.Config;
import Format.GameStateFormattingStrategy;
import GameState.ModifiableGameState;
import ReadWrite.ReadWriteStrategy;

/**
 * Factories for a GameStateController
 */
public interface GameStateControllerFactory {

    /**
     * Create a GameState initialized as wished
     * @return A GameState in some valid state
     */
    public ModifiableGameState getGameState();

    /**
     * Creates a config, which has been validated.
     * @postcondition: The config has been validated and has
     *                 all key values defined in StandardConfig
     * @return A validated config to read settings from
     */
    public Config getValidatedConfig();

    /**
     * The ReadWriteStrategy to be used for writing to files
     * @return A ReadWriteStrategy to be used when
     *         committing the files to disk.
     */
    public ReadWriteStrategy getReadWriteStrategy();

    /**
     * The Formatting to use when writing to files
     * @return A GameStateFormattingStrategy defining the format
     *         and strings to send to disk
     */
    public GameStateFormattingStrategy getFormattingStrategy();
}
