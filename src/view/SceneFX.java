package view;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import model.Command;

public class SceneFX extends Scene
{
    private boolean left,right,up,down;

    public SceneFX(Parent root)
    {
        super(root);
        left = false;
        right = false;
        up = false;
        down = false;
        this.setFill(Color.BLACK);
        this.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode()== KeyCode.LEFT) left = true;
                if (event.getCode()==KeyCode.RIGHT) right = true;
                if (event.getCode()==KeyCode.UP) up = true;
                if (event.getCode()==KeyCode.DOWN) down = true;
                event.consume();
            }
        });

        this.setOnKeyReleased(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode()== KeyCode.LEFT) left = false;
                if (event.getCode()==KeyCode.RIGHT) right = false;
                if (event.getCode()==KeyCode.UP) up = false;
                if (event.getCode()==KeyCode.DOWN) down = false;
                event.consume();
            }
        });
    }

    public Command getCommand()
    {
        int vX = 0, vY =  0;
        if(left) vX += -1;
        if(right) vX += 1;
        if(up) vY += -1;
        if(down) vY += 1;
        return new Command(vX,vY);
    }
}
