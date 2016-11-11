package model;

import java.util.Observable;


public abstract class Character extends Observable
{

    public Direction getDirection() {
        return direction;
    }

    public enum Direction { LEFT, RIGHT, UP, DOWN }

    private int health, damage;
    protected Position position;
    protected int speed;
    private Direction direction;

    public Character(Position position, Direction direction)
    {
        this.health = 100;
        this.damage = 20;
        this.position = position;
        this.direction = direction;
        this.speed = 5;
    }

    public Position getPosition()
    {
        return position;
    }

    public void move(Command c)
    {
        if (c.getX() == -1 && c.getY() == 0) {
            direction = Direction.LEFT;
        } else if (c.getX() == 1 && c.getY() == 0) {
            direction = Direction.RIGHT;
        } else if (c.getX() == 0 && c.getY() == 1) {
            direction = Direction.DOWN;
        } else if (c.getX() == 0 && c.getY() == -1) {
            direction = Direction.UP;
        }

        Position tmp = position.clone();
        position.setX(position.getX() + speed * c.getX());
        position.setY(position.getY() + speed * c.getY());
        if (!position.equals(tmp)) {
            setChanged();
            notifyObservers(this.position);
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
