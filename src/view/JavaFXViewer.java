package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.ArrayList;

public class JavaFXViewer
{
    private double scale;
    private ArrayList<JavaFXPrinter> listObservers;

    public JavaFXViewer(double scale)
    {
        this.scale = scale;
        listObservers = new ArrayList<>();
    }

    public Parent getPanel()
    {
        Group panel = new Group();
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
