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
}
