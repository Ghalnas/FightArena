package model;

import java.util.Observable;

public abstract class Character extends Observable
{
    private int health, damage;
    protected Position position;
    protected int speed;

    public Character(Position position)
    {
        this.health = 100;
        this.damage = 20;
        this.position = position;
        this.speed = 5;
    }

    public Position getPosition()
    {
        return position;
    }

    public void move(Command c)
    {
        Position tmp = position.clone();
        position.setX(position.getX() + speed * c.getX());
        position.setY(position.getY() + speed * c.getY());
        if (!position.equals(tmp)) {
            setChanged();
            notifyObservers(this.position);
        }
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

