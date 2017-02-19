package AbstractFactory;

import InputOutput.PreSpaceReadWriteStrategyDecorator;
import InputOutput.ReadWriteStrategy;

/**
 * OBS Studio requires an " " in front of all .txt to center correctly
 */
public class OBSStudioMainFactory extends StandardMainFactory {

    @Override
    public ReadWriteStrategy createReadWriteStrategy() {
        return new PreSpaceReadWriteStrategyDecorator(super.createReadWriteStrategy());
    }
}
