package Time;

import org.junit.Before;
import org.junit.Test;

public class TestStandardTimerCalcStrategies {

    private TimerSpyStub timerStub;
    private TimerCalculatorStrategy incStrat;
    private TimerCalculatorStrategy decStrat;

    @Before
    public void setup(){
        this.timerStub = new TimerSpyStub();
        this.incStrat = new StandardTimerCalculationStrategy(0, 59, 0, 59, 0, 23);
        this.decStrat = new StopAtZeroTimerCalculationStrategyDecorator(
                new StandardTimerCalculationStrategy(59, 0, 59, 0, 23, 0));
    }

    private void testStrategy(TimerCalculatorStrategy strat,
                              int fHour, int fMin, int fSec,
                              int rHour, int rMin, int rSec){
        timerStub.set(fHour, fMin, fSec);
        strat.setNextTime(timerStub);
        TimeCommonTests.assertTime(timerStub, rHour, rMin, rSec);
    }

    @Test
    public void increasingTimerAtNoRollover(){
        testStrategy(incStrat, 12, 13, 22, 12, 13, 23);
    }

    @Test
    public void increasingTimerAtSecondRollover(){
        testStrategy(incStrat, 2, 5, 59, 2, 6, 0);
    }

    @Test
    public void increasingTimerAtMinuteRollover(){
        testStrategy(incStrat, 3, 59, 59, 4, 0, 0);
    }

    @Test
    public void increasingTimerAtHourRollover(){
        testStrategy(incStrat, 23, 59, 59, 0, 0, 0);
    }

    @Test
    public void decreasingTimerAtNoRollover(){
        testStrategy(decStrat, 2, 3, 52, 2, 3, 51);
    }

    @Test
    public void decreasingTimerAtSecondRollover(){
        testStrategy(decStrat, 0, 15, 0, 0, 14, 59);
    }

    @Test
    public void decreasingTimerAtMinuteRollover(){
        testStrategy(decStrat, 8, 0, 0, 7, 59, 59);
    }

    @Test
    public void decreasingTimerStopsAtZero(){
        testStrategy(decStrat, 0, 0, 0, 0, 0, 0);
    }
}
