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

    private StandardConfig sc = new StandardConfig();
    private Config config;

    @Before
    public void setup(){
        this.config = new CFGConfig(new BasicReadWriteStrategy());
    }

    @Test
    public void ShouldHaveTeamAFiles(){
        assertThat(config.getString(sc.file_A_name), is(sc.getString(sc.file_A_name)));
        assertThat(config.getString(sc.file_A_score), is(sc.getString(sc.file_A_score)));
    }

    //If it already got the third team specific file, then it probably also got the others
    @Test
    public void ShouldHaveTeamBFiles(){
        assertThat(config.getString(sc.file_B_tag), is(sc.getString(sc.file_B_tag)));
    }

    @Test
    public void ShouldHaveTrueOnEnablingKeybinds(){
        assertThat(config.getBoolean(sc.enable_keybindings), is(true));
    }

    @Test
    public void ShouldHave72AsSwappingKey(){
        assertThat(config.getInteger(sc.swap_teams_key), is(72));
    }

    //TODO: Test the put and putIfAbsent?
}