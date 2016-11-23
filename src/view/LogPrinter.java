package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class LogPrinter implements JavaFXPrinter,Observer
{

    private Rectangle background;
    private Text title;
    private double startX,startY;
    private ArrayList<String[]> listMessages;

    public LogPrinter(double startX, double startY, double width, double height)
    {
        background = new Rectangle(startX, startY, width, height);
        background.setFill(Color.rgb(0, 0, 0, 0.7));
        title = new Text(startX, startY+20, "Logs");
        title.setFill(Color.WHITE);
        listMessages = new ArrayList<>();
        this.startX = startX;
        this.startY = startY;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if (listMessages.size() > 5) {
            listMessages.remove(0);
        }
        if (arg instanceof String[]) {
            listMessages.add((String[]) arg);
        }

        if (arg instanceof String) {
            listMessages.add((String[]) arg);
        }

    }

    @Override
    public Node getNode()
    {
        Group panel = new Group();
        panel.getChildren().add(background);
        panel.getChildren().add(title);
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
            Group log = new Group();
            Text logLvl = new Text(startX+3, startY + 40 +i*20, "["+message[0].toUpperCase()+"] : ");
            logLvl.setFill(color);
            Text logMessage = new Text(startX+3 + message[0].length()*12,startY  + 40 +i*20,message[1]);
            logMessage.setFill(Color.WHITE);
            log.getChildren().addAll(logLvl,logMessage);
            panel.getChildren().add(log);
        }
        return panel;
    }
}
