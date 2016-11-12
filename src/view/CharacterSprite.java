package view;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Character;
import model.Player;

public class CharacterSprite
{
    private final int spriteWidth;
    private final int spriteHeight;
    private final ImageView imageView;
    private double cpt;
    private double increment;
    private Character character;

    public CharacterSprite(Character c,int spriteWidth, int spriteHeight)
    {
        imageView = c instanceof Player ? new ImageView(new Image("file:src/image/player-simple.png")) : new ImageView(new Image("file:src/image/bot-simple.png"));
        character = c;
        cpt = 1;
        increment = 0.1;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    public ImageView getCharacterSprite()
    {
        cpt = 1;
        increment = Math.abs(increment);
        setImageViewPort(1);
        return imageView;
    }

    public ImageView getMovingCharacterSprite()
    {
        cpt += increment;
        if(cpt > 100 || cpt < 1) {
            increment = -increment;
        }
        int multiplier = (int)Math.rint(cpt);
        setImageViewPort(multiplier%3);
        return imageView;
    }

    private void setImageViewPort(int multiplier)
    {
        switch (character.getDirection()) {
            case UP:
                imageView.setViewport(new Rectangle2D(multiplier*spriteWidth, 0, spriteWidth, spriteHeight));
                break;
            case RIGHT:
                imageView.setViewport(new Rectangle2D(multiplier*spriteWidth, spriteHeight, spriteWidth, spriteHeight));
                break;
            case DOWN:
                imageView.setViewport(new Rectangle2D(multiplier*spriteWidth, 2*spriteHeight, spriteWidth, spriteHeight));
                break;
            case LEFT:
                imageView.setViewport(new Rectangle2D(multiplier*spriteWidth, 3*spriteHeight, spriteWidth, spriteHeight));
                break;
        }
    }
}
