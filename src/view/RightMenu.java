package view;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.GameEvent;

public class RightMenu implements TimerObservable
{
    private Button fightButton;
    private Button statsButton;
    private Button leaveButton;
    private String fontPixelPath = "file:assets/font/Pixeled.ttf";
    private TextField pseudoTextField;
    private String pseudo;
    private TimerObserver o;
    private double width, height;
    private double[][] tabSize;
    private StackPane panel;

    private Rectangle separator;

    private Text pseudoText;
    private Text resolutionText;
    private Text difficultyText;
    private Text musicVolText;
    private Text fxVolText;

    private ChoiceBox resolutionCB;
    private int selectedResolution;

    private ChoiceBox botDifficultyCB;
    private int botDifficultyIndex;

    private CheckBox fullscreenCB;
    private boolean fullscreen;

    Slider sliderMusic;
    private double music;

    Slider sliderFX;
    private double fx;

    public RightMenu(double width, double height, int botDifficulty, int resIndex, boolean fullscreenBool, double musicVol, double fxVol )
    {
        this.width = width;
        this.height = height;
        selectedResolution = resIndex;
        botDifficultyIndex = botDifficulty;
        fullscreen = fullscreenBool;
        music = musicVol;
        fx = fxVol;

        tabSize = new double[][] {new double[]{800,600}, new double[]{1280,720}, new double[]{1920,1080}};
        //Buttons
        //Fight + Pseudo Textfield
        pseudoText = new Text("Pseudo");
        pseudoText.setFont(Font.loadFont(fontPixelPath,15));
        pseudoText.setFill(Color.WHITE);
        pseudoText.setTranslateY(20);

        pseudoTextField = new TextField();
        pseudoTextField.setPromptText("Unknown");
        pseudoTextField.setTranslateY(60);
        pseudoTextField.setMaxWidth(150);

        fightButton = new Button("Fight");
        fightButton.setFont(Font.loadFont(fontPixelPath, 15));
        fightButton.setTextFill(Color.RED);
        fightButton.setTranslateY(120);
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
        statsButton.setFont(Font.loadFont(fontPixelPath, 15));
        statsButton.setTranslateY(170);
        statsButton.setPrefWidth(100);

        statsButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                if(pseudoTextField.getText().isEmpty()){
                    pseudo = null;
                }else{
                    pseudo = pseudoTextField.getText();
                }
                notifyTimer(GameEvent.REQUIRE_STATS);
            }
        });

        separator = new Rectangle(200,3);
        separator.setFill(Color.WHITE);
        separator.setTranslateY(225);

        resolutionText = new Text("ResoIution");
        resolutionText.setFont(Font.loadFont(fontPixelPath,13));
        resolutionText.setFill(Color.WHITE);
        resolutionText.setTranslateY(260);

        resolutionCB = new ChoiceBox(FXCollections.observableArrayList(
                "800*600", "1280*720", "1920*1080")
        );
        resolutionCB.setTranslateY(290);
        resolutionCB.setPrefWidth(150);
        resolutionCB.getSelectionModel().select(resIndex);

        difficultyText = new Text("DifficuIty");
        difficultyText.setFont(Font.loadFont(fontPixelPath,13));
        difficultyText.setFill(Color.WHITE);
        difficultyText.setTranslateY(320);

        botDifficultyCB = new ChoiceBox(FXCollections.observableArrayList(
                "Easy", "Medium", "Hard")
        );
        botDifficultyCB.setTranslateY(350);
        botDifficultyCB.setPrefWidth(150);
        botDifficultyCB.getSelectionModel().select(botDifficultyIndex);
        botDifficultyCB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                botDifficultyIndex = newValue.intValue();
                notifyTimer(GameEvent.DIFFICULTY_CHANGED);
            }
        });

        fullscreenCB = new CheckBox("FuIIscreen");
        fullscreenCB.setFont(Font.loadFont(fontPixelPath, 13));
        fullscreenCB.setTextFill(Color.WHITE);
        fullscreenCB.setTranslateY(400);
        fullscreenCB.setSelected(fullscreenBool);

        resolutionCB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                selectedResolution = newValue.intValue();
                fullscreen = false;
                fullscreenCB.setSelected(false);
                notifyTimer(GameEvent.SIZE_CHANGED);
            }
        });

        fullscreenCB.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                fullscreen = newValue;
                notifyTimer(GameEvent.FULLSCREEN_CHANGED);
            }
        });

        musicVolText = new Text("Music VoIume");
        musicVolText.setFont(Font.loadFont(fontPixelPath,13));
        musicVolText.setFill(Color.WHITE);
        musicVolText.setTranslateY(440);


        sliderMusic = new Slider(0,100,musicVol);
        sliderMusic.setMaxWidth(150);
        sliderMusic.setTranslateY(470);
        sliderMusic.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                music = newValue.doubleValue();
                notifyTimer(GameEvent.MUSIC_VOLUME_CHANGED);
            }
        });

        fxVolText = new Text("FX VoIume");
        fxVolText.setFont(Font.loadFont(fontPixelPath,13));
        fxVolText.setFill(Color.WHITE);
        fxVolText.setTranslateY(500);

        sliderFX = new Slider(0,100,fxVol);
        sliderFX.setMaxWidth(150);
        sliderFX.setTranslateY(530);
        sliderFX.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                fx = newValue.doubleValue();
                notifyTimer(GameEvent.FX_VOLUME_CHANGED);
            }
        });

        //Leave
        leaveButton = new Button("Leave");
        leaveButton.setFont(Font.loadFont(fontPixelPath, 15));
        leaveButton.setTranslateY(650);
        leaveButton.setPrefWidth(100);

        leaveButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent me){
                Platform.exit();
            }
        });

        panel = new StackPane();
        panel.setPrefWidth(256);
        panel.setTranslateX(width);
        panel.getChildren().addAll(pseudoText,pseudoTextField,fightButton,statsButton,separator,difficultyText,botDifficultyCB,
                resolutionText,resolutionCB,fullscreenCB,musicVolText,sliderMusic,fxVolText,sliderFX,leaveButton);

    }

    public Node getPanel()
    {
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

    public double[][] getResolutions()
    {
        return tabSize;
    }

    public int getSelectedResolution()
    {
        return selectedResolution;
    }

    public int getBotDifficultyIndex()
    {
        return botDifficultyIndex;
    }

    public boolean getFullscreen()
    {
        return fullscreen;
    }

    public double getMusic()
    {
        return music;
    }

    public double getFx()
    {
        return fx;
    }
}
