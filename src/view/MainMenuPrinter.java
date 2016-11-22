package view;

import controller.Engine;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Observable;
import java.util.Observer;

import static java.awt.SystemColor.window;

public class MainMenuPrinter implements JavaFXPrinter,Observer {

    private Text title;
    private ImageView background;
    private Button fightButton;
    private Button statsButton;
    private Button settingsButton;
    private Button leaveButton;
    private String fontPixelPath = "file:assets/font/Pixeled.ttf";
    private boolean gamePanelRequired;


    public MainMenuPrinter(double screenWidth, double screenHeight) {

        gamePanelRequired = false;

        title = new Text(screenWidth/4, screenHeight/2,  "FightArena");
        title.setFill(Color.BLACK);
        title.setFont(Font.loadFont(fontPixelPath, 20));
        title.setScaleX(3);
        title.setScaleY(3);

        background = new ImageView(new Image("file:assets/image/background-medium.jpg"));

        fightButton = new Button("Fight");
        fightButton.setFont(Font.loadFont(fontPixelPath, 10));
        fightButton.setLayoutX(screenWidth/1.30);
        fightButton.setLayoutY(screenHeight/4);

        fightButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                gamePanelRequired = true;
            }
        });

        statsButton = new Button("Stats");
        statsButton.setFont(Font.loadFont(fontPixelPath, 10));
        statsButton.setLayoutX(screenWidth/1.30);
        statsButton.setLayoutY(screenHeight/3);

        statsButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                System.out.println("stats button clicked");
            }
        });

        settingsButton = new Button("Settings");
        settingsButton.setFont(Font.loadFont(fontPixelPath, 10));
        settingsButton.setLayoutX(screenWidth/1.30);
        settingsButton.setLayoutY(screenHeight/2);

        settingsButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                System.out.println("settings button clicked");
            }
        });

        leaveButton = new Button("Leave");
        leaveButton.setCancelButton(true);
        leaveButton.setFont(Font.loadFont(fontPixelPath, 10));
        leaveButton.setLayoutX(screenWidth/1.30);
        leaveButton.setLayoutY(screenHeight/1.5);

        leaveButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                System.out.println("leave button clicked");
                Platform.exit();
            }
        });

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
        panel.getChildren().add(fightButton);
        panel.getChildren().add(statsButton);
        panel.getChildren().add(settingsButton);
        panel.getChildren().add(leaveButton);

        return panel;
    }

    public boolean getGamePanelRequired(){
        return gamePanelRequired;
    }

}
