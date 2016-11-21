package view;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import model.Character;
import model.Hitbox;
import model.Player;

public class CharacterSprite
{
    private final int spriteWidth;
    private final int spriteHeight;
    private final ImageView imageView;
    private final ImageView lightning;
    private double cpt,cptLightning;
    private int cptSlash;
    private double increment, incrementSpin,incrementLightning;
    private Character character;
    private final int slashFrames;
    private final double slashFrames1, slashFrames2;

    public CharacterSprite(Character c,int spriteWidth, int spriteHeight, int slashFrames)
    {
        imageView = c instanceof Player ? new ImageView(new Image("file:assets/image/player80.png")) : new ImageView(new Image("file:assets/image/bot80.png"));
        lightning = new ImageView(new Image("file:assets/image/lightning.png"));
        character = c;
        cpt = 1;
        cptSlash = 1;
        cptLightning = 1;
        increment = 0.1;
        incrementSpin = 0.35;
        incrementLightning = 0.2;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        double slashFramesSlice = (double)slashFrames/3;
        this.slashFrames = slashFrames;
        slashFrames1 = slashFramesSlice;
        slashFrames2 = 2*slashFramesSlice;
    }

    public ImageView getCharacterSprite()
    {
        if(character instanceof Player)
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

    public ImageView getLightning(Hitbox hb)
    {
        cptLightning += incrementLightning;
        if (cptLightning > 100 || cptLightning < 1) {
            incrementLightning = -incrementLightning;
        }
        int multiplier = (int)Math.rint(cptLightning);
        lightning.setViewport(new Rectangle2D(multiplier%8*240,720-hb.getY()-170,240,hb.getY()+170));
        return lightning;
    }

    public ImageView getSpinSprite()
    {
        cpt += incrementSpin;
        if(cpt > 100 || cpt < 1) {
            incrementSpin = -incrementSpin;
        }
        int multiplier = (int)Math.rint(cpt);
        imageView.setViewport(new Rectangle2D(multiplier%3*spriteWidth, 4*spriteHeight, spriteWidth, spriteHeight));
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
