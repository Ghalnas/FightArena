package controller;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.GameEvent;
import model.MusicPlayer;
import model.ParamWriter;
import view.*;

public class GameController implements TimerObserver
{

    private SceneFX scene;
    private Engine engine;
    private WindowViewer window;
    private AnimationTimer animationTimer;
    private Stage stage;
    private double screenWidth, screenHeight;
    private ParamWriter params;
    private MusicPlayer musicPlayer;

    public GameController(Stage stage, SceneFX scene, Engine engine, WindowViewer window, ParamWriter params, double volMusic)
    {
        this.stage = stage;
        this.scene = scene;
        this.engine = engine;
        this.window = window;
        this.params = params;
        musicPlayer = new MusicPlayer(volMusic);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        screenWidth = primaryScreenBounds.getWidth();
        screenHeight = primaryScreenBounds.getHeight();
        scene.setRoot(window.getMainPanel(scene.getShrinkX(), scene.getShrinkY()));
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                scene.setRoot(window.getGamePanel(scene.getShrinkX(), scene.getShrinkY()));
                engine.run(scene.getCommand());
            }
        };
        musicPlayer.startMenu();
    }

    @Override
    public void update(Object source, Object arg) {
        if (arg instanceof GameEvent)
            switch ((GameEvent)arg) {
                case SIZE_CHANGED:
                    if (source instanceof RightMenu) {
                        RightMenu menu = (RightMenu)source;
                        int index = menu.getSelectedResolution();
                        double[] size = menu.getResolutions()[index];
                        double width = size[0];
                        double height = size[1];
                        stage.setX(screenWidth/2 - width/2);
                        stage.setY(screenHeight/2 - height/2);
                        stage.setWidth(size[0]);
                        stage.setHeight(size[1]);
                        params.writeSelectedRes(index);
                        params.writeWidth(size[0]);
                        params.writeHeight(size[1]);
                        params.writeFullscreen(false);
                    }
                    window.resizePanel(scene.getShrinkX(), scene.getShrinkY());
                    break;
                case DIFFICULTY_CHANGED:
                    int index = ((RightMenu)source).getBotDifficultyIndex();
                    engine.setStrategy(index);
                    params.writeBodDifficulty(index);
                    break;
                case FULLSCREEN_CHANGED:
                    boolean bool = ((RightMenu)source).getFullscreen();
                    stage.setFullScreen(bool);
                    params.writeFullscreen(bool);
                    break;
                case MUSIC_VOLUME_CHANGED:
                    double volumeMusic = ((RightMenu)source).getMusic();
                    musicPlayer.setVolMusic(volumeMusic/100);
                    params.writeMusicVol(volumeMusic);
                    break;
                case FX_VOLUME_CHANGED:
                    double volumeFx = ((RightMenu)source).getFx();
                    engine.setVolFx(volumeFx/100);
                    params.writeFxVol(volumeFx);
                    break;
                case REQUIRE_GAME:
                    musicPlayer.startGame();
                    engine.setPseudo(((RightMenu)source).getPseudo());
                    scene.setRoot(window.getGamePanel(scene.getShrinkX(), scene.getShrinkY()));
                    animationTimer.start();
                    break;
                case REQUIRE_MENU:
                    musicPlayer.startMenu();
                    animationTimer.stop();
                    engine.reinitScores();
                    engine.reinit();
                    scene.setRoot(window.getMainPanel(scene.getShrinkX(), scene.getShrinkY()));
                    break;
                case REQUIRE_STATS:
                    scene.setRoot(window.getStatsPanel(scene.getShrinkX(), scene.getShrinkY(),((RightMenu)source).getPseudo() ) );
                    break;
            }
    }
}

