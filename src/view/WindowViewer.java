package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WindowViewer
{
    private JavaFXViewer gameViewer;
    private LogViewer logViewer;
    private ScoreViewer scoreViewer;
    private MainMenuPrinter mainMenuPrinter;
    private RightMenu rightMenu;
    private Group panel;
    private final double width = 1280, height = 720;

    public WindowViewer(double arenaWidth, double arenaHeight, double scale, MainMenuPrinter mainMenuPrinter, RightMenu rightMenu)
    {
        this.gameViewer = new JavaFXViewer(scale,arenaWidth,arenaHeight);
        this.logViewer = new LogViewer();
        this.scoreViewer = new ScoreViewer();
        this.mainMenuPrinter = mainMenuPrinter;
        this.rightMenu = rightMenu;
        panel = new Group();
    }

    public Parent getGamePanel(double shrinkX, double shrinkY)
    {
        panel.getChildren().clear();
        Node gamePanel = gameViewer.getPanel();
        Node scorePanel = scoreViewer.getPanel();
        Node logPanel = logViewer.getPanel();
        panel.getChildren().add(gamePanel);
        panel.getChildren().add(scorePanel);
        panel.getChildren().add(logPanel);
        resizePanel(shrinkX, shrinkY);
        return panel;
    }

    public Parent getMainPanel(double shrinkX, double shrinkY)
    {
        panel.getChildren().clear();
        panel.getChildren().add(mainMenuPrinter.getPanel());
        panel.getChildren().add(rightMenu.getPanel());
        resizePanel(shrinkX, shrinkY);
        return panel;
    }

    public void resizePanel(double shrinkX, double shrinkY)
    {
        double scaleX = shrinkX/width;
        double scaleY = shrinkY/height;
        panel.setScaleX(scaleX);
        panel.setScaleY(scaleY);
        panel.setTranslateX((shrinkX/2)-(width/2));
        panel.setTranslateY((shrinkY/2)-(height/2));
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

}
