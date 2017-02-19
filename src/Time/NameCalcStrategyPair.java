package Time;

/**
 * Created by Steffan on 19-02-2017.
 */
public class NameCalcStrategyPair {

    String name;
    TimerCalculatorStrategy calcStrat;

    public NameCalcStrategyPair(String name, TimerCalculatorStrategy calcStrat){
        this.name = name;
        this.calcStrat = calcStrat;
    }

    public String getName(){
        return name;
    }

    public TimerCalculatorStrategy getCalculationStrategy(){
        return calcStrat;
    }
}
