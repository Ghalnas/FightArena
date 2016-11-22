package model;

import java.util.Observable;
import model.Command.Action;

public abstract class Character extends Observable
{

    public Direction getDirection() {
        return direction;
    }



    public enum Direction { LEFT, RIGHT, UP, DOWN }
    public enum Event { MOVED, STOPPED, ASKSLASH, SPIN, LIGHTNING }

    protected Position position, startPos;
    private double speed, startSpeed, damage, startDamage, health, startHealth;
    private Direction direction, startDir;
    private boolean isMoving, isSlashing, isSpinning, isLightning, isGold;
    private int slashCpt;
    private Item.ItemType itemType;
    private Hitbox hitbox;
    private Hitbox lightning;
    private Hitbox sword;
    private boolean isDead;

    public Character(Position position, Direction direction, double speed, double damage, double health)
    {
        startPos = position.clone();
        startDir = direction;
        startSpeed = speed;
        startDamage = damage;
        startHealth = health;
        isGold = false;
        initChar();
    }

    public void initChar()
    {
        isDead = false;
        this.health = startHealth;
        this.damage = startDamage;
        this.direction = startDir;
        setPosition(startPos.clone());
        this.speed = startSpeed;
        isMoving = false;
        slashCpt = 0;
        if (isSlashing) {
            endSlash();
        }
        if (isSpinning) {
            stopSpin();
        }
        if (isLightning) {
            stopLightning();
        }
        if (isGold) {
            stopGold();
        }
        itemType = null;
        this.hitbox = new Hitbox(position,-20,-30,40,60);
        getHitbox();
    }


    public Position getPosition()
    {
        return position;
    }

    public void move(Command c)
    {
        // prevent double speed on diagonal
        if (Math.abs(c.getX()) + Math.abs(c.getY()) > 1.9) {
            c.setX(Math.signum(c.getX()) * Math.PI/4);
            c.setY(Math.signum(c.getY()) * Math.PI/4);
        }
        if(c.getAction() == Action.SLASH) {
            setChanged();
            notifyObservers(Event.ASKSLASH);
        }
        c.setX(Math.rint(c.getX()));
        c.setY(Math.rint(c.getY()));

        if ((int)c.getX() == -1 && (int)c.getY() == 0) {
            direction = Direction.LEFT;
        } else if ((int)c.getX() == 1 && (int)c.getY() == 0) {
            direction = Direction.RIGHT;
        } else if ((int)c.getX() == 0 && (int)c.getY() == 1) {
            direction = Direction.DOWN;
        } else if ((int)c.getX() == 0 && (int)c.getY() == -1) {
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
        if (position == null) {
            position = p;
            return;
        }
        Position posTmp = position.clone();
        position.setX(p.getX());
        position.setY(p.getY());
        if (!p.equals(posTmp) && slashCpt == 0 && !isSlashing && !isSpinning)  {
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

    public void damaged(double damage)
    {
        if (isGold) {
            damage /= 2;
        }
        health -= damage;
        if (health <= 0) {
            isDead = true;
            health = 0;
        }
    }

//    public void setHealth(double health)
//    {
//        this.health = health;
//    }

    public Hitbox getHitbox()
    {
        return hitbox;
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

    public void startSpin()
    {
        hitbox = new Hitbox(position, -31, -40,62,80);
        speed = startSpeed+1;
        isSpinning = true;
        spin();
    }

    public void spin()
    {
        setChanged();
        notifyObservers(Event.SPIN);
    }

    public void stopSpin()
    {
        hitbox = new Hitbox(position, -20, -30,40,60);
        isSpinning = false;
        speed = startSpeed;
        setChanged();
        notifyObservers(Action.NONE);
    }

    public void startLightning()
    {
        isLightning = true;
        lightning = new Hitbox(position.clone(), -85,-85,170,170);
        lightning();
    }

    public void lightning()
    {
        setChanged();
        notifyObservers(Event.LIGHTNING);
    }

    public void stopLightning()
    {
        isLightning = false;
        setChanged();
        notifyObservers(Action.NONE);
    }

    public Hitbox getLightning() {
        return lightning;
    }

    public boolean isSpinning()
    {
        return isSpinning;
    }

    public boolean isGold() {
        return isGold;
    }

    public void startGold()
    {
        isGold = true;
        hitbox.multiply(2);
        sword.multiply(2);
        damage *= 2;
        setChanged();
        notifyObservers(Action.NONE);
    }

    public void stopGold()
    {
        isGold = false;
        hitbox.divide(2);
        hitbox.divide(2);
        damage /= 2;
        setChanged();
        notifyObservers(Action.NONE);
    }

    public Hitbox getSword()
    {
        switch (direction) {
            case UP:
                sword = new Hitbox(position, -15, -40,30,25);
                break;
            case RIGHT:
                sword = new Hitbox(position, 15, -10,25,30);
                break;
            case DOWN:
                sword = new Hitbox(position, -15, 20,30,25);
                break;
            case LEFT:
                sword = new Hitbox(position, -40, -10,25,30);
                break;
        }
        int mult = isGold ? 2 : 1;
        sword.multiply(mult);
        return sword;
    }

    public boolean isDead()
    {
        return isDead;
    }
}
