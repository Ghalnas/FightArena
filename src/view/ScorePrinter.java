package view;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import controller.Engine;


public class ScorePrinter implements JavaFXPrinter, Observer {

    private Text title;
    private Text scorePlayerText;
    private Text scoreBotText;
    private Text separator;

    private StackPane panel;

    int PLAYER = 0;
    int BOT = 1;
    private String fontPixelPath = "file:assets/font/Pixeled.ttf";

    public ScorePrinter(double width, double translateX, double translateY) {

        title = new Text("Scores");
        title.setFont(Font.loadFont(fontPixelPath,30));
        title.setFill(Color.WHITE);
        title.setTranslateY(20);

        separator = new Text("-");
        separator.setFont(Font.loadFont(fontPixelPath,50));
        separator.setFill(Color.WHITE);
        separator.setTranslateY(160);

        scorePlayerText = new Text("0");
        scorePlayerText.setFont(Font.loadFont(fontPixelPath,50));
        scorePlayerText.setFill(Color.GREEN);
        scorePlayerText.setTranslateY(150);
        scorePlayerText.setTranslateX(-50);

        scoreBotText = new Text("0");
        scoreBotText.setFont(Font.loadFont(fontPixelPath,50));
        scoreBotText.setFill(Color.GRAY);
        scoreBotText.setTranslateY(150);
        scoreBotText.setTranslateX(50);

        panel = new StackPane();
        panel.setPrefWidth(width);
        panel.setTranslateX(translateX);
        panel.setTranslateY(translateY);
        panel.getChildren().addAll(title, scorePlayerText, separator, scoreBotText);
    }

    @Override
    public void update(Observable o, Object arg) {
        if ( o instanceof Engine && arg instanceof int[]) {
            scorePlayerText.setText(Integer.toString(((int[])arg)[PLAYER]));
            scoreBotText.setText(Integer.toString(((int[])arg)[BOT]));
        }
    }

    @Override
    public Node getNode() {
        return panel;
    }

}
