package model;

import model.Command.Action;

public class StrategyEpic implements Strategy
{
    private Character player;
    private Character bot;

    public StrategyEpic(Character player, Character bot)
    {
        this.player = player;
        this.bot = bot;
    }

    private Command applyStrategy()
    {
        Action action = this.getAction();

        double playerX = player.getPosition().getX();
        double playerY = player.getPosition().getY();
        double botX = bot.getPosition().getX();
        double botY = bot.getPosition().getY();
        double posX = 0, posY = 0;

        if (playerY > botY && playerX < botX) {
            posX = Math.abs(botX - playerX) < 5 ? 0 : -1;
            posY = Math.abs(botY - playerY) < 5 ? 0 : 1;
        } else if (playerY < botY && playerX < botX) {
            posX = Math.abs(botX - playerX) < 5 ? 0 : -1;
            posY = Math.abs(botY - playerY) < 5 ? 0 : -1;
        } else if (playerY > botY && playerX > botX) {
            posX = Math.abs(botX - playerX) < 5 ? 0 : 1;
            posY = Math.abs(botY - playerY) < 5 ? 0 : 1;
        } else if ((playerY < botY && playerX > botX) || (playerY < botY && playerX < botX)) {
            posX = Math.abs(botX - playerX) < 5 ? 0 : 1;
            posY = Math.abs(botY - playerY) < 5 ? 0 : -1;
        } else if (playerY == botY && playerX < botX) {
            posX = Math.abs(botX - playerX) < 5 ? 0 : -1;
            posY = 0;
        } else if (playerY == botY && playerX > botX) {
            posX = Math.abs(botX - playerX) < 5 ? 0 : 1;
            posY = 0;
        } else if (playerY > botY && playerX == botX) {
            posX = 0;
            posY = Math.abs(botY - playerY) < 5 ? 0 : 1;
        } else if (playerY < botY && playerX == botX) {
            posX = 0;
            posY = Math.abs(botY - playerY) < 5 ? 0 : -1;
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
