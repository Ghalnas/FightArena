package model;

import model.Command.Action;

public class StrategyLow implements Strategy
{
    private Character player;
    private Character bot;

    public StrategyLow(Character player, Character bot)
    {
        this.player = player;
        this.bot = bot;
        this.bot.setSpeed(this.bot.getSpeed() * 0.50);
    }

    private Command applyStrategy()
    {
        Action action = this.getAction();

        double dirX = player.getPosition().getX();
        double dirY = player.getPosition().getY();

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
