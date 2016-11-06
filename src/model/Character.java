package model;

import java.util.Observable;

public abstract class Character extends Observable
{

    private int health, damage;
    private final int maxHealth;
    protected Position position;

    public Character(Position position)
    {
        this.maxHealth = 100;
        this.health = 78;
        this.damage = 20;
        this.position = position;
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
        setChanged();
        notifyObservers(this.position);
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

}
