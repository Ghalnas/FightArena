package controller;

import listener.CharacterListener;
import model.*;
import model.Character;
import view.*;

import javax.swing.*;

public class Engine implements CharacterListener
{
    private Character player;
    private Character bot;
    private Arena arena;
    private JPanel panel;

    public Engine(int arenaWidth, int arenaHeight, int playerX, int playerY, int botX, int botY)
    {
        arena = new Arena(arenaWidth,arenaHeight);
        player = new Player(new Position(playerX,playerY));
        bot = new Bot(new Position(botX,botY));

        player.addListener((CharacterListener)bot);
        player.addListener(this);

        bot.addListener(this);

        panel = new MyPanel(arenaWidth,arenaHeight, player, bot);
        JFrame f = new JFrame();
        f.getContentPane().add(panel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

    public void run()
    {
        System.out.println("====================GAME STARTED====================");
        System.out.println("Player"+player.getPosition());
        System.out.println("Bot"+bot.getPosition());
//        System.out.println(toString());
//        player.setPosition(new Position(0,0));
        System.out.println("Player"+player.getPosition());
//        System.out.println(toString());
//        bot.setPosition(new Position(1,0));
        System.out.println("Bot"+bot.getPosition());
//        System.out.println(toString());
    }

    @Override
    public void hasMoved(Character character) {
        // update view here
        System.out.println(character+" has moved");
        panel.repaint();
    }

    @Override
    public String toString()
    {
        String s = "";
        for (int i = -1 ; i <= arena.getHeight() ; i++) {
            for (int j = -1; j <= arena.getWidth() ; j++) {
                String c = "   ";
                if (j == -1) {
                    c = "|  ";
                }
                if (j == arena.getWidth()) {
                    c = "  |";
                }
                if (i == -1 || i == arena.getHeight()) {
                    c = "===";
                }
                if (i == player.getPosition().getY() && j == player.getPosition().getX()) {
                    c = " O ";
                }
                if (i == bot.getPosition().getY() && j == bot.getPosition().getX()) {
                    c = " X ";
                }
                s += c;
            }
            s += "\n";
        }
        return s;
    }
}
