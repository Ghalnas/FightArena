package model;

public class Player extends Character
{
    public Player(Position position, double speed)
    {
        super(position, Direction.RIGHT, speed);
    }

    @Override
    public String toString()
    {
        return "Player";
    }
}
