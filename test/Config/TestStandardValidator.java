package Config;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestStandardValidator {

    private Config spy;
    private StandardValidator validator;

    @Before
    public void setup(){
        this.spy = new ConfigStub();
        this.validator = new StandardValidator(new StandardConfig());
        validator.ValidateConfig(spy);
    }

    @Test
    public void shouldAddMissingValues(){
        //ConfigStub only got enable_keybindings and file_B_tag set
        //So the validator should add all the others, such as file_A_name
        assertThat(spy.getString(ConfigKeys.file_A_name), IsNull.notNullValue());
    }

    @Test
    public void shouldNotChangeTypeCorrectExistingValues(){
        //ConfigStub already got file_B_tag set correctly to "tomato"
        assertThat(spy.getString(ConfigKeys.file_B_tag), is("tomato"));
    }

    @Test
    public void shouldSetTypeIncorrectValueToStandard(){
        //ConfigStub already got enable_keybindings set, but incorrectly to "tomato"
        assertThat(spy.getBoolean(ConfigKeys.enable_keybindings), is(true));
    }
}
