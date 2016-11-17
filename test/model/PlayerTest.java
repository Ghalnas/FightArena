package model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest
{
    private Player player;

    @Before
    public void setUp()
    {
        this.player = new Player(new Position(1, 1), 1, 1, 1);
    }

    @BeforeClass
    public static void testClassName()
    {
        assertEquals("model.Player", Player.class.getName());
    }

    @Test
    public void testToString()
    {
        assertEquals("Player", this.player.toString());
    }
}
