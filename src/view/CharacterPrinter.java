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
        remainingHealth = new Rectangle();
        remainingHealth.setTranslateX(character.getPosition().getX()-40);
        remainingHealth.setTranslateY(character.getPosition().getY()-40);
        remainingHealth.setWidth(charWidth);
        remainingHealth.setHeight(6);
        remainingHealth.setFill(Color.LIGHTGREEN);
        missingHealth = new Rectangle();
        missingHealth.setHeight(6);
        missingHealth.setFill(Color.RED);
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
                if (arg == Command.Action.SLASH) {
                    charac = characterSprite.getSlashCharacterSprite();
                } else if (arg == Command.Action.NONE) {
                    charac = characterSprite.getCharacterSprite();
                }
            } else if (arg instanceof Event) {
                if (arg == Event.DAMAGED) {
                    double charRemainingHealth = (charWidth * character.getHealth()) / maxHealth;
                    remainingHealth.setWidth(charRemainingHealth);
                    missingHealth.setTranslateX(character.getPosition().getX() + charRemainingHealth - 40);
                    missingHealth.setWidth(charWidth - charRemainingHealth);
                } else if (arg == Event.DEAD) {
                    charac = characterSprite.getDead();
                } else if (arg == Event.SPIN) {
                    charac = characterSprite.getSpinSprite();
                } else if (arg == Event.LIGHTNING) {
                    panel.getChildren().remove(lightning);
                    lightning = characterSprite.getLightning(character.getLightning());
                    lightning.setTranslateX(character.getLightning().getX() - 40);
                    panel.getChildren().add(lightning);
                }else if (arg == Event.STOP_LIGHTNING) {
                    panel.getChildren().remove(lightning);
                } else if (arg == Event.MOVED) {
                    charac = characterSprite.getMovingCharacterSprite();
                } else if (arg == Event.STOPPED) {
                    charac = characterSprite.getCharacterSprite();
                }
        }
        panel.getChildren().add(charac);

        if (character.isGold()) {
            remainingHealth.setScaleX(2);
            remainingHealth.setScaleY(2);
            missingHealth.setScaleX(2);
            missingHealth.setScaleY(2);
        } else {
            remainingHealth.setScaleX(1);
            remainingHealth.setScaleY(1);
            missingHealth.setScaleX(1);
            missingHealth.setScaleY(1);
        }
        int size = character.isGold() ? 80 : 40;
        double charRemainingHealth = (charWidth * character.getHealth())/maxHealth;
        remainingHealth.setTranslateX(character.getPosition().getX()-40);
        remainingHealth.setTranslateY(character.getPosition().getY()-size);
        remainingHealth.setWidth(charRemainingHealth);
        missingHealth.setTranslateX(character.getPosition().getX()+charRemainingHealth-40);
        missingHealth.setTranslateY(character.getPosition().getY()-size);
        missingHealth.setWidth(charWidth -charRemainingHealth);
        charac.setTranslateX(character.getPosition().getX()-size);
        charac.setTranslateY(character.getPosition().getY()-size);
    }
}
