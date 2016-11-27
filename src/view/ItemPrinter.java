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
    private ImageView itemView;

    public ItemPrinter(Item item)
    {
        this.item = item;
        itemView = new ImageView();
        spin = new ImageView(new Image("file:assets/image/spin.png"));
        lightning = new ImageView(new Image("file:assets/image/lightning-item.png"));
        gold = new ImageView(new Image("file:assets/image/fist.png"));
        heal = new ImageView(new Image("file:assets/image/heal.png"));
    }

    @Override
    public Node getNode() {
        return itemView;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Item.ItemType) {
            switch (item.getType()) {
                case SPIN:
                    itemView = spin;
                    break;
                case LIGHTNING:
                    itemView = lightning;
                    break;
                case GOLD:
                    itemView = gold;
                    break;
                case HEAL:
                    itemView = heal;
            }
            itemView.setTranslateX(item.getHitbox().getX());
            itemView.setTranslateY(item.getHitbox().getY());
        } else {
            itemView = new ImageView();
        }
    }
}
