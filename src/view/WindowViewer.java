package view;

import javafx.scene.Group;
import javafx.scene.Parent;

public class WindowViewer
{
    private JavaFXViewer gameViewer;
    private LogViewer logViewer;
    private ScoreViewer scoreViewer;


    public WindowViewer(double width, double height, double scale)
    {
        this.gameViewer = new JavaFXViewer(scale,width,height);
        this.logViewer = new LogViewer();
        this.scoreViewer = new ScoreViewer();
    }

    public Parent getPanel()
    {
        Group panel = new Group();
        panel.getChildren().add(gameViewer.getPanel());
        panel.getChildren().add(scoreViewer.getPanel());
        panel.getChildren().add(logViewer.getPanel());
        return panel;
    }

    public void addGamePrinter(JavaFXPrinter printer)
    {
        gameViewer.addObserverJavaFX(printer);
    }
    public void addLogPrinter(JavaFXPrinter printer)
    {
        logViewer.addObserverJavaFX(printer);
    }
    public void addScorePrinter(JavaFXPrinter printer){scoreViewer.addObserverJavaFX(printer);}

}
