package controller;

import model.*;
import model.Character;

import java.util.Observable;
import java.util.Observer;

/**
 * Class Engine
 */
public class Engine extends Observable implements Observer
{
    private Character player;
    private Bot bot;
    private int slash;
    private int slashCptPlayer;
    private int slashCptBot;
    private double width,height;
    private boolean damageInstancePlayer,damageInstanceBot;

    public Engine(Character player, Bot bot, int slash, double width, double height)
    {
        this.player = player;
        this.bot = bot;
        this.slash = slash;
        this.slashCptPlayer = 0;
        this.slashCptBot = 0;
        this.width = width;
        this.height = height;
        this.damageInstancePlayer = false;
        this.damageInstanceBot = false;
    }

    public void init()
    {
        System.out.println("====================GAME STARTED====================");
        bot.setStrategy(new StrategyDumb(player,bot));
        player.setHealth(75);
    }

    public void run(Command c)
    {
        if(player.getHealth() <= 0) {
            setChanged();
            notifyObservers("dhezuoh");
        }
        if (slashCptBot > 0) {
            slashCptBot+=1;
            bot.slash();
            if (slashCptBot == slash-1) {
                slashCptBot = 0;
                damageInstanceBot = false;
                bot.endSlash();
            }
        }

        if (slashCptPlayer > 0) {
            slashCptPlayer++;
            player.slash();
            if (slashCptPlayer == slash-1) {
                slashCptPlayer = 0;
                damageInstancePlayer = false;
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
        if (arg == Command.Action.SLASH) {
            System.out.println(o instanceof Bot && !damageInstanceBot && checkCollision(bot,player));
            System.out.println( !damageInstanceBot);
            System.out.println(checkCollision(bot,player));
            if (o instanceof Player && !damageInstancePlayer && checkCollision(player,bot)) {
                bot.setHealth(bot.getHealth()-20);
                damageInstancePlayer = true;
            } else if(o instanceof Bot && !damageInstanceBot && checkCollision(bot,player)) {
                player.setHealth(bot.getHealth()-20);
                damageInstanceBot = true;
            }
        }
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

    private boolean checkCollision(Character attacker, Character receiver)
    {
        Hitbox sword = null;
        double attackerX = attacker.getPosition().getX();
        double attackerY = attacker.getPosition().getY();
        switch (attacker.getDirection()) {
            case UP:
                sword = new Hitbox(attackerX-5,attackerY-40,20,20);
                break;
            case RIGHT:
                sword = new Hitbox(attackerX+10,attackerY-5,20,20);
                break;
            case DOWN:
                sword = new Hitbox(attackerX-5,attackerY+40,20,20);
                break;
            case LEFT:
                sword = new Hitbox(attackerX-10,attackerY-5,20,20);
                break;
        }
        return receiver.getHitbox().collision(sword) || receiver.getHitbox().collision(attacker.getHitbox());
    }
}

