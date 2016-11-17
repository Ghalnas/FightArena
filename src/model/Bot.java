package model;

public class Bot extends Character
{
    private Strategy strategy;

    public Bot(Position position, double speed, double damage, double health)
    {
        super(position,Direction.LEFT,speed, damage, health);
    }

    public void initChar(Strategy s)
    {
        super.initChar();
        setStrategy(s);
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
