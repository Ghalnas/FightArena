package model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StrategyEpicTest
{
    private StrategyEpic strategyEpic;

    @Before
    public void setUp()
    {
        this.strategyEpic = new StrategyEpic(
            new Player(new Position(1, 1), 1, 1, 2),
            new Bot(new Position(1, 1), 1, 1, 2)
        );
    }

    @BeforeClass
    public static void testClassName()
    {
        assertEquals("model.StrategyEpic", StrategyEpic.class.getName());
    }

    @Test
    public void testGetCommand()
    {
        assertEquals(true, strategyEpic.getCommand() != null);
    }
}
