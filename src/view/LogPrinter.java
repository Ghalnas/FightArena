package view;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class LogPrinter implements JavaFXPrinter,Observer
{

    private Text title;
    private ArrayList<String[]> listMessages;
    private StackPane panel;
    private String fontPixelPath = "file:assets/font/Pixeled.ttf";

    public LogPrinter(double width, double translateX, double translateY)
    {
        title = new Text("Logs");
        title.setFont(Font.loadFont(fontPixelPath,30));
        title.setTranslateY(20);
        title.setFill(Color.WHITE);
        title.setTranslateX(70);
        listMessages = new ArrayList<>();

        panel = new StackPane();
        panel.setPrefWidth(width);
        panel.setTranslateX(translateX);
        panel.setTranslateY(translateY);
        panel.getChildren().add(title);
        panel.setAlignment(Pos.CENTER_LEFT);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if (arg == null) {
            listMessages.clear();
        }
        else if (arg instanceof String[]) {
            listMessages.add((String[]) arg);
        }
        else if (arg instanceof String) {
            listMessages.add((String[]) arg);
        }
        if (listMessages.size() > 13) {
            listMessages.remove(0);
        }
    }

    @Override
    public Node getNode()
    {
        panel.getChildren().remove(1,panel.getChildren().size());
        for (int i = 0 ; i < listMessages.size() ; i++) {
            Color color = null;
            String[] message = listMessages.get(i);
            switch (message[0]) {
                case "info":
                    color = Color.rgb(51,204,204);
                    break;
                case "warning":
                    color = Color.rgb(255,153,51);
                    break;
                case "critical":
                    color = Color.rgb(255,0,0);
                    break;
            }
            Group group = new Group();
            Text logLvl = new Text("["+message[0].toUpperCase()+"] : ");
            logLvl.setFill(color);
            Text logMessage = new Text(message[1]);
            logMessage.setFill(Color.WHITE);
            logMessage.setTranslateX(45);
            group.getChildren().addAll(logLvl,logMessage);
            group.setTranslateY(70 + i*20);

            panel.getChildren().add(group);
        }
        return panel;
    }
}
