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

    public Engine(Character player, Bot bot)
    {
        this.player = player;
        this.bot = bot;
    }

    public void init()
    {
        System.out.println("====================GAME STARTED====================");
        bot.setStrategy(new StrategyDumb(player,bot));
        player.setHealth(75);
    }

    public void run(Command c)
    {
        player.move(c);
        bot.move();
    }

    @Override
    public void update(Observable o, Object arg)
    {
        // some logic here
    }
}

