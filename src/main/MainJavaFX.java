package main;

import controller.Engine;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;
import model.Character;
import view.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainJavaFX extends Application
{
    public static void main(String[] args) {
        System.out.println( "Main method inside Thread : " +  Thread.currentThread().getName());
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            //read properties
            input = new FileInputStream("app/config/parameters.properties");
            prop.load(input);
            int arenaWidth = Integer.parseInt(prop.getProperty("arena_width"));
            int arenaHeight = Integer.parseInt(prop.getProperty("arena_height"));
            int playerX = Integer.parseInt(prop.getProperty("player_start_x"));
            int playerY = Integer.parseInt(prop.getProperty("player_start_y"));
            int botX = Integer.parseInt(prop.getProperty("bot_start_x"));
            int botY = Integer.parseInt(prop.getProperty("bot_start_y"));
            int spriteWidth = Integer.parseInt(prop.getProperty("sprite_width"));
            int spriteHeight = Integer.parseInt(prop.getProperty("sprite_height"));
            double spriteScale = Double.parseDouble(prop.getProperty("sprite_scale"));
            double characterSpeed = Double.parseDouble(prop.getProperty("character_speed"));

            Arena arena = new Arena(arenaWidth,arenaHeight);
            Character player = new Player(new Position(playerX,playerY),characterSpeed);
            Character bot = new Bot(new Position(botX,botY),characterSpeed);
            Logger logger = Logger.getInstance();

            CharacterPrinter playerObs = new CharacterPrinter(player,spriteWidth,spriteHeight);
            CharacterPrinter botObs = new CharacterPrinter(bot,spriteWidth,spriteHeight);

            //instantiate game engine and set Observers
            Engine engine = new Engine(player,(Bot)bot);
            player.addObserver(engine);
            bot.addObserver(engine);
//            player.addObserver(logger);
            player.addObserver(playerObs);
            bot.addObserver(botObs);

            // set window size
            stage.setWidth(arenaWidth);
            stage.setHeight(arenaHeight);
            stage.setTitle("Fight Arena");

            // create panel and set its observers
            JavaFXViewer viewer = new JavaFXViewer(spriteScale);
            viewer.addObserverJavaFX(playerObs);
            viewer.addObserverJavaFX(botObs);

            Group root = new Group();
            SceneFX scene = new SceneFX(root);
            Rectangle background = new Rectangle(arenaWidth,arenaHeight);
            background.setFill(Color.BLACK);

            root.getChildren().add(background);
            root.getChildren().add(viewer.getPanel());

            stage.setScene(scene);
            stage.show();

            engine.init();

            AnimationTimer timer = new AnimationTimer() {
                @Override public void handle(long l) {
                    engine.run(scene.getCommand());
                    scene.setRoot(viewer.getPanel());
                }
            };

            timer.start();

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
