package view;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.TimerEvent;

import java.util.Observable;
import java.util.Observer;

public class MainMenuPrinter implements JavaFXPrinter, TimerObservable {

    private Text title;
    private ImageView background;
    private Button fightButton;
    private Button statsButton;
    private Button settingsButton;
    private Button leaveButton;
    private String fontPixelPath = "file:assets/font/Pixeled.ttf";
    private TextField pseudoTextField;
    private String pseudo;
    private TimerObserver o;


    public MainMenuPrinter(double screenWidth, double screenHeight) {

        title = new Text(screenWidth/4, screenHeight/2,  "FightArena");
        title.setFill(Color.BLACK);
        title.setFont(Font.loadFont(fontPixelPath, 20));
        title.setScaleX(3);
        title.setScaleY(3);

        background = new ImageView(new Image("file:assets/image/background-medium.jpg"));

        //Buttons
        //Fight + Pseudo Textfield
        fightButton = new Button("Fight");
        fightButton.setFont(Font.loadFont(fontPixelPath, 10));
        fightButton.setLayoutX(screenWidth/1.30);
        fightButton.setLayoutY(screenHeight/4);
        fightButton.setPrefWidth(100);

        pseudoTextField = new TextField();
        pseudoTextField.setPromptText("Unknown");
        pseudoTextField.setLayoutX(fightButton.getLayoutX() + fightButton.getPrefWidth() + 10);
        pseudoTextField.setLayoutY(fightButton.getLayoutY());

        fightButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                if(pseudoTextField.getText().isEmpty()){
                    pseudo = null;
                }else{
                    pseudo = pseudoTextField.getText();
                }
                notifyTimer(TimerEvent.REQUIRE_GAME);
            }
        });

        //StatsWriter
        statsButton = new Button("StatsWriter");
        statsButton.setFont(Font.loadFont(fontPixelPath, 10));
        statsButton.setLayoutX(screenWidth/1.30);
        statsButton.setLayoutY(screenHeight/3);
        statsButton.setPrefWidth(100);

        statsButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                notifyTimer(TimerEvent.REQUIRE_STATS);
            }
        });

        //Settings
        settingsButton = new Button("Settings");
        settingsButton.setFont(Font.loadFont(fontPixelPath, 10));
        settingsButton.setLayoutX(screenWidth/1.30);
        settingsButton.setLayoutY(screenHeight/2);
        settingsButton.setPrefWidth(100);

        settingsButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                notifyTimer(TimerEvent.REQUIRE_SETTING);
            }
        });

        //Leave
        leaveButton = new Button("Leave");
        leaveButton.setCancelButton(true);
        leaveButton.setFont(Font.loadFont(fontPixelPath, 10));
        leaveButton.setLayoutX(screenWidth/1.30);
        leaveButton.setLayoutY(screenHeight/1.5);
        leaveButton.setPrefWidth(100);

        leaveButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                Platform.exit();
            }
        });

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
        panel.getChildren().add(pseudoTextField);

        return panel;
    }

    @Override
    public void bindTimer(TimerObserver o) {
        this.o = o;
    }

    @Override
    public void notifyTimer(TimerEvent e) {
        if (o != null) {
            o.update(e,pseudo);
        }
    }

}
