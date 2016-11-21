package model;

import model.Command.Action;

public class StrategyMedium implements Strategy
{
    private Character player;
    private Character bot;
    private Item item;

    public StrategyMedium(Character player, Character bot, Item item)
    {
        this.player = player;
        this.bot = bot;
        this.bot.setSpeed(this.bot.getSpeed() * 0.75);
        this.item = item;
    }

    private Command applyStrategy()
    {
        Action action = this.getAction();

        double dirX = player.getPosition().getX();
        double dirY = player.getPosition().getY();

        if (item.getHitbox() != null) {
            dirX = item.getHitbox().getPosition().getX();
            dirY = item.getHitbox().getPosition().getY();
        }

        double botX = bot.getPosition().getX();
        double botY = bot.getPosition().getY();
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

        return new Command(posX, posY, action);
    }

    private Action getAction()
    {
        return (player.getPosition().distanceTo(bot.getPosition()) <= 50) ? Action.SLASH : Action.NONE;
    }

    @Override
    public Command getCommand()
    {
        return this.applyStrategy();
    }
}
