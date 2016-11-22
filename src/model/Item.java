package model;

public class Item
{
    public enum ItemType { SPIN, LIGHTNING, GOLD, HEAL }

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
        hitbox = new Hitbox(position, -15, -15, 30,30);
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
