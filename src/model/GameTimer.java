package model;

import controller.Engine;
import javafx.animation.AnimationTimer;
import view.SceneFX;
import view.TimerObserver;
import view.WindowViewer;

public class GameTimer extends AnimationTimer implements TimerObserver
{

    private SceneFX scene;
    private Engine engine;
    private WindowViewer window;

    public GameTimer(SceneFX scene, Engine engine, WindowViewer window)
    {

        this.scene = scene;
        this.engine = engine;
        this.window = window;
        scene.setRoot(window.getMainPanel(scene.getShrinkX(), scene.getShrinkY()));
    }

    @Override
    public void handle(long now)
    {
        scene.setRoot(window.getGamePanel(scene.getShrinkX(), scene.getShrinkY()));
        engine.run(scene.getCommand());
    }

    @Override
    public void update(TimerEvent e)
    {
        update(e,null);
    }

    @Override
    public void update(TimerEvent e, String pseudo) {
        switch (e) {
            case REQUIRE_GAME:
                engine.setPseudo(pseudo);
                scene.setRoot(window.getGamePanel(scene.getShrinkX(), scene.getShrinkY()));
                this.start();
                break;
            case REQUIRE_MENU:
                this.stop();
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

