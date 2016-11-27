package model;

import org.junit.Before;
import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;

public class StatsWriterTest
{
    private StatsWriter statsWriter;

    @Before
    public void setUp()
    {
        this.statsWriter = new StatsWriter();
    }

    @BeforeClass
    public static void testClassName()
    {
        assertEquals("model.StatsWriter", StatsWriter.class.getName());
    }


}
