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
    }

    @Override
    public Node getNode()
    {
        Group group = new Group();
        double charRemainingHealth = (charWidth * character.getHealth())/maxHealth;
        Rectangle remainingHealth = new Rectangle(character.getPosition().getX()-40, character.getPosition().getY()-40, charRemainingHealth,6);
        remainingHealth.setFill(Color.LIGHTGREEN);
        Rectangle missingHealth = new Rectangle(character.getPosition().getX()+charRemainingHealth-40, character.getPosition().getY()-40, charWidth -charRemainingHealth,6);
        missingHealth.setFill(Color.RED);
        //PRINT Char position
//        Position playerPos = character.getPosition();
//        Rectangle rec = new Rectangle(playerPos.getX(),playerPos.getY(),2,2);
//        rec.setFill(Color.BLUE);
//        group.getChildren().add(rec);

        //PRINT Char hitbox
//        Hitbox hb = character.getHitbox();
//        Rectangle bobby = new Rectangle(hb.getX(),hb.getY(),hb.getWidth(),hb.getHeight());
//        bobby.setFill(Color.MAGENTA);
//        group.getChildren().add(bobby);

        //PRINT sword hitbox
//        Hitbox sword = null;
//        Position pos = character.getPosition();
//        switch (character.getDirection()) {
//            case UP:
//                sword = new Hitbox(pos, -15, -40,30,25);
//                break;
//            case RIGHT:
//                sword = new Hitbox(pos, 15, -10,25,30);
//                break;
//            case DOWN:
//                sword = new Hitbox(pos, -15, 20,30,25);
//                break;
//            case LEFT:
//                sword = new Hitbox(pos, -40, -10,25,30);
//                break;
//        }
//        Rectangle bob = new Rectangle(sword.getX(),sword.getY(),sword.getWidth(),sword.getHeight());
//        bob.setFill(Color.RED);
//        group.getChildren().add(bob);

        group.getChildren().addAll(playerView,lightning,remainingHealth,missingHealth);

        return group;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Command.Action) {
            if (arg == Command.Action.SLASH) {
                playerView = characterSprite.getSlashCharacterSprite();
            } else if (arg == Command.Action.NONE) {
                playerView = characterSprite.getCharacterSprite();
                lightning = new ImageView();
            }
            playerView.setTranslateX(character.getPosition().getX()-40);
            playerView.setTranslateY(character.getPosition().getY()-40);
        } else if (arg instanceof Event) {
            if (arg == Event.SPIN) {
                playerView = characterSprite.getSpinSprite();
            } else if (arg == Event.LIGHTNING) {
                lightning = characterSprite.getLightning(character.getLightning());
                lightning.setTranslateX(character.getLightning().getX()-40);
                lightning.setTranslateY(-10);
            }
            else if (arg == Event.MOVED) {
                playerView = characterSprite.getMovingCharacterSprite();
            } else if(arg == Event.STOPPED) {
                playerView = characterSprite.getCharacterSprite();
            }
            playerView.setTranslateX(character.getPosition().getX()-40);
            playerView.setTranslateY(character.getPosition().getY()-40);
        }
    }
}
