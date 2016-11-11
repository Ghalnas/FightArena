package model;

public class Bot extends Character
{
    private Strategy strategy;

    public Bot(Position position)
    {
        super(position,Direction.LEFT);
        speed = 3;
    }

    public void move()
    {
        super.move(strategy.getCommand());
    }

    public void setStrategy(Strategy s)
    {
        strategy = s;
    }

    @Override
    public String toString()
    {
        return "Bot";
    }
}
