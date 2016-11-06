package view;

import model.Arena;
import model.Character;
import model.Player;
import model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel implements KeyListener {
    private Character player;
    private Character bot;
    private Arena arena;

    public MyPanel(Arena arena, Character player, Character bot) {
        this.arena = arena;
        this.player = player;
        this.bot = bot;
        this.setPreferredSize(new Dimension(arena.getWidth(),arena.getHeight()));
        addKeyListener(this);
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0,0, getWidth(),getHeight());

        paintCharacter(player,g);
        paintCharacter(bot,g);
    }

    public void keyPressed(KeyEvent e) {
        int currentX = player.getPosition().getX();
        int currentY = player.getPosition().getY();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                player.setPosition(new Position(currentX,currentY-10));
                break;
            case KeyEvent.VK_DOWN:
                player.setPosition(new Position(currentX,currentY+10));
                break;
            case KeyEvent.VK_LEFT:
                player.setPosition(new Position(currentX-10,currentY));
                break;
            case KeyEvent.VK_RIGHT :
                player.setPosition(new Position(currentX+10,currentY));
                break;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e)
    {

    }

    public void keyTyped(KeyEvent e)
    {

    }

    private void paintCharacter(Character c, Graphics g)
    {
        int charWidth = 30;
        int charHeight = 40;

        int charRemainingHealth = (charWidth *c.getHealth())/c.getMaxHealth();
        g.setColor(Color.GREEN);
        g.fillRect(c.getPosition().getX(),c.getPosition().getY()-10,charRemainingHealth,3);
        g.setColor(Color.RED);
        g.fillRect(c.getPosition().getX()+charRemainingHealth,c.getPosition().getY()-10, charWidth -charRemainingHealth,3);
        Color col = c instanceof Player ? Color.BLUE : Color.MAGENTA;
        g.setColor(col);
        g.fillRect(c.getPosition().getX(),c.getPosition().getY(), charWidth,charHeight);
    }
}