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

    public WindowViewer(double width, double height, double scale, MainMenuPrinter mainMenuPrinter, RightMenu rightMenu)
    {
        this.gameViewer = new JavaFXViewer(scale,width,height);
        this.logViewer = new LogViewer();
        this.scoreViewer = new ScoreViewer();
        this.mainMenuPrinter = mainMenuPrinter;
        this.rightMenu = rightMenu;
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
        double scaleX = shrinkX/1600;
        panel.setScaleX(scaleX);
        panel.setScaleY(scaleX);
        panel.setTranslateX((shrinkX/2)-(1600/2));
        panel.setTranslateY((shrinkY/2)-(720/2));
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
