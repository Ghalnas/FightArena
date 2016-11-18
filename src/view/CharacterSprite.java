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
    private int cptSlash;
    private double increment;
    private Character character;
    private final int slashFrames;
    private final double slashFrames1, slashFrames2;

    public CharacterSprite(Character c,int spriteWidth, int spriteHeight, int slashFrames)
    {
        imageView = c instanceof Player ? new ImageView(new Image("file:assets/image/player.png")) : new ImageView(new Image("file:assets/image/bot.png"));
        character = c;
        cpt = 1;
        cptSlash = 1;
        increment = 0.1;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        double slashFramesSlice = (double)slashFrames/3;
        this.slashFrames = slashFrames;
        slashFrames1 = slashFramesSlice;
        slashFrames2 = 2*slashFramesSlice;
    }

    public ImageView getCharacterSprite()
    {
        cptSlash = 0;
        cpt = 1;
        increment = Math.abs(increment);
        setImageViewPort(1);
        return imageView;
    }

    public ImageView getMovingCharacterSprite()
    {
        cptSlash = 0;
        cpt += increment;
        if(cpt > 100 || cpt < 1) {
            increment = -increment;
        }
        int multiplier = (int)Math.rint(cpt);
        setImageViewPort(multiplier%3);
        return imageView;
    }

    public ImageView getSlashCharacterSprite()
    {
        cptSlash++;
        cpt = 1;
        if (cptSlash == slashFrames) {
            cptSlash = 0;
        }
        if (cptSlash <= slashFrames1) {
            setImageViewPort(3);
        } else if (cptSlash > slashFrames1 && cptSlash < slashFrames2) {
            setImageViewPort(4);
        } else {
            setImageViewPort(5);
        }
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
