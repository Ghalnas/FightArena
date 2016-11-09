package model;

public class StrategyDumbTwo implements Strategy
{
    private int i = 0;
    private int direction = -1;

    @Override
    public Command getCommand() {
        i += direction;
        if (i%30 == 0) {
            direction = -direction;
        }
        return new Command(0,direction);
    }
}
