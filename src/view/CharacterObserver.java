package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Character;

public class CharacterObserver  implements ObserverJavaFX
{
    private Character character;
    private int maxHealth;
    private final int charWidth, charHeight;

    public CharacterObserver(Character c)
    {
        this.character = c;
        charWidth = 17;
        charHeight = 26;
        this.maxHealth = c.getHealth();

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

        ImageView player = CharacterSprite.getCharacterSprite(character);
        player.setTranslateX(character.getPosition().getX());
        player.setTranslateY(character.getPosition().getY());
        group.getChildren().addAll(player,remainingHealth,missingHealth);

        return group;
    }
}
