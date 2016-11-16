package model;

import model.Command.Action;

public class StrategyDumb implements Strategy
{
    private int i = 0;
    private int direction = -1;
    private Character player;
    private Character bot;

    public StrategyDumb(Character player, Character bot)
    {
        this.player = player;
        this.bot = bot;
    }

    @Override
    public Command getCommand() {
        i += direction;
        Action action = Action.NONE;
        if(i%30 == 0) {
            action = Action.SLASH;
        }
        if (i%50 == 0) {
            direction = -direction;
        }
        Command c;
//        if (player.getPosition().distanceTo(bot.getPosition()) < 100) {
            c = new Command(0,direction, action);
//        } else {
//        }
        c = new Command(direction,0, action);
        return c;
    }
}
