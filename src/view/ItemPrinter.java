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

    public ItemPrinter(Item item)
    {
        this.item = item;
        spin = new ImageView(new Image("file:assets/image/spin.png"));
        lightning = new ImageView(new Image("file:assets/image/lightning-item.png"));
    }

    @Override
    public Node getNode() {
        if (item.getType() != null) {
            ImageView img = null;
            switch (item.getType()) {
                case SPIN:
                    img = spin;
                    img.setTranslateX(item.getHitbox().getX());
                    img.setTranslateY(item.getHitbox().getY());
                    break;
                case LIGHTNING:
                    img = lightning;
                    img.setTranslateX(item.getHitbox().getX());
                    img.setTranslateY(item.getHitbox().getY());
                    break;
            }
            return img;
        }
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
