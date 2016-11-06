package model;

import java.util.Observable;
import java.util.Observer;

public class Bot extends Character implements Observer
{

    public Bot(Position position)
    {
        super(position);
    }

    public void loop()
    {
        while(true)
        {
            setPosition(new Position(getPosition().getX()-100,getPosition().getY()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            setPosition(new Position(getPosition().getX()+100,getPosition().getY()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public String toString()
    {
        return "Bot";
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Position) {
            if (position.distanceTo((Position)arg) < 100) {
                System.out.println("Bot detected that player is close");
            }
        }
    }
}
