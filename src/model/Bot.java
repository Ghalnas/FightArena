package model;

public class Bot extends Character
{
    private Strategy strategy;

    public Bot(Position position, double speed)
    {
        super(position,Direction.LEFT,speed);
    }

    public void move()
    {
        super.move(strategy.getCommand());
    }

    public void setStrategy(Strategy s)
    {
        strategy = s;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    @Override
    public String toString()
    {
        return "Bot";
    }
}
