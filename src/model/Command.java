package model;

import java.security.InvalidParameterException;

public class Command
{
    private double x,y;

    public Command(double x, double y)
    {
        if (x < -1.01 || x > 1.01 || y < -1.01 ||  y > 1.01) {
            throw new InvalidParameterException("x and y must be between -1 and 1");
        }
        this.x = x;
        this.y = y;
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
}
