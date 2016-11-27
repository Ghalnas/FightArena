package model;

import org.junit.BeforeClass;
import org.junit.Test;

import static model.GameEvent.DIFFICULTY_CHANGED;
import static org.junit.Assert.assertEquals;

public class GameEventTest
{
    @BeforeClass
    public static void testClassName()
    {
        assertEquals("model.GameEvent", GameEvent.class.getName());
    }

    @Test
    public void testGetDamage()
    {
        assertEquals(DIFFICULTY_CHANGED, model.GameEvent.DIFFICULTY_CHANGED);
    }
}
