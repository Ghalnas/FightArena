package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.StatsReader;

public class StatsPrinter {

    private ImageView background;

    private StatsReader statsReader;
    private String fontPixelPath = "file:assets/font/Pixeled.ttf";

    private Text stats;
    private Text fighters;
    private Text player;

    private Text victoriesGeneral;
    private Text losesGeneral;
    private Text hitGeneral;
    private Text missesGeneral;
    private Text hitTakenGeneral;
    private Text spinTimeGeneral;
    private Text spinVictoryGeneral;
    private Text spinLoseGeneral;
    private Text lightningWinGeneral;
    private Text lightningUsedGeneral;
    private Text lightningLoseGeneral;
    private Text goldVictoryGeneral;
    private Text goldLoseGeneral;
    private Text goldTimeGeneral;
    private Text healReceivedGeneral;

    private PieChart victoriesLosesChart;

    private Text victoriesPlayer;
    private Text losesPlayer;
    private Text hitPlayer;
    private Text missesPlayer;
    private Text hitTakenPlayer;
    private Text spinTimePlayer;
    private Text spinVictoryPlayer;
    private Text spinLosePlayer;
    private Text lightningWinPlayer;
    private Text lightningUsedPlayer;
    private Text lightningLosePlayer;
    private Text goldVictoryPlayer;
    private Text goldLosePlayer;
    private Text goldTimePlayer;
    private Text healReceivedPlayer;


    public StatsPrinter()
    {
        background = new ImageView(new Image("file:assets/image/background-1024.jpg"));

        stats = new Text("STATS");
        stats.setFont(Font.loadFont(fontPixelPath,40));
        stats.setLayoutX(425);
        stats.setLayoutY(150);

        fighters = new Text("Fighters count :");
        fighters.setFont(Font.loadFont(fontPixelPath,20));
        fighters.setLayoutY(200);
        fighters.setLayoutX(100);

        statsReader = new StatsReader();

        //General stats
        victoriesGeneral = new Text( Integer.toString(statsReader.getVictory("General")) + " victories");
        victoriesGeneral.setLayoutY(250);
        victoriesGeneral.setLayoutX(50);
        victoriesGeneral.setFont(Font.loadFont(fontPixelPath,10));

        losesGeneral = new Text(Integer.toString(statsReader.getLose("General")) + " Ioses" );
        losesGeneral.setLayoutY(300);
        losesGeneral.setLayoutX(50);
        losesGeneral.setFont(Font.loadFont(fontPixelPath,10));

        hitGeneral = new Text(Integer.toString(statsReader.getHit("General")) + " hits");
        hitGeneral.setLayoutY(350);
        hitGeneral.setLayoutX(50);
        hitGeneral.setFont(Font.loadFont(fontPixelPath,10));

        missesGeneral = new Text(Integer.toString(statsReader.getMiss("General")) + " misses hits" );
        missesGeneral.setLayoutY(400);
        missesGeneral.setLayoutX(50);
        missesGeneral.setFont(Font.loadFont(fontPixelPath,10));

        hitTakenGeneral = new Text(Integer.toString(statsReader.getHitTaken("General")) + " taken hits" );
        hitTakenGeneral.setLayoutY(450);
        hitTakenGeneral.setLayoutX(50);
        hitTakenGeneral.setFont(Font.loadFont(fontPixelPath,10));

        spinTimeGeneral = new Text(Integer.toString(statsReader.getSpinTime("General")) + " spin time" );
        spinTimeGeneral.setLayoutY(500);
        spinTimeGeneral.setLayoutX(50);
        spinTimeGeneral.setFont(Font.loadFont(fontPixelPath,10));

        spinVictoryGeneral = new Text(Integer.toString(statsReader.getSpinVictory("General")) + " spin victories" );
        spinVictoryGeneral.setLayoutY(550);
        spinVictoryGeneral.setLayoutX(50);
        spinVictoryGeneral.setFont(Font.loadFont(fontPixelPath,10));

        spinLoseGeneral = new Text(Integer.toString(statsReader.getSpinLose("General")) + " spin Ioses" );
        spinLoseGeneral.setLayoutY(600);
        spinLoseGeneral.setLayoutX(50);
        spinLoseGeneral.setFont(Font.loadFont(fontPixelPath,10));

        lightningWinGeneral = new Text(Integer.toString(statsReader.getLightningWin("General")) + " Iightning victories" );
        lightningWinGeneral.setLayoutY(650);
        lightningWinGeneral.setLayoutX(50);
        lightningWinGeneral.setFont(Font.loadFont(fontPixelPath,10));

        lightningUsedGeneral = new Text(Integer.toString(statsReader.getLightningUse("General")) + " Iightning used" );
        lightningUsedGeneral.setLayoutY(250);
        lightningUsedGeneral.setLayoutX(300);
        lightningUsedGeneral.setFont(Font.loadFont(fontPixelPath,10));

        lightningLoseGeneral = new Text(Integer.toString(statsReader.getLightningLose("General")) + " Iightning Ioses" );
        lightningLoseGeneral.setLayoutY(300);
        lightningLoseGeneral.setLayoutX(300);
        lightningLoseGeneral.setFont(Font.loadFont(fontPixelPath,10));

        goldVictoryGeneral = new Text(Integer.toString(statsReader.getGoldVictory("General")) + " goId victories");
        goldVictoryGeneral.setLayoutY(350);
        goldVictoryGeneral.setLayoutX(300);
        goldVictoryGeneral.setFont(Font.loadFont(fontPixelPath,10));

        goldLoseGeneral = new Text(Integer.toString(statsReader.getGoldLose("General")) + " goId Ioses");
        goldLoseGeneral.setLayoutY(400);
        goldLoseGeneral.setLayoutX(300);
        goldLoseGeneral.setFont(Font.loadFont(fontPixelPath,10));

        goldTimeGeneral = new Text(Integer.toString(statsReader.getGoldTime("General")) + " goId times");
        goldTimeGeneral.setLayoutY(450);
        goldTimeGeneral.setLayoutX(300);
        goldTimeGeneral.setFont(Font.loadFont(fontPixelPath,10));

        healReceivedGeneral = new Text(Double.toString(statsReader.getHealReceived("General")) + " heaI received");
        healReceivedGeneral.setLayoutY(500);
        healReceivedGeneral.setLayoutX(300);
        healReceivedGeneral.setFont(Font.loadFont(fontPixelPath,10));

        //Generals stats only if pseudoPlayer is null
        victoriesLosesChart = new PieChart();
        victoriesLosesChart.setTitle("Type of victories");
        victoriesLosesChart.getData().setAll(
                new PieChart.Data("Spin victories", statsReader.getSpinVictory("General")),
                new PieChart.Data("Lightning victories", statsReader.getLightningWin("General")),
                new PieChart.Data("Gold victories", statsReader.getGoldVictory("General"))
        );
        victoriesLosesChart.setLayoutX(500);
        victoriesLosesChart.setLayoutY(200);
        victoriesLosesChart.setLegendVisible(false);

    }

