package model;

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
        if (i%30 == 0) {
            direction = -direction;
        }
        Command c;
        if (player.getPosition().distanceTo(bot.getPosition()) < 100) {
            c = new Command(0,direction);
        } else {
            c = new Command(direction,0);
        }
        return c;
    }
}
