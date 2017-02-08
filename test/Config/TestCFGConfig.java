package Config;


import ReadWrite.BasicReadWriteStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the CFG Config for it to read in the settings
 * in the config.cfg file. For these tests to pass,
 * the config.cfg has to adhere to the standard settings
 */
public class TestCFGConfig {

    private StandardConfig sc;
    private Config config;

    @Before
    public void setup(){
        this.config = new CFGConfig(new BasicReadWriteStrategy());
    }

    @Test
    public void ShouldHaveTeamAFiles(){
        assertThat(config.getString(sc.file_A_name), is(sc.getString(sc.file_A_name)));
    }
}
