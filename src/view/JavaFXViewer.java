package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class JavaFXViewer
{
    private double scale;
    private ArrayList<JavaFXPrinter> listObservers;
    private ImageView background;
    private double width, height;

    public JavaFXViewer(double scale, double width, double height)
    {
        this.scale = scale;
        this.width = width;
        this.height = height;
        switch ((int)width) {
            case 800:
                background = new ImageView(new Image("file:src/image/background-small.jpg"));
                break;
            case 1280:
                background = new ImageView(new Image("file:src/image/background-medium.jpg"));
                break;
            case 1920:
                background = new ImageView(new Image("file:src/image/background-large.jpg"));
                break;
            default:
                throw new InvalidParameterException("Parameters must be 800*600, 1280*720 or 1920*1080");
        }
        listObservers = new ArrayList<>();
    }

    public Parent getPanel()
    {
        Group panel = new Group();
        double imgScale = (width/800)*scale;
        panel.getChildren().add(background);
        for (JavaFXPrinter obs : listObservers) {
            Node node = obs.getNode();
            node.setScaleX(imgScale);
            node.setScaleY(imgScale);
            panel.getChildren().add(node);
        }
        return panel;
    }

    public void addObserverJavaFX(JavaFXPrinter o)
    {
        listObservers.add(o);
    }
}
