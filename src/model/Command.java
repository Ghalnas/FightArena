package model;

import java.security.InvalidParameterException;

public class Command
{
    public enum Action { NONE, SLASH }
    private double x, y;
    private Action action;

    public Command(double x, double y, Action action)
    {
        if (x < -1.01 || x > 1.01 || y < -1.01 ||  y > 1.01) {
            throw new InvalidParameterException("x and y must be between -1 and 1");
        }
        this.x = x;
        this.y = y;
        this.action = action;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public Action getAction()
    {
        return action;
    }

    public void setAction(Action action)
    {
        this.action = action;
    }
}
