package view;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import controller.Engine;


public class ScorePrinter implements JavaFXPrinter, Observer {

    private Rectangle scores;
    private Text title;
    private double scoresViewX;
    private double scoresViewY;
    private double scoresViewWidth;
    private double scoresViewHeight;
    private final double scoresViewCons = 20;
    private int scorePlayer;
    private int scoreBot;
    int PLAYER = 0;
    int BOT = 1;

    public ScorePrinter(double scoresViewX, double scoresViewY, double scoresViewWidth, double scoresViewHeight) {

        this.scoresViewX = scoresViewX;
        this.scoresViewY = scoresViewY;
        this.scoresViewWidth = scoresViewWidth;
        this.scoresViewHeight = scoresViewHeight;

        scores = new Rectangle(scoresViewX, scoresViewY, scoresViewWidth, scoresViewHeight);
        scores.setFill(Color.rgb(0, 0, 0, 0.7));
        title = new Text((scoresViewX + scoresViewWidth / 2), scoresViewY + scoresViewCons, "Scores");
        title.setFill(Color.rgb(255, 255, 255));
    }

    @Override
    public void update(Observable o, Object arg) {
        if ( o instanceof Engine && arg instanceof int[]) {
            scorePlayer = ((int[])arg)[PLAYER];
            scoreBot = ((int[])arg)[BOT];
        }
    }

    @Override
    public Node getNode() {
        Group group = new Group();
        //Display score
        Text scorePlayer = new Text(scoresViewX + scoresViewCons, scoresViewY + 2 * scoresViewCons, "Score Player : "+this.scorePlayer);
        scorePlayer.setFill(Color.rgb(255, 255, 255));
        Text scoreBot = new Text(scoresViewX + scoresViewCons, scoresViewY + 3 * scoresViewCons, "Score Bot : "+this.scoreBot);
        scoreBot.setFill(Color.rgb(255, 255, 255));
        group.getChildren().addAll(scores, title, scorePlayer, scoreBot);
        return group;
    }

}
