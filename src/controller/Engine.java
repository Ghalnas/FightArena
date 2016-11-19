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
    private int slashFrames;
    private int slashCptPlayer;
    private int slashCptBot;
    private double width,height;
    private boolean damageInstancePlayer,damageInstanceBot;
    private int [] tabScores;

    public Engine(Character player, Bot bot, int slashFrames, double width, double height)
    {
        this.player = player;
        this.bot = bot;
        this.slashFrames = slashFrames;
        this.slashCptPlayer = 0;
        this.slashCptBot = 0;
        this.width = width;
        this.height = height;
        this.damageInstancePlayer = false;
        this.damageInstanceBot = false;
        tabScores = new int[]{0,0};
    }

    public void init()
    {
        System.out.println("====================GAME STARTED====================");
        bot.setStrategy(new StrategyEpic(player, bot));
    }

    public void reinit()
    {
        player.initChar();
        bot.initChar(new StrategyEpic(player, bot));
        slashCptBot = 0;
        slashCptPlayer = 0;
        damageInstanceBot = false;
        damageInstancePlayer = false;
    }

    public void run(Command c)
    {
        if (slashCptBot > 0) {
            slashCptBot+=1;
            bot.slash();
            if (slashCptBot == slashFrames -1) {
                slashCptBot = 0;
                damageInstanceBot = false;
                bot.endSlash();
            }
        }

        if (slashCptPlayer > 0) {
            slashCptPlayer++;
            player.slash();
            if (slashCptPlayer == slashFrames -1) {
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
            checkLimits((Character)o);
            if (o instanceof Player && !damageInstancePlayer && checkCollision(player,bot)) {
                bot.setHealth(bot.getHealth()-player.getDamage());
                damageInstancePlayer = true;
                if (bot.getHealth() <= 0) {
                    setChanged();
                    tabScores[0]++;
                    notifyObservers(tabScores);
                    reinit();
                }
            } else if(o instanceof Bot && !damageInstanceBot && checkCollision(bot,player)) {
                player.setHealth(player.getHealth()-bot.getDamage());
                damageInstanceBot = true;
                if (player.getHealth() <= 0) {
                    setChanged();
                    tabScores[1]++;
                    notifyObservers(tabScores);
                    reinit();
                }
            }
        }
        if(arg == Character.Event.MOVED) {
            checkLimits((Character)o);
        }
        if(arg == Character.Event.ASKSLASH) {
            if(o instanceof Player  && slashCptPlayer == 0) {
                slashCptPlayer++;
                player.slash();
            } else if (o instanceof Bot && slashCptBot == 0) {
                slashCptBot++;
                bot.slash();
            }
        }
    }

    private void checkLimits(Character c)
    {
        if (c.getPosition().getX() < 10) {
            c.getPosition().setX(10);
        }
        if (c.getPosition().getX() > width-70) {
            c.getPosition().setX(width-70);
        }
        if (c.getPosition().getY() < 70) {
            c.getPosition().setY(70);
        }
        if (c.getPosition().getY() > height-100) {
            c.getPosition().setY(height - 100);
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

