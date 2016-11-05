package model;

public class Player extends Character
{

    public Player(Position position) {
        super(position);
    }

    @Override
    public String toString()
    {
        return "Player";
    }
}
