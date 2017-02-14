package Config;


import InputOutput.BasicReadWriteStrategy;
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
    public void shouldHaveFolders(){
        assertThat(config.getString(ConfigKeys.folder_map_src), is("maps/"));
        assertThat(config.getString(ConfigKeys.folder_map_dst), is("txt/"));
        assertThat(config.getString(ConfigKeys.folder_txt_dst), is("txt/"));
    }

    @Test
    public void shouldHaveTrueOnEnablingKeybinds(){
        assertThat(config.getBoolean(ConfigKeys.enable_keybindings), is(true));
    }

    @Test
    public void shouldHave72AsSwappingKey(){
        assertThat(config.getInteger(ConfigKeys.swap_teams_key), is(72));
    }

    //TODO: Test the put and putIfAbsent?
}
