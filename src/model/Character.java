package model;

import java.util.Observable;


public abstract class Character extends Observable
{

    public Direction getDirection() {
        return direction;
    }

    public enum Direction { LEFT, RIGHT, UP, DOWN }
    public enum Event { MOVED, STOPPED }

    private int health, damage;
    protected Position position;
    private double speed;
    private Direction direction;
    private boolean isMoving;

    public Character(Position position, Direction direction, double speed)
    {
        this.health = 100;
        this.damage = 20;
        this.position = position;
        this.direction = direction;
        this.speed = speed;
        isMoving = false;
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

        position.setX(position.getX() + speed * c.getX());
        position.setY(position.getY() +  speed  * c.getY());
        if (c.getY() != 0 || c.getX() != 0)  {
            setChanged();
            notifyObservers(Event.MOVED);
            isMoving = true;
        } else if(isMoving) {
            setChanged();
            notifyObservers(Event.STOPPED);
            isMoving = false;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }
}
