package controller;

import javafx.animation.AnimationTimer;
import model.GameEvent;
import view.*;

public class GameController implements TimerObserver
{

    private SceneFX scene;
    private Engine engine;
    private WindowViewer window;
    private AnimationTimer animationTimer;

    public GameController(SceneFX scene, Engine engine, WindowViewer window)
    {

        this.scene = scene;
        this.engine = engine;
        this.window = window;
        scene.setRoot(window.getMainPanel(scene.getShrinkX(), scene.getShrinkY()));
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                scene.setRoot(window.getGamePanel(scene.getShrinkX(), scene.getShrinkY()));
                engine.run(scene.getCommand());
            }
        };
    }

    @Override
    public void update(Object source, Object arg) {
        if (arg instanceof GameEvent)
        switch ((GameEvent)arg) {
            case SIZE_CHANGED:
                window.resizePanel(scene.getShrinkX(), scene.getShrinkY());
                break;
            case REQUIRE_GAME:
                engine.setPseudo(((RightMenu)source).getPseudo());
                scene.setRoot(window.getGamePanel(scene.getShrinkX(), scene.getShrinkY()));
                animationTimer.start();
                break;
            case REQUIRE_MENU:
                animationTimer.stop();
                engine.reinitScores();
                engine.reinit();
                scene.setRoot(window.getMainPanel(scene.getShrinkX(), scene.getShrinkY()));
                break;
            case REQUIRE_STATS:

                break;
            case REQUIRE_SETTING:

                break;
        }
    }
}

