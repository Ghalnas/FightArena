package view;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    private ChoiceBox cb;
    private String pseudo;
    private TimerObserver o;
    private double width, height;
    private double[][] tabSize;

    public RightMenu(double width, double height)
    {
        this.width = width;
        this.height = height;
        tabSize = new double[][] {new double[]{800,600}, new double[]{1280,720}, new double[]{1920,1080}};
        background = new ImageView(new Image("file:assets/image/right-menu-256.png"));
        //Buttons
        //Fight + Pseudo Textfield
        pseudoTextField = new TextField();
        pseudoTextField.setPromptText("Unknown");
        pseudoTextField.setTranslateX(53);
        pseudoTextField.setTranslateY(50);
        pseudoTextField.setPrefWidth(150);

        fightButton = new Button("Fight");
        fightButton.setFont(Font.loadFont(fontPixelPath, 10));
        fightButton.setTranslateX(78);
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

        //Stats
        statsButton = new Button("Stats");
        statsButton.setFont(Font.loadFont(fontPixelPath, 10));
        statsButton.setTranslateX(78);
        statsButton.setTranslateY(150);
        statsButton.setPrefWidth(100);

        statsButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                notifyTimer(GameEvent.REQUIRE_STATS);
            }
        });

        //Leave
        leaveButton = new Button("Leave");
        leaveButton.setFont(Font.loadFont(fontPixelPath, 10));
        leaveButton.setTranslateX(78);
        leaveButton.setTranslateY(200);
        leaveButton.setPrefWidth(100);

        leaveButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                Platform.exit();
            }
        });

        cb = new ChoiceBox(FXCollections.observableArrayList(
                "First", "Second", "Third")
        );
        cb.setTranslateX(78);
        cb.setTranslateY(300);
        cb.setPrefWidth(100);
        cb.getSelectionModel().selectFirst();
        cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            }
        });

    }

    public Node getPanel()
    {
        Group panel = new Group();
        panel.setTranslateX(width);
        panel.getChildren().add(background);
        panel.getChildren().add(fightButton);
        panel.getChildren().add(statsButton);
        panel.getChildren().add(leaveButton);
        panel.getChildren().add(cb);
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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
