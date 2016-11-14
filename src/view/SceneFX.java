package view;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import model.Command;
import model.Command.Action;

public class SceneFX extends Scene
{
    private boolean left,right,up,down,slash,isSlashing;

    public SceneFX(Parent root)
    {
        super(root);
        left = false;
        right = false;
        up = false;
        down = false;
        slash = false;
        isSlashing = false;

        this.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode()== KeyCode.Q) left = true;
                if (event.getCode()==KeyCode.D) right = true;
                if (event.getCode()==KeyCode.Z) up = true;
                if (event.getCode()==KeyCode.S) down = true;
                if (event.getCode()==KeyCode.L && !slash) {
                    slash = true;
                    isSlashing = false;
                }
                event.consume();
            }
        });

        this.setOnKeyReleased(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode()== KeyCode.Q) left = false;
                if (event.getCode()==KeyCode.D) right = false;
                if (event.getCode()==KeyCode.Z) up = false;
                if (event.getCode()==KeyCode.S) down = false;
                if (event.getCode()==KeyCode.L) {
                    slash = false;
                    isSlashing = false;
                }
                event.consume();
            }
        });
    }

    public Command getCommand()
    {
        double vX = 0, vY =  0;
        Action action = Action.NONE;
        if(left) vX += -1;
        if(right) vX += 1;
        if(up) vY += -1;
        if(down) vY += 1;
        if(slash && !isSlashing) {
            action = Action.SLASH;
            isSlashing = true;
        }
        return new Command(vX,vY, action);
    }
}
