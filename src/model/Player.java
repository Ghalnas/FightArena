package model;

public class Player extends Character
{
    public Player(Position position, double speed, double damage, double health)
    {
        super(position, Direction.RIGHT, speed,damage, health);
    }

    @Override
    public String toString()
    {
        return "Player";
    }
}
