package view;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class ScorePrinter implements JavaFXPrinter, Observer {

    //private Engine engine;
    private Rectangle scores;
    private Text title;
    private double scoresViewX;
    private double scoresViewY;
    private double scoresViewWidth;
    private double scoresViewHeight;
    private double scoresViewCons;

    public ScorePrinter(double scoresViewX, double scoresViewY, double scoresViewWidth, double scoresViewHeight) {

        this.scoresViewX = scoresViewX;
        this.scoresViewY = scoresViewY;
        this.scoresViewWidth = scoresViewWidth;
        this.scoresViewHeight = scoresViewHeight;
        this.scoresViewCons = 20;

        scores = new Rectangle(scoresViewX, scoresViewY, scoresViewWidth, scoresViewHeight);
        scores.setFill(Color.rgb(0, 0, 0, 0.7));
        title = new Text((scoresViewX + scoresViewWidth / 2), scoresViewY + scoresViewCons, "Scores");
        title.setFill(Color.rgb(255, 255, 255));
    }


    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public Node getNode() {
        Group group = new Group();
        // TODO : Add real Score  here via observer
        //Display score
        Text scorePlayer = new Text(scoresViewX + scoresViewCons, scoresViewY + 2 * scoresViewCons, "Score Player : " );
        scorePlayer.setFill(Color.rgb(255, 255, 255));
        Text scoreBot = new Text(scoresViewX + scoresViewCons, scoresViewY + 3 * scoresViewCons, "Score Bot : " );
        scoreBot.setFill(Color.rgb(255, 255, 255));

        group.getChildren().addAll(scores, title, scorePlayer, scoreBot);

        return group;

    }

}
