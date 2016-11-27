package model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatTest
{
    private Stat stat;

    @Before
    public void setUp()
    {
        this.stat = new Stat();
    }

    @BeforeClass
    public static void testClassName()
    {
        assertEquals("model.Stat", Stat.class.getName());
    }

    @Test
    public void testGetVictories()
    {
        stat.setVictories(2);
        assertEquals(2, stat.getVictories());
    }

    @Test
    public void testGetLoses()
    {
        stat.setLoses(3);
        assertEquals(3, stat.getLoses());
    }

    @Test
    public void testGetHit()
    {
        stat.setHit(4);
        assertEquals(4, stat.getHit());
    }

    @Test
    public void testGetMisses()
    {
        stat.setMisses(5);
        assertEquals(5, stat.getMisses());
    }

    @Test
    public void testGetHitTaken()
    {
        stat.setHitTaken(6);
        assertEquals(6, stat.getHitTaken());
    }

    @Test
    public void testGetSpinTime()
    {
        stat.setSpinTime(7);
        assertEquals(7, stat.getSpinTime());
    }

    @Test
    public void testGetSpinVictory()
    {
        stat.setSpinVictory(8);
        assertEquals(8, stat.getSpinVictory());
    }

    @Test
    public void testGetSpinLose()
    {
        stat.setSpinLose(9);
        assertEquals(9, stat.getSpinLose());
    }

    @Test
    public void testGetLightningWin()
    {
        stat.setLightningWin(10);
        assertEquals(10, stat.getLightningWin());
    }

    @Test
    public void testGetLightningUsed()
    {
        stat.setLightningUsed(11);
        assertEquals(11, stat.getLightningUsed());
    }

    @Test
    public void testGetLightningLose()
    {
        stat.setLightningLose(12);
        assertEquals(12, stat.getLightningLose());
    }

    @Test
    public void testGetGoldVictory()
    {
        stat.setGoldVictory(13);
        assertEquals(13, stat.getGoldVictory());
    }

    @Test
    public void testGetGoldLose()
    {
        stat.setGoldLose(16);
        assertEquals(16, stat.getGoldLose());
    }

    @Test
    public void testGetGoldTime()
    {
        stat.setGoldTime(14);
        assertEquals(14, stat.getGoldTime());
    }

    @Test
    public void testGetHealReceived()
    {
        stat.setHealReceived(15);
        assertEquals(15, stat.getHealReceived(), 0.001);
    }
}
