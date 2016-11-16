package model;

public class Position
{
    private double x,y;

    public Position(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distanceTo(Position p)
    {
        return Math.sqrt(Math.pow(p.getX()-x,2) + Math.pow(p.getY()-y,2));
    }

    @Override
    public String toString()
    {
        return "(" + (int)x + "," + (int)y + ")";
    }

    @Override
    public boolean equals(Object o)
    {
        return o instanceof Position && ((Position) o).getX() == x && ((Position) o).getY() == y;
    }

    @Override
    public Position clone()
    {
        return new Position(x,y);
    }
}
