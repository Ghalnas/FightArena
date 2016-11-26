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
    private StatsPrinter statsPrinter;
    private Group panel;
    private double width, height;

    public WindowViewer(double width, double height, double arenaWidth, double arenaHeight, double scale, MainMenuPrinter mainMenuPrinter, RightMenu rightMenu, StatsPrinter statsPrinter)
    {
        this.width = width;
        this.height = height;
        this.gameViewer = new JavaFXViewer(scale,arenaWidth,arenaHeight);
        this.logViewer = new LogViewer();
        this.scoreViewer = new ScoreViewer();
        this.mainMenuPrinter = mainMenuPrinter;
        this.rightMenu = rightMenu;
        this.statsPrinter = statsPrinter;
    }

    public Parent getGamePanel(double shrinkX, double shrinkY)
    {
        panel = new Group();
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
        panel = new Group();
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

    public Parent getStatsPanel(double shrinkX, double shrinkY, String pseudoPlayer){
        panel = new Group();
        Node statsPanel = statsPrinter.getPanel(pseudoPlayer);
        panel.getChildren().add(statsPanel);
        panel.getChildren().add(rightMenu.getPanel());
        resizePanel(shrinkX, shrinkY);
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

}
