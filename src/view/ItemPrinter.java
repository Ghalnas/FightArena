package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Hitbox;
import model.Item;

import java.util.Observable;
import java.util.Observer;

public class ItemPrinter implements JavaFXPrinter, Observer
{
    private Item item;

    public ItemPrinter(Item item)
    {
        this.item = item;
    }

    @Override
    public Node getNode() {
        if (item.getType() != null) {
            Hitbox hb = item.getHitbox();
            Rectangle rec = new Rectangle(hb.getX(), hb.getY(), hb.getWidth(), hb.getHeight());
            rec.setFill(Color.GREEN);
            return rec;
        }
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
