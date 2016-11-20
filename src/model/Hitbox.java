package model;

public class Hitbox
{
    private double x,y,width,height;

    public Hitbox(double x, double y, double width, double height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean collision(Hitbox hb)
    {
        return x < hb.x + hb.width &&
                x + width > hb.x &&
                y < hb.y + hb.height &&
                height + y > hb.y;
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

    public String toString()
    {
        return "("+x+","+y+" : "+width+","+height+")";
    }
}
