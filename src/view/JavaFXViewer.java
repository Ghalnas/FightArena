package view;

import javafx.scene.Group;
import javafx.scene.Parent;

import java.util.ArrayList;

public class JavaFXViewer
{
    private ArrayList<ObserverJavaFX> listObservers;

    public JavaFXViewer()
    {
        listObservers = new ArrayList<>();
    }

    public Parent getPanel()
    {
        Group panel = new Group();
        for (ObserverJavaFX obs : listObservers) {
            panel.getChildren().add(obs.getNode());
        }
        return panel;
    }

    public void addObserverJavaFX(ObserverJavaFX o)
    {
        listObservers.add(o);
    }
}
