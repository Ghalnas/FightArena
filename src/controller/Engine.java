package controller;

import main.MainJavaFX;
import model.*;
import model.Character;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**<
 * Class Engine
 */
public class Engine extends Observable implements Observer
{
    private Character player;
    private Bot bot;
    private int frameCpt;
    private int slashFrames, spinFrames, goldFrames;
    private int slashCptPlayer;
    private int slashCptBot;
    private int lightningCpt;
    private int goldCpt;
    private int spinCpt;
    private double width,height;
    private boolean damageInstancePlayer,damageInstanceBot;
    private Item item;
    private Character itemUser;
    private Character target;
    private int [] tabScores;
    private final int PLAYER = 0;
    private final int BOT = 1;
    private final Item.ItemType[] itemTypes = {Item.ItemType.SPIN, Item.ItemType.LIGHTNING, Item.ItemType.GOLD};

    public Engine(Character player, Bot bot, Item item, int slashFrames, int spinFrames, int goldFrames, double width, double height)
    {
        this.player = player;
        this.bot = bot;
        this.item = item;
        this.slashFrames = slashFrames;
        this.spinFrames = spinFrames;
        this.goldFrames = goldFrames;
        this.item = item;
        this.slashCptPlayer = 0;
        this.slashCptBot = 0;
        lightningCpt = 0;
        frameCpt = 1;
        spinCpt = 0;
        goldCpt = 0;
        this.width = width;
        this.height = height;
        this.damageInstancePlayer = false;
        this.damageInstanceBot = false;
        this.tabScores = new int[]{0,0};
    }

    public void init()
    {
        System.out.println("====================GAME STARTED====================");
        bot.setStrategy(new StrategyEpic(player, bot, item));
    }

    public void reinit()
    {
        player.initChar();
        bot.initChar(new StrategyEpic(player, bot, item));
        slashCptBot = 0;
        slashCptPlayer = 0;
        frameCpt = 1;
        spinCpt = 0;
        lightningCpt = 0;
        goldCpt = 0;
        damageInstanceBot = false;
        damageInstancePlayer = false;
        itemUser = null;
        target = null;
    }

    public void run(Command c)
    {
        frameCpt++;
        if (bot.getHealth() <= 0) {
            setChanged();
            tabScores[PLAYER]++;
            notifyObservers(tabScores);
            reinit();
        }
        if (player.getHealth() <= 0) {
            setChanged();
            tabScores[BOT]++;
            notifyObservers(tabScores);
            reinit();
        }
        if (goldCpt > 0) {
            goldCpt++;
            if (goldCpt == goldFrames) {
                goldCpt = 0;
                itemUser.stopGold();
                itemUser = null;
                target = null;
            }
        }
        if (lightningCpt > 0) {
            lightningCpt++;
            itemUser.lightning();
            if (lightningCpt == 40) {
                lightningCpt = 0;
                itemUser.stopLightning();
                itemUser = null;
                target = null;
            }
        }
        if (spinCpt > 0) {
            spinCpt++;
            itemUser.spin();
            if (spinCpt%10 == 0 && checkCollision(itemUser,target)) {
                target.damaged((5f/3f)*itemUser.getDamage());
            }
            if (spinCpt == spinFrames) {
                spinCpt = 0;
                itemUser.stopSpin();
                itemUser = null;
                target = null;
            }
        }
        if (item.getType() != null && checkCollisionItem(player)) {
            useItem(player);
        }
        if (item.getType() != null && checkCollisionItem(bot)) {
            useItem(bot);
        }

        if (item.getType() == null && frameCpt%600 == 0) {
            Random r = new Random();
            int rX = r.nextInt((int)width-50-50)+50;
            int rY = r.nextInt((int)height-100-100)+100;
            item.init(itemTypes[r.nextInt(itemTypes.length)],new Position(rX,rY));
        }
        if (slashCptBot > 0 && !bot.isSpinning()) {
            slashCptBot+=1;
            bot.slash();
            if (slashCptBot == slashFrames -1) {
                slashCptBot = 0;
                damageInstanceBot = false;
                bot.endSlash();
            }
        }

        if (slashCptPlayer > 0 && !player.isSpinning()) {
            slashCptPlayer++;
            player.slash();
            if (slashCptPlayer == slashFrames -1) {
                slashCptPlayer = 0;
                damageInstancePlayer = false;
                player.endSlash();
            }
        }

        player.move(c);
        bot.move();
    }

    @Override
    public void update(Observable o, Object arg)
    {
        checkLimits((Character)o);
        if (arg == Command.Action.SLASH) {
            if (o instanceof Player && !damageInstancePlayer && checkCollision(player,bot)) {
                bot.damaged(player.getDamage());
                damageInstancePlayer = true;
            } else if(o instanceof Bot && !damageInstanceBot && checkCollision(bot,player)) {
                player.damaged(bot.getDamage());
                damageInstanceBot = true;
            }
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
        if (c.getPosition().getX() < 70) {
            c.getPosition().setX(70);
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
        return receiver.getHitbox().collision(attacker.getSword()) || receiver.getHitbox().collision(attacker.getHitbox());
    }
    private boolean checkCollisionItem(Character character)
    {
        return character.getHitbox().collision(item.getHitbox());
    }

    private void useItem(Character character)
    {
        switch (item.getType()) {
            case SPIN:
                character.startSpin();
                itemUser = character;
                spinCpt++;
                item.remove();
                if (character instanceof  Player) {
                    target = bot;
                } else {
                    target = player;
                }
                break;
            case LIGHTNING:
                lightningCpt++;
                character.startLightning();
                itemUser = character;
                item.remove();
                if (character instanceof  Player) {
                    target = bot;
                } else {
                    target = player;
                }
                if (target.getHitbox().collision(itemUser.getLightning())) {
                    target.setHealth(0);
                }
                break;
            case GOLD:
                goldCpt++;
                character.startGold();
                itemUser = character;
                item.remove();
                if (character instanceof  Player) {
                    target = bot;
                } else {
                    target = player;
                }
        }

    }
}

