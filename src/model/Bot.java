package model;

import listener.CharacterListener;

public class Bot extends Character implements CharacterListener
{

    public Bot(Position position) {
        super(position);
    }

    @Override
    public void hasMoved(Character character) {
        if (position.distanceTo(character.getPosition()) < 100) {
            System.out.println("Bot detected that player is close");
        }
    }

    @Override
    public String toString()
    {
        return "Bot";
    }

}
