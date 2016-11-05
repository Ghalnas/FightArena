package model;

import listener.CharacterListener;

import java.util.ArrayList;

public class Character
{

    private int health, damage;
    protected Position position;
    private ArrayList<CharacterListener> listeners;

    public Character(Position position)
    {
        this.health = 100;
        this.damage = 20;
        this.position = position;
        listeners = new ArrayList<CharacterListener>();
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        for (CharacterListener e : listeners) {
            e.hasMoved(this);
        }
        this.position = position;
    }


    public void addListener(CharacterListener e)
    {
        listeners.add(e);
    }

    public void removeListener(CharacterListener e)
    {
        listeners.remove(e);
    }

    @Override
    public String toString()
    {
        return "Character";
    }

}
