package view;

import model.Character;
import model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel implements KeyListener {
    private Character player;
    private Character bot;

    public MyPanel(int width, int height, Character player, Character bot) {
        this.player = player;
        this.bot = bot;
        this.setPreferredSize(new Dimension(width,height));
        addKeyListener(this);
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLUE);
        g.clearRect(0, 0, getWidth(), getHeight());
        g.drawRect(player.getPosition().getX(),player.getPosition().getY(),15,30);
        g.drawRect(bot.getPosition().getX(),bot.getPosition().getY(),15,30);
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
}