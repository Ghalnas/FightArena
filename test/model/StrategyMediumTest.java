package model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StrategyMediumTest
{
    private StrategyMedium strategyMedium;

    @Before
    public void setUp()
    {
        this.strategyMedium = new StrategyMedium(
            new Player(new Position(1, 1), 1, 1, 2),
            new Bot(new Position(1, 1), 1, 1, 2),
            new Item()
        );
    }

    @BeforeClass
    public static void testClassName()
    {
        assertEquals("model.StrategyMedium", StrategyMedium.class.getName());
    }

    @Test
    public void testGetCommand()
    {
        assertEquals(true, strategyMedium.getCommand() != null);
    }

    @Test
    public void testGetCommandWithBotGold()
    {
        Bot bot = new Bot(new Position(1, 1), 1, 1, 2);
        bot.isGold = true;
        this.strategyMedium = new StrategyMedium(
                new Player(new Position(1, 1), 1, 1, 2),
                bot,
                new Item()
        );
        assertEquals(true, strategyMedium.getCommand() != null);
    }

    @Test
    public void testBotGetHeal()
    {
        Bot bot = new Bot(new Position(200, 200), 1, 1, 2);
        bot.health = 51;

        Item item = new Item();
        item.init(Item.ItemType.HEAL, new Position(1, 1));

        this.strategyMedium = new StrategyMedium(
                new Player(new Position(1, 1), 1, 1, 2),
                bot,
                item
        );
        this.strategyMedium.cmpt = 29;
        assertEquals(true, strategyMedium.getCommand() != null);
    }

    @Test
    public void testGetDirection()
    {
        Bot bot = new Bot(new Position(49, 49), 1, 1, 2);

        this.strategyMedium = new StrategyMedium(
                new Player(new Position(50, 50), 1, 1, 2),
                bot,
                new Item()
        );
        assertEquals(true, strategyMedium.getCommand() != null);

        bot = new Bot(new Position(51, 49), 1, 1, 2);

        this.strategyMedium = new StrategyMedium(
                new Player(new Position(50, 50), 1, 1, 2),
                bot,
                new Item()
        );
        assertEquals(true, strategyMedium.getCommand() != null);

        bot = new Bot(new Position(49, 51), 1, 1, 2);

        this.strategyMedium = new StrategyMedium(
                new Player(new Position(50, 50), 1, 1, 2),
                bot,
                new Item()
        );
        assertEquals(true, strategyMedium.getCommand() != null);

        bot = new Bot(new Position(51, 50), 1, 1, 2);

        this.strategyMedium = new StrategyMedium(
                new Player(new Position(50, 50), 1, 1, 2),
                bot,
                new Item()
        );
        assertEquals(true, strategyMedium.getCommand() != null);

        bot = new Bot(new Position(49, 50), 1, 1, 2);

        this.strategyMedium = new StrategyMedium(
                new Player(new Position(50, 50), 1, 1, 2),
                bot,
                new Item()
        );
        assertEquals(true, strategyMedium.getCommand() != null);

        bot = new Bot(new Position(50, 51), 1, 1, 2);

        this.strategyMedium = new StrategyMedium(
                new Player(new Position(50, 50), 1, 1, 2),
                bot,
                new Item()
        );
        assertEquals(true, strategyMedium.getCommand() != null);

        bot = new Bot(new Position(50, 49), 1, 1, 2);

        this.strategyMedium = new StrategyMedium(
                new Player(new Position(50, 50), 1, 1, 2),
                bot,
                new Item()
        );
        assertEquals(true, strategyMedium.getCommand() != null);
    }
}
