package GlobalShortcuts;

import GameState.GameStateControllerSpy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestCommands {

    private GameStateControllerSpy gsc;

    @Before
    public void setup(){
        gsc = new GameStateControllerSpy();
    }

    @Test
    public void valueChangerShouldIncrementWhenInitializedWithOne(){
        Command c = new ValueChangeCommand(gsc, 0, 1);
        c.execute();
        assertThat(gsc.teamIndex, is(0));
        assertThat(gsc.teamScoreChange, is(1));
    }

    @Test
    public void valueChangeShouldDecrementWhenInitializedWithMinusOne(){
        Command c = new ValueChangeCommand(gsc, 1, -1);
        c.execute();
        assertThat(gsc.teamIndex, is(1));
        assertThat(gsc.teamScoreChange, is(-1));
    }

    @Test
    public void shiftCommandShouldCallShift(){
        Command c = new ShiftCommand(gsc);
        c.execute();
        assertThat(gsc.shift, is(true));
    }

    @Test
    public void unpauseCommandShouldCallUnpause(){
        Command c = new UnpauseCommand(gsc);
        c.execute();
        assertThat(gsc.unpause, is(true));
    }
}
