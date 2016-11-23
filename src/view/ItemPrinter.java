package view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Item;

import java.util.Observable;
import java.util.Observer;

public class ItemPrinter implements JavaFXPrinter, Observer
{
    private Item item;
    private ImageView spin;
    private ImageView lightning;
    private ImageView gold;
    private ImageView heal;

    public ItemPrinter(Item item)
    {
        this.item = item;
        spin = new ImageView(new Image("file:assets/image/spin.png"));
        lightning = new ImageView(new Image("file:assets/image/lightning-item.png"));
        gold = new ImageView(new Image("file:assets/image/fist.png"));
        heal = new ImageView(new Image("file:assets/image/heal.png"));
    }

    @Override
    public Node getNode() {
        if (item.getType() != null) {
            ImageView img = null;
            switch (item.getType()) {
                case SPIN:
                    img = spin;
                    break;
                case LIGHTNING:
                    img = lightning;
                    break;
                case GOLD:
                    img = gold;
                    break;
                case HEAL:
                    img = heal;
            }
            img.setTranslateX(item.getHitbox().getX());
            img.setTranslateY(item.getHitbox().getY());
            return img;
        }
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
