package view;

import controller.Engine;
import javafx.animation.AnimationTimer;
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
    private TextField pseudoTextField;
    private boolean gamePanelRequired;
    private boolean statsPanelRequired;
    private boolean settingsPanelRequired;
    private boolean mainMenuPanelRequired;


    public MainMenuPrinter(double screenWidth, double screenHeight) {

        gamePanelRequired = false;
        statsPanelRequired = false;
        settingsPanelRequired = false;
        mainMenuPanelRequired = true;

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
        pseudoTextField.setPromptText("Unknow");
        pseudoTextField.setLayoutX(fightButton.getLayoutX() + fightButton.getPrefWidth() + 10);
        pseudoTextField.setLayoutY(fightButton.getLayoutY());

        fightButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                gamePanelRequired = true;
                mainMenuPanelRequired = false;
                if(pseudoTextField.getText().isEmpty()){
                    System.out.println("Unknow");
                }else{
                    System.out.println(pseudoTextField.getText());
                }
            }
        });

        //Stats
        statsButton = new Button("Stats");
        statsButton.setFont(Font.loadFont(fontPixelPath, 10));
        statsButton.setLayoutX(screenWidth/1.30);
        statsButton.setLayoutY(screenHeight/3);
        statsButton.setPrefWidth(100);

        statsButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                statsPanelRequired = true;
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
                settingsPanelRequired = true;
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
        panel.getChildren().add(pseudoTextField);

        return panel;
    }

    public boolean getGamePanelRequired(){
        return gamePanelRequired;
    }

    public boolean getSettingsPanelRequired(){
        return settingsPanelRequired;
    }

    public boolean getStatsPanelRequired(){
        return statsPanelRequired;
    }

    public boolean getMainMenuPanelRequired(){
        return mainMenuPanelRequired;
    }

    public void setGamePanelRequired(boolean bool){
        gamePanelRequired = bool;
    }

    public void setSettingsPanelRequired(boolean bool){
        settingsPanelRequired = bool;
    }

    public void setStatsPanelRequired(boolean bool){
        statsPanelRequired = bool;
    }

    public void setMainMenuPanelRequired(boolean bool){
        mainMenuPanelRequired = bool;
    }

}
