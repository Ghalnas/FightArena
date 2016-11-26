package view;


import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.ArrayList;

public class ScoreViewer {

    private ArrayList<JavaFXPrinter> listPrinters;
    private Group panel;

    public ScoreViewer()
    {
        listPrinters = new ArrayList<>();
        panel = new Group();
    }

    public Parent getPanel()
    {
        panel.getChildren().clear();
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
