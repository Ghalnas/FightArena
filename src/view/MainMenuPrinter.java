package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Observable;
import java.util.Observer;

public class MainMenuPrinter implements JavaFXPrinter,Observer {

    private Text title;
    private ImageView background;
    private Button fightButton;
    private Button statsButton;
    private Button settingsButton;
    private Button leaveButton;
    //private Font fontPixel = Font.loadFont("file:src/font/Pixeled.ttf", 20);
    private String fontPixelPath = "file:src/font/Pixeled.ttf";


    public MainMenuPrinter(double screenWidth, double screenHeight) {

        title = new Text(screenWidth/4, screenHeight/2,  "FightArena");
        title.setFill(Color.BLACK);
        title.setFont(Font.loadFont(fontPixelPath, 20));
        title.setScaleX(3);
        title.setScaleY(3);


        background = new ImageView(new Image("file:src/image/background-medium.jpg"));

        fightButton = new Button("Fight");
        fightButton.setFont(Font.loadFont(fontPixelPath, 10));
        fightButton.setLayoutX(screenWidth/1.30);
        fightButton.setLayoutY(screenHeight/4);

        statsButton = new Button("Stats");
        statsButton.setFont(Font.loadFont(fontPixelPath, 10));
        statsButton.setLayoutX(screenWidth/1.30);
        statsButton.setLayoutY(screenHeight/3);

        settingsButton = new Button("Settings");
        settingsButton.setFont(Font.loadFont(fontPixelPath, 10));
        settingsButton.setLayoutX(screenWidth/1.30);
        settingsButton.setLayoutY(screenHeight/2);

        leaveButton = new Button("Leave");
        leaveButton.setFont(Font.loadFont(fontPixelPath, 10));
        leaveButton.setLayoutX(screenWidth/1.30);
        leaveButton.setLayoutY(screenHeight/1.5);

        /*fightButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                fightButton.setText("Accepted");
            }
        });*/

        /*statsButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e) {
                //fightButton.setText("Accepted");
            }
        });

        settingsButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e) {
                //fightButton.setText("Accepted");
            }
        });

        leaveButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e) {
                //leaveButton.setText("QUITING");
            }
        });*/

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

}