    public Node getPanel(String pseudoPlayer)
    {
        Group panel = new Group();
        panel.getChildren().add(background);
        panel.getChildren().add(stats);
        panel.getChildren().add(fighters);

        panel.getChildren().add(victoriesGeneral);
        panel.getChildren().add(losesGeneral);
        panel.getChildren().add(hitGeneral);
        panel.getChildren().add(missesGeneral);
        panel.getChildren().add(hitTakenGeneral);
        panel.getChildren().add(spinTimeGeneral);
        panel.getChildren().add(spinVictoryGeneral);
        panel.getChildren().add(spinLoseGeneral);
        panel.getChildren().add(lightningWinGeneral);
        panel.getChildren().add(lightningUsedGeneral);
        panel.getChildren().add(lightningLoseGeneral);
        panel.getChildren().add(goldVictoryGeneral);
        panel.getChildren().add(goldLoseGeneral);
        panel.getChildren().add(goldTimeGeneral);
        panel.getChildren().add(healReceivedGeneral);

        if(pseudoPlayer == null){
            panel.getChildren().add(victoriesLosesChart);
        }else if(statsReader.existPlayer(pseudoPlayer)){
                player = new Text(pseudoPlayer + " count :");
                player.setFont(Font.loadFont(fontPixelPath,20));
                player.setLayoutY(200);
                player.setLayoutX(700);

                //Player Stats
                victoriesPlayer = new Text( Integer.toString(statsReader.getVictory(pseudoPlayer)) + " victories");
                victoriesPlayer.setLayoutY(250);
                victoriesPlayer.setLayoutX(600);
                victoriesPlayer.setFont(Font.loadFont(fontPixelPath,10));

                losesPlayer = new Text(Integer.toString(statsReader.getLose(pseudoPlayer)) + " Ioses" );
                losesPlayer.setLayoutY(300);
                losesPlayer.setLayoutX(600);
                losesPlayer.setFont(Font.loadFont(fontPixelPath,10));

                hitPlayer = new Text(Integer.toString(statsReader.getHit(pseudoPlayer)) + " hits");
                hitPlayer.setLayoutY(350);
                hitPlayer.setLayoutX(600);
                hitPlayer.setFont(Font.loadFont(fontPixelPath,10));

                missesPlayer = new Text(Integer.toString(statsReader.getMiss(pseudoPlayer)) + " misses hits" );
                missesPlayer.setLayoutY(400);
                missesPlayer.setLayoutX(600);
                missesPlayer.setFont(Font.loadFont(fontPixelPath,10));

                hitTakenPlayer = new Text(Integer.toString(statsReader.getHitTaken(pseudoPlayer)) + " taken hits" );
                hitTakenPlayer.setLayoutY(450);
                hitTakenPlayer.setLayoutX(600);
                hitTakenPlayer.setFont(Font.loadFont(fontPixelPath,10));

                spinTimePlayer = new Text(Integer.toString(statsReader.getSpinTime(pseudoPlayer)) + " spin time" );
                spinTimePlayer.setLayoutY(500);
                spinTimePlayer.setLayoutX(600);
                spinTimePlayer.setFont(Font.loadFont(fontPixelPath,10));

                spinVictoryPlayer = new Text(Integer.toString(statsReader.getSpinVictory(pseudoPlayer)) + " spin victories" );
                spinVictoryPlayer.setLayoutY(550);
                spinVictoryPlayer.setLayoutX(600);
                spinVictoryPlayer.setFont(Font.loadFont(fontPixelPath,10));

                spinLosePlayer = new Text(Integer.toString(statsReader.getSpinLose(pseudoPlayer)) + " spin Ioses" );
                spinLosePlayer.setLayoutY(600);
                spinLosePlayer.setLayoutX(600);
                spinLosePlayer.setFont(Font.loadFont(fontPixelPath,10));

                lightningWinPlayer = new Text(Integer.toString(statsReader.getLightningWin(pseudoPlayer)) + " Iightning victories" );
                lightningWinPlayer.setLayoutY(650);
                lightningWinPlayer.setLayoutX(600);
                lightningWinPlayer.setFont(Font.loadFont(fontPixelPath,10));

                lightningUsedPlayer = new Text(Integer.toString(statsReader.getLightningUse(pseudoPlayer)) + " Iightning used" );
                lightningUsedPlayer.setLayoutY(250);
                lightningUsedPlayer.setLayoutX(850);
                lightningUsedPlayer.setFont(Font.loadFont(fontPixelPath,10));

                lightningLosePlayer = new Text(Integer.toString(statsReader.getLightningLose(pseudoPlayer)) + " Iightning Ioses" );
                lightningLosePlayer.setLayoutY(300);
                lightningLosePlayer.setLayoutX(850);
                lightningLosePlayer.setFont(Font.loadFont(fontPixelPath,10));

                goldVictoryPlayer = new Text(Integer.toString(statsReader.getGoldVictory(pseudoPlayer)) + " goId victories");
                goldVictoryPlayer.setLayoutY(350);
                goldVictoryPlayer.setLayoutX(850);
                goldVictoryPlayer.setFont(Font.loadFont(fontPixelPath,10));

                goldLosePlayer = new Text(Integer.toString(statsReader.getGoldLose(pseudoPlayer)) + " goId Ioses");
                goldLosePlayer.setLayoutY(400);
                goldLosePlayer.setLayoutX(850);
                goldLosePlayer.setFont(Font.loadFont(fontPixelPath,10));

                goldTimePlayer = new Text(Integer.toString(statsReader.getGoldTime(pseudoPlayer)) + " goId times");
                goldTimePlayer.setLayoutY(450);
                goldTimePlayer.setLayoutX(850);
                goldTimePlayer.setFont(Font.loadFont(fontPixelPath,10));

                healReceivedPlayer = new Text(Double.toString(statsReader.getHealReceived(pseudoPlayer)) + " heaI received");
                healReceivedPlayer.setLayoutY(500);
                healReceivedPlayer.setLayoutX(850);
                healReceivedPlayer.setFont(Font.loadFont(fontPixelPath,10));

                panel.getChildren().add(player);

                panel.getChildren().add(victoriesPlayer);
                panel.getChildren().add(losesPlayer);
                panel.getChildren().add(hitPlayer);
                panel.getChildren().add(missesPlayer);
                panel.getChildren().add(hitTakenPlayer);
                panel.getChildren().add(spinTimePlayer);
                panel.getChildren().add(spinVictoryPlayer);
                panel.getChildren().add(spinLosePlayer);
                panel.getChildren().add(lightningWinPlayer);
                panel.getChildren().add(lightningUsedPlayer);
                panel.getChildren().add(lightningLosePlayer);
                panel.getChildren().add(goldVictoryPlayer);
                panel.getChildren().add(goldLosePlayer);
                panel.getChildren().add(goldTimePlayer);
                panel.getChildren().add(healReceivedPlayer);

        }else{
            panel.getChildren().add(victoriesLosesChart);
        }
        return panel;
    }

}
