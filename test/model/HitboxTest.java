package model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HitboxTest
{
    private Hitbox hitbox;

    @Before
    public void setUp()
    {
        this.hitbox = new Hitbox(new Position(1, 1), 1, 1, 1, 1);
    }

    @BeforeClass
    public static void testClassName()
    {
        assertEquals("model.Hitbox", Hitbox.class.getName());
    }

    @Test
    public void testCollision()
    {
        Hitbox otherHitbox = new Hitbox(new Position(1, 1), 1, 1, 1, 1);
        assertTrue(this.hitbox.collision(otherHitbox));
    }

    @Test
    public void testToString()
    {
        assertEquals("(2.0,2.0 : 1.0,1.0)", this.hitbox.toString());
    }

    @Test
    public void testGetX()
    {
        this.hitbox.setX(12);
        assertEquals(13, this.hitbox.getX(), 0.001);
    }

    @Test
    public void testGetY()
    {
        this.hitbox.setY(2);
        assertEquals(3, this.hitbox.getY(), 0.001);
    }

    @Test
    public void testGetWidth()
    {
        this.hitbox.setWidth(6);
        assertEquals(6, this.hitbox.getWidth(), 0.001);
    }

    @Test
    public void testGetHeight()
    {
        this.hitbox.setHeight(10);
        assertEquals(10, this.hitbox.getHeight(), 0.001);
    }

    @Test
    public void testGetPosition()
    {
        assertTrue(this.hitbox.getPosition() != null);
    }

    @Test
    public void testDivide()
    {
        this.hitbox.divide(2);
        assertEquals(1.5, this.hitbox.getX(), 0.001);
        assertEquals(1.5, this.hitbox.getY(), 0.001);
        assertEquals(0.5, this.hitbox.getWidth(), 0.001);
        assertEquals(1.5, this.hitbox.getY(), 0.001);
    }
}
