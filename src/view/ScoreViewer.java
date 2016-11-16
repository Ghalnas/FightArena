package view;


import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.ArrayList;

public class ScoreViewer {

    private ArrayList<JavaFXPrinter> listPrinters;

    public ScoreViewer()
    {
        listPrinters = new ArrayList<>();
    }

    public Parent getPanel()
    {
        Group panel = new Group();
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
