package view;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class JavaFXViewer
{
    private double scale;
    private ArrayList<JavaFXPrinter> listObservers;
    private ImageView background;

    public JavaFXViewer(double scale)
    {
        this.scale = scale;
        listObservers = new ArrayList<>();
        background = new ImageView(new Image("file:src/image/background.jpg"));
    }

    public Parent getPanel()
    {
        Group panel = new Group();
        panel.getChildren().add(background);
        for (JavaFXPrinter obs : listObservers) {
            Node node = obs.getNode();
            node.setScaleY(scale);
            node.setScaleX(scale);
            panel.getChildren().add(node);
        }
        return panel;
    }

    public void addObserverJavaFX(JavaFXPrinter o)
    {
        listObservers.add(o);
    }
}
