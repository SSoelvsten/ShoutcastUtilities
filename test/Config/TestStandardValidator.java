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
        this.spy = new ConfigValidationSpy();
        this.validator = new StandardValidator();
        validator.ValidateConfig(spy);
    }

    @Test
    public void ShouldAddMissingValues(){
        //ConfigValidationSpy only got enable_keybindings and file_B_tag set
        //So the validator should add all the others, such as file_A_name
        assertThat(spy.getString(StandardConfig.file_A_name), IsNull.notNullValue());
    }

    @Test
    public void ShouldNotChangeTypeCorrectExistingValues(){
        //ConfigValidationSpy already got file_B_tag set correctly to "tomato"
        assertThat(spy.getString(StandardConfig.file_B_tag), is("tomato"));
    }

    @Test
    public void ShouldSetTypeIncorrectValueToStandard(){
        //ConfigValidationSpy already got enable_keybindings set, but incorrectly to "tomato"
        assertThat(spy.getBoolean(StandardConfig.enable_keybindings), is(true));
    }
}
