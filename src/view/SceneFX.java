package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Command;
import model.Command.Action;
import model.GameEvent;

import java.util.Objects;

public class SceneFX extends Scene implements TimerObservable
{
    private boolean left,right,up,down,slash,isSlashing;
    private double sizeX,sizeY;
    private TimerObserver o;

    public SceneFX(Parent root, double width, double height)
    {
        super(root);
        left = false;
        right = false;
        up = false;
        down = false;
        slash = false;
        isSlashing = false;
        sizeX = width;
        sizeY = height;

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
                if (event.getCode() == KeyCode.ESCAPE) notifyTimer(GameEvent.REQUIRE_MENU);
                if (Objects.equals(event.getText(), "l")) {
                    slash = false;
                    isSlashing = false;
                }
                event.consume();
            }
        });
        this.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                sizeX = newSceneWidth.doubleValue();
                notifyTimer(GameEvent.SIZE_CHANGED);
            }
        });
        this.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                sizeY = newSceneHeight.doubleValue()+39;
                notifyTimer(GameEvent.SIZE_CHANGED);
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

    public double getShrinkX()
    {
        return sizeX;
    }

    public double getShrinkY()
    {
        return sizeY;
    }

    @Override
    public void bindTimer(TimerObserver o) {
        this.o = o;
    }

    @Override
    public void notifyTimer(Object arg) {
        if (o != null) {
            o.update(this, arg);
        }
    }

}
