package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Character;
import model.Character.Event;
import model.Command;
import model.Hitbox;
import model.Player;

import java.util.Observable;
import java.util.Observer;

public class CharacterPrinter implements JavaFXPrinter, Observer
{
    private Character character;
    private double maxHealth;
    private final int charWidth, charHeight;
    private ImageView playerView;
    private CharacterSprite characterSprite;


    public CharacterPrinter(Character c, int charWidth, int charHeight, int slashFrames)
    {
        this.character = c;
        this.charWidth = charWidth;
        this.charHeight = charHeight;
        this.maxHealth = c.getHealth();
        characterSprite = new CharacterSprite(c,charWidth,charHeight,slashFrames);
        playerView = characterSprite.getCharacterSprite();
        playerView.setTranslateX(character.getPosition().getX());
        playerView.setTranslateY(character.getPosition().getY());
    }

    @Override
    public Node getNode()
    {
        Group group = new Group();
        double charRemainingHealth = (charWidth * character.getHealth())/maxHealth;
        Rectangle remainingHealth = new Rectangle(character.getPosition().getX(), character.getPosition().getY()-10, charRemainingHealth,6);
        remainingHealth.setFill(Color.LIGHTGREEN);
        Rectangle missingHealth = new Rectangle(character.getPosition().getX()+charRemainingHealth, character.getPosition().getY()-10, charWidth -charRemainingHealth,6);
        missingHealth.setFill(Color.RED);
//        Hitbox hb = character.getHitbox();
//        Rectangle rec = new Rectangle(hb.getX(),hb.getY(),hb.getWidth(),hb.getHeight());
//        rec.setFill(Color.BLUE);
//        Hitbox sword = null;
//        double attackerX = character.getPosition().getX();
//        double attackerY = character.getPosition().getY();
//        switch (character.getDirection()) {
//            case UP:
//                sword = new Hitbox(attackerX+25,attackerY-20,30,30);
//                break;
//            case RIGHT:
//                sword = new Hitbox(attackerX+60,attackerY+25,30,30);
//                break;
//            case DOWN:
//                sword = new Hitbox(attackerX+25,attackerY+70,30,30);
//                break;
//            case LEFT:
//                sword = new Hitbox(attackerX-10,attackerY+25,30,30);
//                break;
//        }
//        Rectangle bob = new Rectangle(sword.getX(),sword.getY(),sword.getWidth(),sword.getHeight());
//        bob.setFill(Color.RED);
        group.getChildren().addAll(playerView,remainingHealth,missingHealth);

        return group;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Command.Action) {
            if (arg == Command.Action.SLASH) {
                playerView = characterSprite.getSlashCharacterSprite();
            } else if (arg == Command.Action.NONE) {
                if(o instanceof Player)
                playerView = characterSprite.getCharacterSprite();
            }
            playerView.setTranslateX(character.getPosition().getX());
            playerView.setTranslateY(character.getPosition().getY());
        } else if (arg instanceof Event) {
            if (arg == Event.SPIN) {
                playerView = characterSprite.getSpinSprite();
            }
            else if (arg == Event.MOVED) {
                playerView = characterSprite.getMovingCharacterSprite();
            } else if(arg == Event.STOPPED) {
                playerView = characterSprite.getCharacterSprite();
            }
            playerView.setTranslateX(character.getPosition().getX());
            playerView.setTranslateY(character.getPosition().getY());
        }
    }
}
