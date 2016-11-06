package view;

import controller.Engine;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame
{
    private Engine engine;

    public Window(Engine engine, JPanel panel)
    {
        this.engine = engine;
        panel.addKeyListener(new Keyboard());
        this.getContentPane().add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private class Keyboard implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    engine.movePlayer(Engine.Direction.UP);
                    break;
                case KeyEvent.VK_RIGHT:
                    engine.movePlayer(Engine.Direction.RIGHT);
                    break;
                case KeyEvent.VK_DOWN :
                    engine.movePlayer(Engine.Direction.DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    engine.movePlayer(Engine.Direction.LEFT);
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }




}
