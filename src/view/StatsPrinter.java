package view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Stat;

import java.util.Map;

public class StatsPrinter {

    private ImageView background;

    private Map<String, Stat> map;
    private final String fontPixelPath = "file:assets/font/Pixeled.ttf";
    private StackPane panel;

    private Text stats;
    private final String general = "General";
    private double width;


    public StatsPrinter(Map<String, Stat> map, double width, double translateX, double translateY)
    {
        this.map = map;
        this.width = width;
        background = new ImageView(new Image("file:assets/image/background-1024.jpg"));

        panel = new StackPane();
        panel.setAlignment(Pos.TOP_CENTER);
        panel.setPrefWidth(width);
        panel.setTranslateX(translateX);
        panel.setTranslateY(translateY);
        panel.getChildren().add(background);
        statsPanel(null);
    }

    public Node getPanel()
    {
        return panel;
    }


    public void statsPanel(String pseudo)
    {
        panel.getChildren().remove(1,panel.getChildren().size());
        if (pseudo != null) {
            panel.getChildren().add(getStats(pseudo,0));
            panel.getChildren().add(drawPie(pseudo,width/2));
        } else {
            panel.getChildren().add(getStats(general,0));
            panel.getChildren().add(drawPie(general,width/2));
        }
    }

    private StackPane getStats(String pseudo, double alignX)
    {
        StackPane sp = new StackPane();
        sp.setTranslateX(alignX);
        sp.setPrefWidth(width/2);
        sp.setAlignment(Pos.TOP_LEFT);
        int startY = 190;
        int intervalY = 30;

        StackPane center = new StackPane();
        center.setAlignment(Pos.TOP_CENTER);
        center.setTranslateX(-256);

        Text general = new Text(pseudo.toUpperCase() + " :");
        general.setFont(Font.loadFont(fontPixelPath,20));
        general.setTranslateY(140);

        Text victories = new Text( Integer.toString(map.get(pseudo).getVictories()) + " Victories");
        victories.setTranslateY(startY);
        victories.setFont(Font.loadFont(fontPixelPath,10));

        Text loses = new Text(Integer.toString(map.get(pseudo).getLoses()) + " Defeats" );
        loses.setTranslateY(startY+intervalY);
        loses.setFont(Font.loadFont(fontPixelPath,10));

        Text hit = new Text(Integer.toString(map.get(pseudo).getHit()) + " Hits");
        hit.setTranslateY(startY+intervalY*2);
        hit.setFont(Font.loadFont(fontPixelPath,10));

        Text misses = new Text(Integer.toString(map.get(pseudo).getMisses()) + " Missed hits" );
        misses.setTranslateY(startY+intervalY*3);
        misses.setFont(Font.loadFont(fontPixelPath,10));

        Text hitTaken = new Text(Integer.toString(map.get(pseudo).getHitTaken()) + " Taken hits" );
        hitTaken.setTranslateY(startY+intervalY*4);
        hitTaken.setFont(Font.loadFont(fontPixelPath,10));

        Text spinTime = new Text(String.format("%.1f",(map.get(pseudo).getSpinTime())/60) + "  Spin seconds" );
        spinTime.setTranslateY(startY+intervalY*5);
        spinTime.setFont(Font.loadFont(fontPixelPath,10));

        Text spinVictories = new Text(Integer.toString(map.get(pseudo).getSpinVictory()) + " Spin victories" );
        spinVictories.setTranslateY(startY+intervalY*6);
        spinVictories.setFont(Font.loadFont(fontPixelPath,10));

        Text spinLoses = new Text(Integer.toString(map.get(pseudo).getSpinLose()) + " Spin defeats" );
        spinLoses.setTranslateY(startY+intervalY*7);
        spinLoses.setFont(Font.loadFont(fontPixelPath,10));

        Text lightningVictories = new Text(Integer.toString(map.get(pseudo).getLightningWin()) + " Lightning victories" );
        lightningVictories.setTranslateY(startY+intervalY*8);
        lightningVictories.setFont(Font.loadFont(fontPixelPath,10));

        Text lightningsUsed = new Text(Integer.toString(map.get(pseudo).getLightningUsed()) + " Lightning used" );
        lightningsUsed.setTranslateY(startY+intervalY*9);
        lightningsUsed.setFont(Font.loadFont(fontPixelPath,10));

        Text lightningLoses = new Text(Integer.toString(map.get(pseudo).getLightningLose()) + " Lightning defeats" );
        lightningLoses.setTranslateY(startY+intervalY*10);
        lightningLoses.setFont(Font.loadFont(fontPixelPath,10));

        Text goldVictories = new Text(Integer.toString(map.get(pseudo).getGoldVictory()) + " GoId victories");
        goldVictories.setTranslateY(startY+intervalY*11);
        goldVictories.setFont(Font.loadFont(fontPixelPath,10));

        Text goldLoses = new Text(Integer.toString(map.get(pseudo).getGoldLose()) + " GoId defeats");
        goldLoses.setTranslateY(startY+intervalY*12);
        goldLoses.setFont(Font.loadFont(fontPixelPath,10));

        Text goldTime = new Text(String.format("%.1f",(map.get(pseudo).getGoldTime())/60) + " GoId seconds");
        goldTime.setTranslateY(startY+intervalY*13);
        goldTime.setFont(Font.loadFont(fontPixelPath,10));

        Text healReceived = new Text(Double.toString(map.get(pseudo).getHealReceived()) + " HeaI received");
        healReceived.setTranslateY(startY+intervalY*14);
        healReceived.setFont(Font.loadFont(fontPixelPath,10));

        center.getChildren().addAll(general,victories,loses,hit, misses, hitTaken,spinTime,spinVictories,spinLoses,lightningVictories,lightningsUsed,lightningLoses,goldVictories,goldLoses,goldTime,healReceived);
        sp.getChildren().add(center);
        return sp;
    }

    private StackPane drawPie(String pseudo, double alignX)
    {
        StackPane sp = new StackPane();
        sp.setTranslateX(alignX);
        sp.setPrefWidth(width/2);
        sp.setAlignment(Pos.TOP_LEFT);
        PieChart chart = new PieChart();
        int all = map.get(pseudo).getVictories();
        int spin = map.get(pseudo).getSpinVictory();
        int lightning = map.get(pseudo).getLightningWin();
        int gold = map.get(pseudo).getGoldVictory();
        int normal = all - spin -lightning - gold;
        chart.setTitle("Victories");
        chart.getData().setAll(
                new PieChart.Data("Normal ("+String.format("%.2f", (normal*100f)/all)+" %)", normal),
                new PieChart.Data("Spin ("+String.format("%.2f", (spin*100f)/all)+" %)", spin),
                new PieChart.Data("Lightning ("+String.format("%.2f", (lightning*100f)/all)+" %)", lightning),
                new PieChart.Data("Gold ("+String.format("%.2f", (gold*100f)/all)+" %)", gold)
        );
        chart.setTranslateX(-256);
        chart.setLabelsVisible(true);
        chart.setLegendVisible(false);
        chart.setStyle("-fx-font-size:40px;");
        chart.setScaleX(0.5);
        chart.setScaleY(0.5);
        sp.getChildren().add(chart);
        return sp;
    }
}
