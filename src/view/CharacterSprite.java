package view;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Character;
import model.Player;

public class CharacterSprite
{
    private static final int spriteWidth = 17;
    private static final int spriteHeight = 26;

    private static ImageView playerView = new ImageView(new Image("file:src/image/player-simple.png"));;
    private static ImageView botView = new ImageView(new Image("file:src/image/bot-simple.png"));;

    public static ImageView getCharacterSprite(Character c)
    {
        ImageView charView = c instanceof Player ? playerView : botView;
        switch (c.getDirection()) {
            case LEFT:
                charView.setViewport(new Rectangle2D(0, 0, spriteWidth, spriteHeight));
                break;
            case DOWN:
                charView.setViewport(new Rectangle2D(spriteWidth, 0, spriteWidth, spriteHeight));
                break;
            case RIGHT:
                charView.setViewport(new Rectangle2D(2*spriteWidth, 0, spriteWidth, spriteHeight));
                break;
            case UP:
                charView.setViewport(new Rectangle2D(3*spriteWidth, 0, spriteWidth, spriteHeight));
                break;
        }
        return charView;
    }
}
