package model;

import java.util.Observable;
import model.Command.Action;

public abstract class Character extends Observable
{

    public Direction getDirection() {
        return direction;
    }



    public enum Direction { LEFT, RIGHT, UP, DOWN }
    public enum Event { MOVED, STOPPED, ASKSLASH }

    protected Position position, startPos;
    private double speed, startSpeed, damage, startDamage, health, startHealth;
    private Direction direction, startDir;
    private boolean isMoving, isSlashing;
    private int slashCpt;

    public Character(Position position, Direction direction, double speed, double damage, double health)
    {
        startPos = position.clone();
        startDir = direction;
        startSpeed = speed;
        startDamage = damage;
        startHealth = health;
        initChar();
    }

    public void initChar()
    {
        this.health = startHealth;
        this.damage = startDamage;
        this.direction = startDir;
        setPosition(startPos.clone());
        this.speed = startSpeed;
        isMoving = false;
        slashCpt = 0;
        if (isSlashing) {
            endSlash();
            isSlashing = false;
        }
    }


    public Position getPosition()
    {
        return position;
    }

    public void move(Command c)
    {

        if(c.getAction() == Action.SLASH) {
            setChanged();
            notifyObservers(Event.ASKSLASH);
        }
        if (c.getX() == -1 && c.getY() == 0) {
            direction = Direction.LEFT;
        } else if (c.getX() == 1 && c.getY() == 0) {
            direction = Direction.RIGHT;
        } else if (c.getX() == 0 && c.getY() == 1) {
            direction = Direction.DOWN;
        } else if (c.getX() == 0 && c.getY() == -1) {
            direction = Direction.UP;
        }
        Position p = new Position(position.getX() + speed * c.getX(), position.getY() + speed * c.getY());
        setPosition(p);
    }

    public void slash()
    {
        isSlashing = true;
        setChanged();
        notifyObservers(Action.SLASH);
    }

    public void endSlash()
    {
        isSlashing = false;
        setChanged();
        notifyObservers(Action.NONE);
    }

    private void setPosition(Position p)
    {
        Position posTmp = position;
        position = p;
        if (!p.equals(posTmp) && slashCpt == 0 && !isSlashing)  {
            setChanged();
            notifyObservers(Event.MOVED);
            isMoving = true;
        } else if(isMoving && !isSlashing) {
            setChanged();
            notifyObservers(Event.STOPPED);
            isMoving = false;
        }
    }


    public double getHealth()
    {
        return health;
    }

    public void setHealth(double health)
    {
        this.health = health;
    }

    public Hitbox getHitbox()
    {
        return new Hitbox(position.getX()-20,position.getY()-20,40,40);
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public double getDamage()
    {
        return damage;
    }

    public void setDamage(double damage)
    {
        this.damage = damage;
    }
}
