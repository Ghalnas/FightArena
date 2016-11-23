package controller;

import model.*;
import model.Character;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
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
    private int deathCpt;
    private double width,height;
    private boolean damageInstancePlayer,damageInstanceBot;
    private Item item;
    private Character itemUser;
    private Character target;
    private int [] tabScores;
    private final int PLAYER = 0;
    private final int BOT = 1;
    private final Item.ItemType[] itemTypes = {Item.ItemType.SPIN, Item.ItemType.LIGHTNING, Item.ItemType.GOLD, Item.ItemType.HEAL};
    private StatsWriter statsWriter;
    private String pseudoPlayer;
    public enum Event { NAMED }
    private double healReceived;
    private int timerGold, timerSpin, hit, miss, hitTaken, lightningUses;
    private boolean loseSpin, winSpin, winLightning, loseLightning, winGold, loseGold;
    
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
        timerGold = 0;
        timerSpin = 0;
        hit = 0;
        miss = 0;
        hitTaken = 0;
        lightningUses = 0;
        healReceived = 0;
        loseSpin = false;
        winSpin = false;
        winLightning = false;
        loseLightning = false;
        winGold = false;
        loseGold = false;
        this.width = width;
        this.height = height;
        this.damageInstancePlayer = false;
        this.damageInstanceBot = false;
        this.tabScores = new int[]{0,0};
        statsWriter = new StatsWriter();
    }

    public void init()
    {
        System.out.println("====================GAME STARTED====================");
        bot.setStrategy(new StrategyEpic(player, bot, item));
    }

    public void reinit()
    {
        if (player.isDead()) {
            tabScores[BOT]++;
            statsWriter.addLose(pseudoPlayer);
        } else {
            tabScores[PLAYER]++;
            statsWriter.addVictory(pseudoPlayer);
        }
        setChanged();
        notifyObservers(tabScores);

        if(timerSpin > 0)
            timerSpin++;
        if(timerGold > 0)
            timerGold++;
        statsWriter.addGoldTime(pseudoPlayer,timerGold);
        statsWriter.addSpinTime(pseudoPlayer,timerSpin);
        statsWriter.addHealReceived(pseudoPlayer,healReceived);
        if(winGold) {
            statsWriter.addGoldVictory(pseudoPlayer);
        } else if (loseGold) {
            statsWriter.addGoldLose(pseudoPlayer);
        } else if (winLightning) {
            statsWriter.addLightningWin(pseudoPlayer);
        } else if (loseLightning) {
            statsWriter.addLightningLose(pseudoPlayer);
        } else if (winSpin) {
            statsWriter.addSpinVictory(pseudoPlayer);
        } else if (loseSpin) {
            statsWriter.addSpinLose(pseudoPlayer);
        }
        statsWriter.addHit(pseudoPlayer,hit);
        statsWriter.addMiss(pseudoPlayer, miss);
        statsWriter.addHitTaken(pseudoPlayer,hitTaken);
        statsWriter.addLightningUse(pseudoPlayer,lightningUses);
        statsWriter.persist();

        player.initChar();
        bot.initChar(new StrategyEpic(player, bot, item));
        slashCptBot = 0;
        slashCptPlayer = 0;
        frameCpt = 1;
        spinCpt = 0;
        lightningCpt = 0;
        goldCpt = 0;
        deathCpt = 0;
        hit = 0;
        miss = 0;
        hitTaken = 0;
        lightningUses = 0;
        damageInstanceBot = false;
        damageInstancePlayer = false;
        itemUser = null;
        target = null;

        timerGold = 0;
        timerSpin = 0;
        healReceived = 0;
        loseSpin = false;
        winSpin = false;
        winLightning = false;
        loseLightning = false;
        winGold = false;
        loseGold = false;

    }

    public void run(Command c)
    {
        frameCpt++;
        if (deathCpt== 40) {
            reinit();
        }
        if (bot.isDead() || player.isDead()) {
            deathCpt++;
        }

        if (goldCpt > 0 && !itemUser.isDead()) {
            goldCpt++;
            if (itemUser instanceof Player) {
                timerGold++;
            }
            if (bot.isDead()) {
                winGold = true;
            } else if(player.isDead()) {
                loseGold = true;
            }
            if (goldCpt == goldFrames) {
                goldCpt = 0;
                itemUser.stopGold();

                itemUser = null;
                target = null;
            }
        }
        if (lightningCpt > 0 && !itemUser.isDead()) {
            lightningCpt++;
            itemUser.lightning();
            if (bot.isDead()) {
                winLightning = true;
            } else if(player.isDead()) {
                loseLightning = true;
            }
            if (lightningCpt == 40) {
                lightningCpt = 0;
                itemUser.stopLightning();
                itemUser = null;
                target = null;
            }
        }
        if (spinCpt > 0 && !itemUser.isDead()) {
            spinCpt++;
            if (itemUser instanceof Player) {
                timerSpin++;
            }
            if (bot.isDead()) {
                winSpin = true;
            } else if(player.isDead()) {
                loseSpin = true;
            }
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

        if (item.getType() == null && frameCpt%600 == 0 && deathCpt == 0) {
            Random r = new Random();
            int rX = r.nextInt((int)width-50-50)+50;
            int rY = r.nextInt((int)height-100-100)+100;
            item.init(itemTypes[r.nextInt(itemTypes.length)],new Position(rX,rY));
        }
        if (slashCptBot > 0 && !bot.isSpinning() && !bot.isDead()) {
            slashCptBot+=1;
            bot.slash();
            if (slashCptBot == slashFrames -1) {
                slashCptBot = 0;
                damageInstanceBot = false;
                bot.endSlash();
            }
        }

        if (slashCptPlayer > 0 && !player.isSpinning() && !player.isDead()) {
            slashCptPlayer++;
            player.slash();
            if (slashCptPlayer == slashFrames -1) {
                slashCptPlayer = 0;
                if (!damageInstancePlayer) {
                    miss++;
                }
                damageInstancePlayer = false;
                player.endSlash();
                hit++;
            }
        }

        if (!player.isDead()) {
            player.move(c);
        }
        if (!bot.isDead()) {
            bot.move();
        }
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
        return !receiver.isDead() && receiver.getHitbox().collision(attacker.getSword()) || receiver.getHitbox().collision(attacker.getHitbox());
    }

    private boolean checkCollisionItem(Character character)
    {
        return !character.isDead() && character.getHitbox().collision(item.getHitbox());
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
                    lightningUses++;
                } else {
                    target = player;
                }
                if (target.getHitbox().collision(itemUser.getLightning())) {
                    target.damaged(target.getHealth());
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
                break;
            case HEAL:
                double low = character.getHealth();
                character.heal();
                double high = character.getHealth();
                itemUser = character;
                item.remove();
                if (character instanceof  Player) {
                    healReceived += (high - low);
                    target = bot;
                } else {
                    target = player;
                }
        }

    }

    public void setPseudo(String pseudo) {
        pseudoPlayer = pseudo;
        setChanged();
        notifyObservers(Event.NAMED);
    }

    public String getPseudo() {
        return pseudoPlayer;
    }
}

