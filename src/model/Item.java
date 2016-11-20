package model;

public class Item
{
    public enum ItemType { SPIN, LIGHTNING }

    private ItemType type;
    private Hitbox hitbox;

    public Item()
    {
    }

    public void remove()
    {
        this.type = null;
        this.hitbox = null;
    }

    public void init(ItemType type, Position position)
    {
        this.type = type;
        hitbox = new Hitbox(position.getX()-7, position.getY()-7, 14,14);
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }
}
