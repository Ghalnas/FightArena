package model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PositionTest
{
    private Position position;

    @Before
    public void setUp()
    {
        this.position = new Position(20.2, 12.9);
    }

    @BeforeClass
    public static void testClassName()
    {
        assertEquals("model.Position", Position.class.getName());
    }

    @Test
    public void testGetX()
    {
        assertEquals(20.2, this.position.getX(), 0.001);
    }

    @Test
    public void testGetY()
    {
        assertEquals(12.9, this.position.getY(), 0.001);
    }

    @Test
    public void testSetX()
    {
        this.position.setX(2.5);
        assertEquals(2.5, this.position.getX(), 0.001);
    }

    @Test
    public void testSetY()
    {
        this.position.setY(42.33);
        assertEquals(42.33, this.position.getY(), 0.001);
    }

    @Test
    public void testToString()
    {
        assertEquals("(20,12)", this.position.toString());
    }

    @Test
    public void testEquals()
    {
        assertTrue(this.position.equals(this.position));
    }

    @Test
    public void testClone()
    {
        assertEquals(this.position, this.position.clone());
    }
}
