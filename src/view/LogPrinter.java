package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Observable;
import java.util.Observer;

public class LogPrinter implements JavaFXPrinter,Observer
{

    private Rectangle background;
    private Text title;
    private double startX,startY;

    public LogPrinter(double startX, double startY, double width, double height)
    {
        background = new Rectangle(startX, startY, width, height);
        background.setFill(Color.rgb(0, 0, 0, 0.7));
        title = new Text(startX, startY+20, "Logs");
        title.setFill(Color.WHITE);
        this.startX = startX;
        this.startY = startY;
    }

    @Override
    public void update(Observable o, Object arg)
    {

    }

    @Override
    public Node getNode()
    {
        Group panel = new Group();
        panel.getChildren().add(background);
        panel.getChildren().add(title);

        // TODO : Add real logs  here via observer
        //Display logs
        Text log1 = new Text(startX, startY+40, "Exemple de log 1");
        log1.setFill(Color.rgb(255, 255, 255));
        Text log2 = new Text(startX, startY+60, "Exemple de log 2");
        log2.setFill(Color.rgb(255, 255, 255));
        Text log3 = new Text(startX, startY+80, "Exemple de log 3");
        log3.setFill(Color.rgb(255, 255, 255));
        Text log4 = new Text(startX, startY+100, "Exemple de log 4");
        log4.setFill(Color.rgb(255, 255, 255));
        Text log5 = new Text(startX, startY+120, "Exemple de log 5");
        log5.setFill(Color.rgb(255, 255, 255));
        Text log6 = new Text(startX, startY+140, "Exemple de log 6");
        log6.setFill(Color.rgb(255, 255, 255));

        panel.getChildren().add(log1);
        panel.getChildren().add(log2);
        panel.getChildren().add(log3);
        panel.getChildren().add(log4);
        panel.getChildren().add(log5);
        panel.getChildren().add(log6);

        return panel;
    }
}
