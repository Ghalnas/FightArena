package model;

import listener.CharacterListener;

import java.util.ArrayList;

public abstract class Character
{

    private int health, damage;
    private final int maxHealth;
    protected Position position;
    private ArrayList<CharacterListener> listeners;

    public Character(Position position)
    {
        this.maxHealth = 100;
        this.health = 78;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
