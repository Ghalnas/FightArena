package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;

public class WindowViewer
{
    private JavaFXViewer gameViewer;
    private LogViewer logViewer;
    private ScoreViewer scoreViewer;
    private MainMenuViewer mainMenuViewer;


    public WindowViewer(double width, double height, double scale)
    {
        this.gameViewer = new JavaFXViewer(scale,width,height);
        this.logViewer = new LogViewer();
        this.scoreViewer = new ScoreViewer();
        this.mainMenuViewer = new MainMenuViewer();
    }

    public Parent getGamePanel(double shrinkX, double shrinkY)
    {
        double scaleX = shrinkX/1600;
        Group panel = new Group();
        Node gamePanel = gameViewer.getPanel();
        Node scorePanel = scoreViewer.getPanel();
        Node logPanel = logViewer.getPanel();
        panel.getChildren().add(gamePanel);
        panel.getChildren().add(scorePanel);
        panel.getChildren().add(logPanel);
        panel.setScaleX(scaleX);
        panel.setScaleY(scaleX);
        panel.setTranslateX((shrinkX/2)-(1600/2));
        panel.setTranslateY((shrinkY/2)-(720/2));
        return panel;
    }

    public Parent getMainPanel(double shrinkX, double shrinkY)
    {
        double scaleX = shrinkX/1600;
        Group panel = new Group();
        panel.setScaleX(scaleX);
        panel.setScaleY(scaleX);
        panel.setTranslateX((shrinkX/2)-(1600/2));
        panel.setTranslateY((shrinkY/2)-(720/2));
        panel.getChildren().add(mainMenuViewer.getPanel());
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
    public void addScorePrinter(JavaFXPrinter printer){ scoreViewer.addObserverJavaFX(printer);}
    public void addMainMenuPrinter(JavaFXPrinter printer) { mainMenuViewer.addObserverJavaFX(printer);}

}
