package view;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Observer;

public class LogViewer
{
    private ArrayList<Observer> listObservers;
    private Rectangle background;
    private Text title;

    public LogViewer()
    {
        listObservers = new ArrayList<>();
        background = new Rectangle(502, 200, 300, 200);
        background.setFill(Color.rgb(0, 0, 0, 0.7));
        title = new Text(635, 220, "Logs");
        title.setFill(Color.rgb(255, 255, 255));
    }

    public Parent getPanel()
    {
        Group panel = new Group();
        panel.getChildren().add(background);
        panel.getChildren().add(title);

        // TODO : Add real logs  here via observer
        //Display logs
        Text log1 = new Text(510, 260, "Exemple de log 1");
        log1.setFill(Color.rgb(255, 255, 255));
        Text log2 = new Text(510, 280, "Exemple de log 2");
        log2.setFill(Color.rgb(255, 255, 255));
        Text log3 = new Text(510, 300, "Exemple de log 3");
        log3.setFill(Color.rgb(255, 255, 255));
        Text log4 = new Text(510, 320, "Exemple de log 4");
        log4.setFill(Color.rgb(255, 255, 255));
        Text log5 = new Text(510, 340, "Exemple de log 5");
        log5.setFill(Color.rgb(255, 255, 255));
        Text log6 = new Text(510, 360, "Exemple de log 6");
        log6.setFill(Color.rgb(255, 255, 255));

        panel.getChildren().add(log1);
        panel.getChildren().add(log2);
        panel.getChildren().add(log3);
        panel.getChildren().add(log4);
        panel.getChildren().add(log5);
        panel.getChildren().add(log6);

        return panel;
    }

    public void addObserver(Observer o)
    {
        listObservers.add(o);
    }
}
