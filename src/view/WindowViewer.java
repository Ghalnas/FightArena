package view;

import javafx.scene.Group;
import javafx.scene.Node;
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

    public Parent getPanel(double shrink)
    {
        double scale = shrink/720;
        System.out.println(shrink);
        System.out.println(scale);
        Group panel = new Group();
        Node gamePanel = gameViewer.getPanel();
        gamePanel.setScaleX(scale);
        gamePanel.setScaleY(scale);
        gamePanel.setTranslateX(-(720-shrink));
        gamePanel.setTranslateY(-(720-shrink));
        Node scorePanel = scoreViewer.getPanel();
        scorePanel.setScaleX(scale);
        scorePanel.setScaleY(scale);
        Node logPanel = logViewer.getPanel();
        logPanel.setScaleX(scale);
        logPanel.setScaleY(scale);
        panel.getChildren().add(gamePanel);
        panel.getChildren().add(scorePanel);
        panel.getChildren().add(logPanel);
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
