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
    private ArrayList<JavaFXPrinter> listPrinters;
    private ImageView background;
    private double width, height;

    public JavaFXViewer(double scale, double width, double height)
    {
        this.scale = scale;
        this.width = width;
        this.height = height;
        switch ((int)width) {
            case 800:
                background = new ImageView(new Image("file:assets/image/background-small.jpg"));
                break;
            case 1280:
                background = new ImageView(new Image("file:assets/image/background-medium.jpg"));
                break;
            case 1920:
                background = new ImageView(new Image("file:assets/image/background-large.jpg"));
                break;
            default:
                throw new InvalidParameterException("Parameters must be 800*600, 1280*720 or 1920*1080");
        }
        listPrinters = new ArrayList<>();
    }

    public Parent getPanel()
    {
        Group panel = new Group();
        panel.getChildren().add(background);
        for (JavaFXPrinter obs : listPrinters) {
            Node node = obs.getNode();
            if (node != null) {
                panel.getChildren().add(node);
            }
        }
        return panel;
    }

    public void addObserverJavaFX(JavaFXPrinter o)
    {
        listPrinters.add(o);
    }
}
