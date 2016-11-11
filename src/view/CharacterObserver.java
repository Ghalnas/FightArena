package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Character;
import model.Player;

public class CharacterObserver  implements ObserverJavaFX
{
    private Character character;
    private int maxHealth;
    private Rectangle player;
    private final int charWidth, charHeight;

    public CharacterObserver(Character c)
    {
        this.character = c;
        charWidth = 30;
        charHeight = 40;
        Color color = c instanceof Player ? Color.BLUE : Color.MAGENTA;
        this.maxHealth = c.getHealth();
        player = new Rectangle(charWidth, charHeight);
        player.setFill(color);

    }

    @Override
    public Node getNode()
    {
        Group group = new Group();

        int charRemainingHealth = (charWidth * character.getHealth())/maxHealth;
        Rectangle remainingHealth = new Rectangle(character.getPosition().getX(), character.getPosition().getY()-10, charRemainingHealth,3);
        remainingHealth.setFill(Color.LIGHTGREEN);
        Rectangle missingHealth = new Rectangle(character.getPosition().getX()+charRemainingHealth, character.getPosition().getY()-10, charWidth -charRemainingHealth,3);
        missingHealth.setFill(Color.RED);

        group.getChildren().addAll(player,remainingHealth,missingHealth);

        player.setTranslateX(character.getPosition().getX());
        player.setTranslateY(character.getPosition().getY());

        return group;
    }
}
