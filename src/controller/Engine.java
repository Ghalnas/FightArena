package controller;

import model.*;
import model.Character;

public class Engine
{

    public enum Direction {
        UP,RIGHT,DOWN,LEFT;
    }

    private Arena arena;
    private Character player;
    private Bot bot;

    public Engine(Arena arena,Character player, Bot bot)
    {
        this.arena = arena;
        this.player = player;
        this.bot = bot;
    }

    public void run()
    {
        System.out.println("====================GAME STARTED====================");
        bot.loop();
    }


    public void movePlayer(Direction dir)
    {
        int currentX = player.getPosition().getX();
        int currentY = player.getPosition().getY();
        switch (dir) {
            case UP:
                player.setPosition(new Position(currentX,currentY-10));
                break;
            case RIGHT:
                player.setPosition(new Position(currentX+10,currentY));
                break;
            case DOWN:
                player.setPosition(new Position(currentX,currentY+10));
                break;
            case LEFT :
                player.setPosition(new Position(currentX-10,currentY));
                break;
        }
    }
}
