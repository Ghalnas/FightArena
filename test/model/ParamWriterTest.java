package model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParamWriterTest
{
    private ParamWriter paramWriter;

    @Before
    public void setUp()
    {
        this.paramWriter = new ParamWriter();
    }

    @BeforeClass
    public static void testClassName()
    {
        assertEquals("model.ParamWriter", ParamWriter.class.getName());
    }

    @Test
    public void testWindowWidth()
    {
        this.paramWriter.writeWidth(100);
        assertEquals(100, this.paramWriter.windowWidth, 0.001);
    }

    @Test
    public void testWindowHeight()
    {
        this.paramWriter.writeHeight(100);
        assertEquals(100, this.paramWriter.windowHeight, 0.001);
    }

    @Test
    public void testBotDifficulty()
    {
        this.paramWriter.writeBodDifficulty(100);
        assertEquals(100, this.paramWriter.botDifficulty, 0.001);
    }

    @Test
    public void testSelectRes()
    {
        this.paramWriter.writeSelectedRes(100);
        assertEquals(100, this.paramWriter.selectedRes, 0.001);
    }

    @Test
    public void testFullscreen()
    {
        this.paramWriter.writeFullscreen(true);
        assertTrue(this.paramWriter.fullscreen);
    }

    @Test
    public void testMusicVolume()
    {
        this.paramWriter.writeMusicVol(100);
        assertEquals(100, this.paramWriter.musicVolume, 0.001);
    }

    @Test
    public void testFxVolume()
    {
        this.paramWriter.writeFxVol(100);
        assertEquals(100, this.paramWriter.fxVolume, 0.001);
    }
}
