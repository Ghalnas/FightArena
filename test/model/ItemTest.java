package model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ItemTest
{
    private Item item;

    @Before
    public void setUp()
    {
        this.item = new Item();
    }

    @BeforeClass
    public static void testClassName()
    {
        assertEquals("model.Item", Item.class.getName());
    }

    @Test
    public void testGetType()
    {
        this.item.setType(Item.ItemType.LIGHTNING);
        assertEquals(Item.ItemType.LIGHTNING, this.item.getType());
    }

    @Test
    public void testGetHitbox()
    {
        this.item.setHitbox(new Hitbox(new Position(1, 1), -15, -15, 30,30));
        assertTrue(this.item.getHitbox() != null);
    }

    @Test
    public void testRemove()
    {
        this.item.remove();
        assertNull(this.item.getType());
        assertNull(this.item.getHitbox());
    }
}
