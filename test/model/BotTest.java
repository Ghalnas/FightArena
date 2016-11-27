package model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class BotTest
{
    private Bot bot;

    @Before
    public void setUp()
    {
        this.bot = new Bot(new Position(1, 1), 1, 1, 1);
    }

    @BeforeClass
    public static void testClassName()
    {
        assertEquals("model.Bot", Bot.class.getName());
    }

    @Test
    public void testSetStrategy()
    {
        this.bot.setStrategy(
            new StrategyLow(
                new Player(
                    new Position(1, 1),
                    1,
                    1,
                    1
                ),
                this.bot,
                new Item()
            )
        );
        assertTrue(this.bot.getStrategy() != null);
    }

    @Test
    public void testToString()
    {
        assertEquals("Bot", this.bot.toString());
    }

    @Test
    public void testInitChar()
    {
        this.bot.slash();
        this.bot.startSpin();
        this.bot.startLightning();
        this.bot.startGold();
        this.bot.damaged(25);
        this.bot.getHitbox();
        this.bot.getLightning();
        this.bot.heal();
        this.bot.initChar(new StrategyLow(new Player(new Position(1, 1), 1, 1, 1), this.bot, new Item()));
        this.bot.move();
        assertEquals("Bot", this.bot.toString());
    }

    @Test
    public void testGetSpeed()
    {
        this.bot.setSpeed(12);
        assertEquals(12, this.bot.getSpeed(), 0.001);
    }

    @Test
    public void testGetDamage()
    {
        this.bot.setDamage(12);
        assertEquals(12, this.bot.getDamage(), 0.001);
    }

    @Test
    public void testIsDead()
    {
        this.bot.isDead = true;
        assertTrue(this.bot.isDead());
    }
}
