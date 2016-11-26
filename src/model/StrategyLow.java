package model;

import model.Command.Action;

public class StrategyLow implements Strategy
{
    private Character player;
    private Character bot;
    private Item item;

    public StrategyLow(Character player, Character bot, Item item)
    {
        this.player = player;
        this.bot = bot;
        this.item = item;
    }

    private Command applyStrategy()
    {
        Action action = this.getAction();

        double dirX = player.getPosition().getX();
        double dirY = player.getPosition().getY();

        if (item.getHitbox() != null) {
            if (bot.getHealth() < 100 && item.getType() == Item.ItemType.HEAL) {
                dirX = item.getHitbox().getPosition().getX();
                dirY = item.getHitbox().getPosition().getY();
            }
        }

        double botX = bot.getPosition().getX();
        double botY = bot.getPosition().getY();
        double[] direction;

        if (bot.getHealth() < 50) {
            direction = this.getDirection(dirX, dirY, botX, botY, true);
        } else {
            direction = this.getDirection(dirX, dirY, botX, botY, false);
        }

        return new Command(direction[0], direction[1], action);
    }

    private double[] getDirection(double dirX, double dirY, double botX, double botY, boolean isEscaping)
    {
        double posX = 0, posY = 0;

        if (dirY > botY && dirX < botX) {
            posX = Math.abs(botX - dirX) < 5 ? 0 : -1;
            posY = Math.abs(botY - dirY) < 5 ? 0 : 1;
        } else if (dirY < botY && dirX < botX) {
            posX = Math.abs(botX - dirX) < 5 ? 0 : -1;
            posY = Math.abs(botY - dirY) < 5 ? 0 : -1;
        } else if (dirY > botY && dirX > botX) {
            posX = Math.abs(botX - dirX) < 5 ? 0 : 1;
            posY = Math.abs(botY - dirY) < 5 ? 0 : 1;
        } else if ((dirY < botY && dirX > botX) || (dirY < botY && dirX < botX)) {
            posX = Math.abs(botX - dirX) < 5 ? 0 : 1;
            posY = Math.abs(botY - dirY) < 5 ? 0 : -1;
        } else if (dirY == botY && dirX < botX) {
            posX = Math.abs(botX - dirX) < 5 ? 0 : -1;
            posY = 0;
        } else if (dirY == botY && dirX > botX) {
            posX = Math.abs(botX - dirX) < 5 ? 0 : 1;
            posY = 0;
        } else if (dirY > botY && dirX == botX) {
            posX = 0;
            posY = Math.abs(botY - dirY) < 5 ? 0 : 1;
        } else if (dirY < botY && dirX == botX) {
            posX = 0;
            posY = Math.abs(botY - dirY) < 5 ? 0 : -1;
        }

        if (isEscaping) {
            posX = -posX;
            posY = -posY;
        }

        return new double[] {posX, posY};
    }

    private Action getAction()
    {
        if (bot.isGold()) {
            return Action.SLASH;
        }

        return (player.getPosition().distanceTo(bot.getPosition()) <= 50) ? Action.SLASH : Action.NONE;
    }

    @Override
    public Command getCommand()
    {
        return this.applyStrategy();
    }
}
