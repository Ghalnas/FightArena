package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Character;
import model.Character.Event;

import java.util.Observable;
import java.util.Observer;

public class CharacterPrinter implements JavaFXPrinter, Observer
{
    private Character character;
    private int maxHealth;
    private final int charWidth, charHeight;
    private ImageView playerView;
    private CharacterSprite characterSprite;

    public CharacterPrinter(Character c, int charWidth, int charHeight)
    {
        this.character = c;
        this.charWidth = charWidth;
        this.charHeight = charHeight;
        this.maxHealth = c.getHealth();
        characterSprite = new CharacterSprite(c,charWidth,charHeight);
        playerView = characterSprite.getCharacterSprite();
        playerView.setTranslateX(character.getPosition().getX());
        playerView.setTranslateY(character.getPosition().getY());
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
        group.getChildren().addAll(playerView,remainingHealth,missingHealth);

        return group;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Event) {
            if(arg == Event.MOVED) {
                playerView = characterSprite.getMovingCharacterSprite();
            } else if(arg == Event.STOPPED) {
                playerView = characterSprite.getCharacterSprite();

            }
            playerView.setTranslateX(character.getPosition().getX());
            playerView.setTranslateY(character.getPosition().getY());
        }
    }
}
