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
            new Bot(new Position(1, 1), 1, 1, 2),
            new Item()
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

    @Test
    public void testGetCommandWithBotGold()
    {
        Bot bot = new Bot(new Position(1, 1), 1, 1, 2);
        bot.isGold = true;
        this.strategyEpic = new StrategyEpic(
                new Player(new Position(1, 1), 1, 1, 2),
                bot,
                new Item()
        );
        assertEquals(true, strategyEpic.getCommand() != null);
    }

    @Test
    public void testBotGetHeal()
    {
        Bot bot = new Bot(new Position(200, 200), 1, 1, 2);
        bot.health = 48;

        Player player = new Player(new Position(1, 1), 1, 1, 2);
        player.health = 49;
        player.isGold = true;

        Item item = new Item();
        item.init(Item.ItemType.HEAL, new Position(1, 1));

        this.strategyEpic = new StrategyEpic(
                player,
                bot,
                item
        );
        assertEquals(true, strategyEpic.getCommand() != null);
    }

    @Test
    public void testGetDirection()
    {
        Bot bot = new Bot(new Position(49, 49), 1, 1, 2);

        this.strategyEpic = new StrategyEpic(
                new Player(new Position(50, 50), 1, 1, 2),
                bot,
                new Item()
        );
        assertEquals(true, strategyEpic.getCommand() != null);

        bot = new Bot(new Position(51, 49), 1, 1, 2);

        this.strategyEpic = new StrategyEpic(
                new Player(new Position(50, 50), 1, 1, 2),
                bot,
                new Item()
        );
        assertEquals(true, strategyEpic.getCommand() != null);

        bot = new Bot(new Position(49, 51), 1, 1, 2);

        this.strategyEpic = new StrategyEpic(
                new Player(new Position(50, 50), 1, 1, 2),
                bot,
                new Item()
        );
        assertEquals(true, strategyEpic.getCommand() != null);

        bot = new Bot(new Position(51, 50), 1, 1, 2);

        this.strategyEpic = new StrategyEpic(
                new Player(new Position(50, 50), 1, 1, 2),
                bot,
                new Item()
        );
        assertEquals(true, strategyEpic.getCommand() != null);

        bot = new Bot(new Position(49, 50), 1, 1, 2);

        this.strategyEpic = new StrategyEpic(
                new Player(new Position(50, 50), 1, 1, 2),
                bot,
                new Item()
        );
        assertEquals(true, strategyEpic.getCommand() != null);

        bot = new Bot(new Position(50, 51), 1, 1, 2);

        this.strategyEpic = new StrategyEpic(
                new Player(new Position(50, 50), 1, 1, 2),
                bot,
                new Item()
        );
        assertEquals(true, strategyEpic.getCommand() != null);

        bot = new Bot(new Position(50, 49), 1, 1, 2);

        this.strategyEpic = new StrategyEpic(
                new Player(new Position(50, 50), 1, 1, 2),
                bot,
                new Item()
        );
        assertEquals(true, strategyEpic.getCommand() != null);
    }
}
