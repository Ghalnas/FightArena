package view;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import model.Command;
import model.Command.Action;

import java.util.Objects;

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
        this.setFill(Color.BLACK);



        this.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event)
            {
                if (Objects.equals(event.getText(), "q")) left = true;
                if (Objects.equals(event.getText(), "d")) right = true;
                if (Objects.equals(event.getText(), "z")) up = true;
                if (Objects.equals(event.getText(), "s")) down = true;
                if (Objects.equals(event.getText(), "l") && !slash) {
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
                if (Objects.equals(event.getText(), "q")) left = false;
                if (Objects.equals(event.getText(), "d")) right = false;
                if (Objects.equals(event.getText(), "z")) up = false;
                if (Objects.equals(event.getText(), "s")) down = false;
                if (Objects.equals(event.getText(), "l")) {
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
