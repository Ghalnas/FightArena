package model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;

public class CommandTest
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private Command command;

    @Before
    public void setUp()
    {
        this.command = new Command(0.3, 0.5, Command.Action.NONE);
    }

    @BeforeClass
    public static void testClassName()
    {
        assertEquals("model.Command", Command.class.getName());
    }

    @Test
    public void testInvalidParameters()
    {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("x and y must be between -1 and 1");
        new Command(2, 3, Command.Action.NONE);
    }

    @Test
    public void testGetY()
    {
        assertEquals(0.5, this.command.getY(), 0.001);
    }

    @Test
    public void testGetX()
    {
        assertEquals(0.3, this.command.getX(), 0.001);
    }

    @Test
    public void testSetY()
    {
        this.command.setY(0.1);
        assertEquals(0.1, this.command.getY(), 0.001);
    }

    @Test
    public void testSetX()
    {
        this.command.setX(0.8);
        assertEquals(0.8, this.command.getX(), 0.001);
    }

    @Test
    public void testGetAction()
    {
        assertEquals(Command.Action.NONE, this.command.getAction());
    }

    @Test
    public void testSetAction()
    {
        this.command.setAction(Command.Action.SLASH);
        assertEquals(Command.Action.SLASH, this.command.getAction());
    }
}
