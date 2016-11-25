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
import javafx.scene.text.Font;
import model.GameEvent;

public class RightMenu implements TimerObservable
{
    private ImageView background;
    private Button fightButton;
    private Button statsButton;
    private Button settingsButton;
    private Button leaveButton;
    private String fontPixelPath = "file:assets/font/Pixeled.ttf";
    private TextField pseudoTextField;
    private String pseudo;
    private TimerObserver o;

    public RightMenu(double screenWidth, double screenHeight)
    {
        background = new ImageView(new Image("file:assets/image/right-menu.png"));
        //Buttons
        //Fight + Pseudo Textfield
        pseudoTextField = new TextField();
        pseudoTextField.setPromptText("Unknown");
        pseudoTextField.setTranslateX(85);
        pseudoTextField.setTranslateY(50);
        pseudoTextField.setPrefWidth(150);

        fightButton = new Button("Fight");
        fightButton.setFont(Font.loadFont(fontPixelPath, 10));
        fightButton.setTranslateX(110);
        fightButton.setTranslateY(100);
        fightButton.setPrefWidth(100);



        fightButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                if(pseudoTextField.getText().isEmpty()){
                    pseudo = null;
                }else{
                    pseudo = pseudoTextField.getText();
                }
                notifyTimer(GameEvent.REQUIRE_GAME);
            }
        });

        //StatsWriter
        statsButton = new Button("StatsWriter");
        statsButton.setFont(Font.loadFont(fontPixelPath, 10));
        statsButton.setTranslateX(110);
        statsButton.setTranslateY(150);
        statsButton.setPrefWidth(100);

        statsButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                notifyTimer(GameEvent.REQUIRE_STATS);
            }
        });

        //Settings
        settingsButton = new Button("Settings");
        settingsButton.setFont(Font.loadFont(fontPixelPath, 10));
        settingsButton.setTranslateX(110);
        settingsButton.setTranslateY(200);
        settingsButton.setPrefWidth(100);

        settingsButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                notifyTimer(GameEvent.REQUIRE_SETTING);
            }
        });

        //Leave
        leaveButton = new Button("Leave");
        leaveButton.setCancelButton(true);
        leaveButton.setFont(Font.loadFont(fontPixelPath, 10));
        leaveButton.setTranslateX(110);
        leaveButton.setTranslateY(250);
        leaveButton.setPrefWidth(100);

        leaveButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                Platform.exit();
            }
        });

    }

    public Node getPanel()
    {
        Group panel = new Group();
        panel.setTranslateX(1280);
        panel.getChildren().add(background);
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
    public void notifyTimer(Object arg) {
        if (o != null) {
            o.update(this,arg);
        }
    }

    public String getPseudo()
    {
        return pseudo;
    }
}
