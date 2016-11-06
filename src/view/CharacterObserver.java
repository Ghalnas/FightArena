package view;

import model.Character;
import model.Player;

import java.awt.*;

public class CharacterObserver implements ObserverSwing
{

    private Character c;
    private Color color;
    private int maxHealth;

    public CharacterObserver(Character c)
    {
        this.c = c;
        this.color = c instanceof Player ? Color.BLUE : Color.MAGENTA;
        this.maxHealth = c.getHealth();
    }

    @Override
    public void print(Graphics g)
    {
        int charWidth = 30;
        int charHeight = 40;

        int charRemainingHealth = (charWidth *c.getHealth())/maxHealth;
        g.setColor(Color.GREEN);
        g.fillRect(c.getPosition().getX(),c.getPosition().getY()-10,charRemainingHealth,3);
        g.setColor(Color.RED);
        g.fillRect(c.getPosition().getX()+charRemainingHealth,c.getPosition().getY()-10, charWidth -charRemainingHealth,3);
        g.setColor(color);
        g.fillRect(c.getPosition().getX(),c.getPosition().getY(), charWidth,charHeight);
    }
}
