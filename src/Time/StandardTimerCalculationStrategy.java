package Time;

/**
 * A timer, that at every step changes by one second and
 * checks the values for being in the [Start; End]
 *
 * The start and end compared determines adding or subtracting a second
 *     For x < y -> [x;y] ticks up
 *               -> [y,x] ticks down
 */
public class StandardTimerCalculationStrategy implements TimerCalculatorStrategy {

    private int secondStart;
    private int secondEnd;
    private int secondChange;

    private int minuteStart;
    private int minuteEnd;
    private int minuteChange;

    private int hourStart;
    private int hourEnd;
    private int hourChange;

    public StandardTimerCalculationStrategy(int secondRollover, int secondReset,
                                            int minuteRollover, int minuteReset,
                                            int hourRollover, int hourReset){
        this.secondEnd = secondRollover;
        this.secondStart = secondReset;

        if(secondReset < secondRollover){
            this.secondChange = 1;
        } else {
            this.secondChange = -1;
        }

        this.minuteEnd = minuteRollover;
        this.minuteStart = minuteReset;

        if(minuteReset < minuteRollover){
            this.minuteChange = 1;
        } else {
            this.minuteChange = -1;
        }

        this.hourEnd = hourRollover;
        this.hourStart = hourReset;

        if(hourReset < hourRollover){
            this.hourChange = 1;
        } else {
            this.hourChange = -1;
        }
    }


    @Override
    public void setNextTime(ModifiableTimer timer) {
        int hour = timer.getHour();
        int minute = timer.getMinute();
        int second = timer.getSecond();

        second += secondChange;

        if((secondChange > 0 && second > secondEnd) ||
                (secondChange < 0 && second < secondEnd)) {
            second = secondStart;
            minute += minuteChange;
        }

        if((minuteChange > 0 && minute > minuteEnd) ||
                (minuteChange < 0 && minute < minuteEnd)){
            minute = minuteStart;
            hour += hourChange;
        }

        if((hourChange > 0 && hour  > hourEnd) ||
                (hourChange < 0 && hour < hourEnd)){
            hour = hourStart;
        }

        timer.set(hour, minute, second);
    }
}
