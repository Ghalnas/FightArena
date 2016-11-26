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
    private Strategy strategy;
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
    private Logger logger;
    private FXPlayer fxPlayer;

    public Engine(Character player, Bot bot, Item item, int strategyIndex, int slashFrames, int spinFrames, int goldFrames, double width, double height, double fxVol)
    {
        this.player = player;
        this.bot = bot;
        this.item = item;
        setStrategy(strategyIndex);
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
        logger = Logger.getInstance();
        statsWriter = new StatsWriter();
        this.fxPlayer = new FXPlayer(fxVol);
    }

    public void setVolFx(double volFx)
    {
        fxPlayer.setVolFx(volFx);
    }


    public void setStrategy(int index)
    {
        switch (index) {
            case 0:
                strategy = new StrategyLow(player,bot,item);
                break;
            case 1:
                strategy = new StrategyLow(player,bot,item);
                break;
            case 2:
                strategy = new StrategyEpic(player,bot,item);
                break;
        }
        bot.setStrategy(strategy);
    }

    public void writeStats()
    {
        String winner = null;
        String itemWin = "";
        if (player.isDead()) {
            tabScores[BOT]++;
            statsWriter.addLose(pseudoPlayer);
            winner = "Bot";
            setChanged();
            notifyObservers(tabScores);
        } else if (bot.isDead()){
            tabScores[PLAYER]++;
            statsWriter.addVictory(pseudoPlayer);
            winner = "Player";
            setChanged();
            notifyObservers(tabScores);
        }


        if(timerSpin > 0)
            timerSpin++;
        if(timerGold > 0)
            timerGold++;

        statsWriter.addGoldTime(pseudoPlayer,timerGold);
        statsWriter.addSpinTime(pseudoPlayer,timerSpin);
        statsWriter.addHealReceived(pseudoPlayer,healReceived);
        if(winGold) {
            itemWin = "with Gold";
            statsWriter.addGoldVictory(pseudoPlayer);
        } else if (loseGold) {
            itemWin = "with Gold";
            statsWriter.addGoldLose(pseudoPlayer);
        } else if (winLightning) {
            itemWin = "with Lightning";
            statsWriter.addLightningWin(pseudoPlayer);
        } else if (loseLightning) {
            itemWin = "with Lightning";
            statsWriter.addLightningLose(pseudoPlayer);
        } else if (winSpin) {
            itemWin = "with Spin";
            statsWriter.addSpinVictory(pseudoPlayer);
        } else if (loseSpin) {
            itemWin = "with Spin";
            statsWriter.addSpinLose(pseudoPlayer);
        }

        if (winner != null) {
            logger.info(winner + " wins " + itemWin + " !");
        }

        statsWriter.addHit(pseudoPlayer,hit);
        statsWriter.addMiss(pseudoPlayer, miss);
        statsWriter.addHitTaken(pseudoPlayer,hitTaken);
        statsWriter.addLightningUse(pseudoPlayer,lightningUses);
        statsWriter.persist();

    }

    public void reinit()
    {
        fxPlayer.reinit();
        player.initChar();
        bot.initChar(strategy);
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
        item.remove();
        timerGold = 0;
        timerSpin = 0;
        healReceived = 0;
        loseSpin = false;
        winSpin = false;
        winLightning = false;
        loseLightning = false;
        winGold = false;
        loseGold = false;
        logger.reset();
    }

    public void run(Command c)
    {
        frameCpt++;
        if (bot.isDead() || player.isDead()) {
            deathCpt++;
            if(deathCpt == 1) {
                fxPlayer.death();
            }
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
                fxPlayer.reinit();
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
                fxPlayer.reinit();
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
                double dmg = (5f/3f)*itemUser.getDamage();
                target.damaged(dmg);
                if (deathCpt < slashFrames) {
                    String damage = String.format("%.2f", dmg);
                    String wounded = target instanceof Player ? "Player" : "Bot";
                    logger.info("Spin deals " + damage + " damage to " + wounded + " !");
                }
            }
            if (spinCpt == spinFrames) {
                fxPlayer.reinit();
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

        if (item.getType() == null && frameCpt%300 == 0 && deathCpt == 0) {
            Random r = new Random();
            int rX = r.nextInt((int)width-91-91)+91;
            int rY = r.nextInt((int)height-100-100)+100;
            item.init(itemTypes[r.nextInt(itemTypes.length)],new Position(rX,rY));
        }
        if (slashCptBot > 0 && !bot.isSpinning() && !bot.isDead()) {
            if (slashCptBot == 1) {
                fxPlayer.swordSound();
            }
            slashCptBot+=1;
            bot.slash();
            if (slashCptBot == slashFrames -1) {
                slashCptBot = 0;
                String hitMessage = null;
                if (!damageInstanceBot && deathCpt < slashFrames) {
                    hitMessage = "Bot misses !";
                    miss++;
                } else if (damageInstanceBot && deathCpt < slashFrames){
                    double dmg = player.isGold() ? bot.getDamage()/2 : bot.getDamage();
                    hitMessage = "Bot hits player for " + dmg + " damage !";
                }
                damageInstanceBot = false;
                bot.endSlash();
                if (hitMessage != null) {
                    logger.info(hitMessage);
                }
            }
        }

        if (slashCptPlayer > 0 && !player.isSpinning() && !player.isDead()) {
            if (slashCptPlayer == 1) {
                fxPlayer.swordSound();
            }
            slashCptPlayer++;
            player.slash();
            if (slashCptPlayer == slashFrames -1) {
                slashCptPlayer = 0;
                String hitMessage = null;
                if (!damageInstancePlayer && deathCpt < slashFrames) {
                    hitMessage = "Player misses !";
                    miss++;
                } else if (damageInstancePlayer && deathCpt < slashFrames) {
                    double dmg = bot.isGold() ? player.getDamage()/2 : player.getDamage();
                    hitMessage = "Player hits bot for " + dmg + " damage !";
                }
                damageInstancePlayer = false;
                player.endSlash();
                hit++;
                if (hitMessage != null) {
                    logger.info(hitMessage);
                }
            }
        }

        if (!player.isDead()) {
            player.move(c);
        }
        if (!bot.isDead()) {
            bot.move();
        }
        if (deathCpt == slashFrames) {
            writeStats();
        }
        if (deathCpt== 40) {
            reinit();
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
        frameCpt = 1;
        String itemMessage = null;
        switch (item.getType()) {
            case SPIN:
                character.startSpin();
                fxPlayer.playSpin();
                itemUser = character;
                spinCpt++;
                item.remove();
                itemMessage = " used Spin !";
                if (character instanceof  Player) {
                    target = bot;
                } else {
                    target = player;
                }
                break;
            case LIGHTNING:
                lightningCpt++;
                fxPlayer.playLightning();
                character.startLightning();
                itemUser = character;
                item.remove();
                itemMessage = " used Lightning !";
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
                fxPlayer.playGold();
                character.startGold();
                itemUser = character;
                item.remove();
                itemMessage = " used Gold !";
                if (character instanceof  Player) {
                    target = bot;
                } else {
                    target = player;
                }
                break;
            case HEAL:
                double low = character.getHealth();
                character.heal();
                fxPlayer.playHeal();
                double high = character.getHealth();
                itemUser = character;
                item.remove();
                itemMessage = " used healed for  " + String.format("%.2f", high-low) + " HPs !";
                if (character instanceof  Player) {
                    healReceived += (high - low);
                    target = bot;
                } else {
                    target = player;
                }
        }
        String userMessage = itemUser instanceof Player ? "Player" : "Bot";
        logger.info(userMessage+itemMessage);


    }

    public void reinitScores()
    {
        tabScores = new int[]{0,0};
        setChanged();
        notifyObservers(tabScores);
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

