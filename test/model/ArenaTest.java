package model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArenaTest
{
    private Arena arena;

    @Before
    public void setUp()
    {
        this.arena = new Arena(100.3, 56.8);
    }

    @BeforeClass
    public static void testClassName()
    {
        assertEquals("model.Arena", Arena.class.getName());
    }

    @Test
    public void testGetWidth()
    {
        assertEquals(100.3, this.arena.getWidth(), 0.001);
    }

    @Test
    public void testGetHeight()
    {
        assertEquals(56.8, this.arena.getHeight(), 0.001);
    }
}
