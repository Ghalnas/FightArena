package model;

public class Hitbox
{
    private double x,y,width,height;
    private Position position;

    public Hitbox(Position position, double x, double y, double width, double height)
    {
        this.position = position;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean collision(Hitbox hb)
    {
        return getX() < hb.getX() + hb.width &&
                getX() + width > hb.getX() &&
                getY() < hb.getY() + hb.height &&
                height + getY() > hb.getY();
    }

    public double getX() {
        return position.getX()+x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return position.getY()+y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Position getPosition() {
        return position;
    }

    public String toString()
    {
        return "("+getX()+","+getY()+" : "+width+","+height+")";
    }

    public void multiply(double mult)
    {
        x *= mult;
        y *= mult;
        width *= mult;
        height *= mult;
    }

    public void divide(double div)
    {
        x /= div;
        y /= div;
        width /= div;
        height /= div;
    }
}
