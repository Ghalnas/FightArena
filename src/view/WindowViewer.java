package view;

import javafx.scene.Group;
import javafx.scene.Parent;

public class WindowViewer
{
    private JavaFXViewer gameViewer;
    private LogViewer logViewer;
    private ScoreViewer scoreViewer;
    private MainMenuPrinter mainMenuPrinter;
    private RightMenu rightMenu;
    private StatsPrinter statsPrinter;
    private Group panel;
    private final double width = 1280, height = 720;

    public WindowViewer(double arenaWidth, double arenaHeight, double scale, MainMenuPrinter mainMenuPrinter, RightMenu rightMenu, StatsPrinter statsPrinter)
    {
        this.gameViewer = new JavaFXViewer(scale,arenaWidth,arenaHeight);
        this.logViewer = new LogViewer();
        this.scoreViewer = new ScoreViewer();
        this.mainMenuPrinter = mainMenuPrinter;
        this.rightMenu = rightMenu;
        this.statsPrinter = statsPrinter;
        panel = new Group();
    }

    public Parent getGamePanel(double shrinkX, double shrinkY)
    {
        panel.getChildren().clear();
        panel.getChildren().add(gameViewer.getPanel());
        panel.getChildren().add(scoreViewer.getPanel());
        panel.getChildren().add(logViewer.getPanel());
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

    public Parent getStatsPanel(double shrinkX, double shrinkY, String pseudoPlayer){
        panel.getChildren().clear();
        statsPrinter.statsPanel(pseudoPlayer);
        panel.getChildren().add(statsPrinter.getPanel());
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
