package AbstractFactory;

import Config.*;
import Format.*;
import GameState.*;
import ReadWrite.*;

/**
 * Creates a GameState containing the information saved from the last session.
 * Furthermore it also has a pre
 */
public class OBSStudioGameStateControllerFactory implements GameStateControllerFactory {

    private Config config;

    public OBSStudioGameStateControllerFactory(){
        this.config = new CFGConfig(new BasicReadWriteStrategy());
        (new StandardValidator(new StandardConfig())).ValidateConfig(config);
    }

    @Override
    public ModifiableGameState getGameState() {
        ModifiableGameState gs = new StandardGameState();

        ReadWriteStrategy rws = getReadWriteStrategy();
        gs.setTeamAIdentity(rws.read(ConfigKeys.file_A_name), rws.read(ConfigKeys.file_A_abbreviation));
        gs.setTeamAPoints(Integer.parseInt(rws.read(ConfigKeys.file_A_score)));

        gs.setTeamBIdentity(rws.read(ConfigKeys.file_B_name), rws.read(ConfigKeys.file_B_abbreviation));
        gs.setTeamBPoints(Integer.parseInt(rws.read(ConfigKeys.file_B_score)));

        return gs;
    }

    @Override
    public Config getValidatedConfig() {
        return config;
    }

    @Override
    public ReadWriteStrategy getReadWriteStrategy() {
        return new PreSpaceReadWriteStrategyDecorator(new BasicReadWriteStrategy());
    }

    @Override
    public GameStateFormattingStrategy getFormattingStrategy() {
        return new StandardGameStateFormattingStrategy(config);
    }
}
