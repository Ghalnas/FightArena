package controller;

import model.*;
import model.Character;

import java.util.Observable;
import java.util.Observer;

/**
 * Class Engine
 */
public class Engine implements Observer
{
    private Character player;
    private Bot bot;
    private int slash;
    private int slashCptPlayer;
    private int slashCptBot;
    private int width,height;

    public Engine(Character player, Bot bot, int slash, int width, int height)
    {
        this.player = player;
        this.bot = bot;
        this.slash = slash;
        this.slashCptPlayer = 0;
        this.slashCptBot = 0;
        this.width = width;
        this.height = height;
    }

    public void init()
    {
        System.out.println("====================GAME STARTED====================");
        bot.setStrategy(new StrategyDumb(player,bot));
        player.setHealth(75);
    }

    public void run(Command c)
    {
        if (slashCptBot > 0) {
            slashCptBot+=1;
            bot.slash();
            if (slashCptBot == slash-1) {
                slashCptBot = 0;
                bot.endSlash();
            }
        }

        if (slashCptPlayer > 0) {
            slashCptPlayer++;
            player.slash();
            if (slashCptPlayer == slash-1) {
                slashCptPlayer = 0;
                player.endSlash();
            }
        }
        // prevent double speed on diagonal
        if (Math.abs(c.getX()) + Math.abs(c.getY()) > 1.9) {
            c.setX(Math.signum(c.getX()) * Math.PI/4);
            c.setY(Math.signum(c.getY()) * Math.PI/4);
        }
        player.move(c);
        bot.move();
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if(arg == Character.Event.MOVED) {
            if (((Character)o).getPosition().getX() < 5) {
                ((Character) o).getPosition().setX(5);
            }
            if (((Character)o).getPosition().getX() > width-45) {
                ((Character) o).getPosition().setX(width-45);
            }
            if (((Character)o).getPosition().getY() < 5) {
                ((Character) o).getPosition().setY(5);
            }
            if (((Character)o).getPosition().getY() > height-80) {
                ((Character) o).getPosition().setY(height-80);
            }
        }
        if(arg == Character.Event.ASKSLASH && slashCptPlayer == 0) {
            if(o instanceof Player) {
                slashCptPlayer++;
                player.slash();
            } else {
                slashCptBot++;
                bot.slash();
            }
        }
    }
}

