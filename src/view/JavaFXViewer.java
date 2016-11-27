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
    private Group panel;

    public JavaFXViewer(double scale, double width, double height)
    {
        this.scale = scale;
        this.width = width;
        this.height = height;
        background = new ImageView(new Image("file:assets/image/background-1024.jpg"));
        listPrinters = new ArrayList<>();
        panel = new Group();
        panel.getChildren().add(background);
    }

    public Parent getPanel()
    {
        panel.getChildren().remove(1,panel.getChildren().size());
        for (JavaFXPrinter obs : listPrinters) {
            Node node = obs.getNode();
            panel.getChildren().add(node);
        }
        return panel;
    }

    public void addObserverJavaFX(JavaFXPrinter o)
    {
        listPrinters.add(o);
    }
}
