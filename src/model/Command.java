package model;

import java.security.InvalidParameterException;

public class Command
{
    private int x,y;

    public Command(int x, int y)
    {
        if (x != -1 && x != 0 &&  x!= 1 || y != -1 && y != 0 &&  y!= 1) {
            throw new InvalidParameterException("x and y must be either -1, 0 or 1");
        }
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
