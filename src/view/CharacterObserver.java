package view;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Character;
import model.Player;

import java.awt.*;

public class CharacterObserver  implements ObserverJavaFX
{
    private Character c;
    private int maxHealth;
    private Rectangle player;

    public CharacterObserver(Character c)
    {
        this.c = c;
        Color color = c instanceof Player ? Color.BLUE : Color.MAGENTA;
        this.maxHealth = c.getHealth();
        player = new Rectangle(30,40);
        player.setFill(color);
    }

    @Override
    public Node getNode() {
        int charWidth = 30;
        int charHeight = 40;

        int charRemainingHealth = (charWidth *c.getHealth())/maxHealth;

        player.setTranslateX(c.getPosition().getX());
        player.setTranslateY(c.getPosition().getY());
        return player;
    }
}
