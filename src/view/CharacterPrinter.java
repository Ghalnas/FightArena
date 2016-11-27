package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.*;
import model.Character;
import model.Character.Event;

import java.util.Observable;
import java.util.Observer;

public class CharacterPrinter implements JavaFXPrinter, Observer
{
    private Character character;
    private double maxHealth;
    private final int charWidth, charHeight;
    private ImageView playerView;
    private Node lightning;
    private CharacterSprite characterSprite;
    private Group panel;
    private ImageView charac;
    private Rectangle remainingHealth;
    private Rectangle missingHealth;
//    private Group charAndLifeBar;


    public CharacterPrinter(Character c, int charWidth, int charHeight, int slashFrames)
    {
        this.character = c;
        this.charWidth = charWidth;
        this.charHeight = charHeight;
        this.maxHealth = c.getHealth();
        lightning = new ImageView();
        characterSprite = new CharacterSprite(c,charWidth,charHeight,slashFrames);
        playerView = characterSprite.getCharacterSprite();
        playerView.setTranslateX(character.getPosition().getX()-40);
        playerView.setTranslateY(character.getPosition().getY()-40);
        charac = playerView;
        panel = new Group();
//        charAndLifeBar = new Group();
        remainingHealth = new Rectangle();
        remainingHealth.setTranslateX(character.getPosition().getX()-40);
        remainingHealth.setTranslateY(character.getPosition().getY()-40);
        remainingHealth.setWidth(charWidth);
        remainingHealth.setHeight(6);
        remainingHealth.setFill(Color.LIGHTGREEN);
        missingHealth = new Rectangle();
        missingHealth.setHeight(6);
        missingHealth.setFill(Color.RED);
//        charAndLifeBar.getChildren().addAll(charac,remainingHealth,)
        panel.getChildren().addAll(charac,remainingHealth,missingHealth);
    }

    @Override
    public Node getNode()
    {
        return panel;
    }

    @Override
    public void update(Observable o, Object arg) {
        panel.getChildren().remove(charac);
        if (arg instanceof Command.Action) {
            switch ((Command.Action)arg) {
                case SLASH:
                    charac = characterSprite.getSlashCharacterSprite();
                    break;
                case NONE:
                    charac = characterSprite.getCharacterSprite();
                    break;
            }
        } else if (arg instanceof Event) {
            switch ((Event)arg) {
                case DAMAGED:
                    double charRemainingHealth = (charWidth * character.getHealth()) / maxHealth;
                    remainingHealth.setWidth(charRemainingHealth);
                    missingHealth.setTranslateX(character.getPosition().getX() + charRemainingHealth - 40);
                    missingHealth.setWidth(charWidth - charRemainingHealth);
                    break;
                case DEAD:
                    charac = characterSprite.getDead();
                    break;
                case SPIN:
                    charac = characterSprite.getSpinSprite();
                    break;
                case LIGHTNING:
                    panel.getChildren().remove(lightning);
                    lightning = characterSprite.getLightning(character.getLightning());
                    lightning.setTranslateX(character.getLightning().getX() - 40);
                    panel.getChildren().add(lightning);
                    break;
                case STOP_LIGHTNING:
                    panel.getChildren().remove(lightning);
                    break;
                case MOVED:
                    charac = characterSprite.getMovingCharacterSprite();
                    break;
                case STOPPED:
                    charac = characterSprite.getCharacterSprite();
                    break;
            }
        }
        panel.getChildren().add(charac);

        double charRemainingHealth = (charWidth * character.getHealth())/maxHealth;
        remainingHealth.setTranslateX(character.getPosition().getX()-40);
        remainingHealth.setTranslateY(character.getPosition().getY()-40);
        remainingHealth.setWidth(charRemainingHealth);
        missingHealth.setTranslateX(character.getPosition().getX()+charRemainingHealth-40);
        missingHealth.setTranslateY(character.getPosition().getY()-40);
        missingHealth.setWidth(charWidth-charRemainingHealth);
        if (character.isGold()) {
            panel.setScaleX(2);
            panel.setScaleY(2);
        } else {
            panel.setScaleX(1);
            panel.setScaleY(1);
        }
        charac.setTranslateX(character.getPosition().getX()-40);
        charac.setTranslateY(character.getPosition().getY()-40);
    }
}
