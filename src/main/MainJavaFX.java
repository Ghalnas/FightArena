package main;

import controller.Engine;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.*;
import model.Character;
import view.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainJavaFX extends Application {
    public static void main(String[] args) {
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
            double arenaWidth = Integer.parseInt(prop.getProperty("arena_width"));
            double arenaHeight = Integer.parseInt(prop.getProperty("arena_height"));
            double playerDamage = Double.parseDouble(prop.getProperty("player_damage"));
            double botDamage = Double.parseDouble(prop.getProperty("bot_damage"));
            double playerHealth = Double.parseDouble(prop.getProperty("player_health"));
            double botHealth = Double.parseDouble(prop.getProperty("bot_health"));
            double playerX = (arenaWidth/800) * Double.parseDouble(prop.getProperty("player_start_x"));
            double playerY = (arenaHeight/600) * Double.parseDouble(prop.getProperty("player_start_y"));
            double botX = (arenaWidth/800) * Double.parseDouble(prop.getProperty("bot_start_x"));
            double botY = (arenaHeight/600) * Double.parseDouble(prop.getProperty("bot_start_y"));
            int spriteWidth = Integer.parseInt(prop.getProperty("sprite_width"));
            int spriteHeight = Integer.parseInt(prop.getProperty("sprite_height"));
            double spriteScale = Double.parseDouble(prop.getProperty("sprite_scale"));
            double playerSpeed = (arenaWidth/800) * Double.parseDouble(prop.getProperty("player_speed"));
            double botSpeed = (arenaWidth/800) * Double.parseDouble(prop.getProperty("bot_speed"));
            int slashFrames = Integer.parseInt(prop.getProperty("slash_frames"));
            int spinFrames = Integer.parseInt(prop.getProperty("spin_frames"));
            int goldFrames = Integer.parseInt(prop.getProperty("gold_frames"));

            Arena arena = new Arena(arenaWidth, arenaHeight);
            Character player = new Player(new Position(playerX, playerY), playerSpeed, playerDamage, playerHealth);
            Character bot = new Bot(new Position(botX, botY), botSpeed, botDamage, botHealth);
            Item item = new Item();

            Logger logger = Logger.getInstance();
            LogPrinter logPrinter = new LogPrinter(arenaWidth,(50f/100f)*arenaHeight,(25f/100f)*arenaWidth,(50f/100f)*arenaHeight);
            logger.addObserver(logPrinter);

            ScorePrinter scorePrinter = new ScorePrinter(arenaWidth,0, (25f/100f) * arenaWidth, (50f/100f) * arenaHeight);

            CharacterPrinter playerObs = new CharacterPrinter(player, spriteWidth, spriteHeight, slashFrames);
            CharacterPrinter botObs = new CharacterPrinter(bot, spriteWidth, spriteHeight, slashFrames);
            ItemPrinter itemPrinter = new ItemPrinter(item);
            MainMenuPrinter mainMenuPrinter = new MainMenuPrinter(arenaWidth, arenaHeight);

//            Media media = new Media(new File("assets/music/fight_arena_theme_song.mp3").toURI().toString());
//            MediaPlayer mediaPlayer = new MediaPlayer(media);
//            mediaPlayer.setAutoPlay(true);
//            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//            mediaPlayer.play();

            //instantiate game engine and set Observers
            Engine engine = new Engine(player,(Bot)bot, item, slashFrames, spinFrames, goldFrames, arenaWidth, arenaHeight);
            player.addObserver(engine);
            bot.addObserver(engine);
            player.addObserver(playerObs);
            bot.addObserver(botObs);
            engine.addObserver(scorePrinter);
            //engine.addObserver(mainMenuPrinter);


            // set window size
            stage.setWidth(arenaWidth+(25f/100f)*arenaWidth);
            stage.setHeight(arenaHeight);
            stage.setTitle("Fight Arena");

            WindowViewer window = new WindowViewer(arenaWidth, arenaHeight, spriteScale);
            window.addMainMenuPrinter(mainMenuPrinter);
            window.addGamePrinter(playerObs);
            window.addGamePrinter(botObs);
            window.addGamePrinter(itemPrinter);
            window.addScorePrinter(scorePrinter);
            window.addLogPrinter(logPrinter);


            Group root = new Group();
            SceneFX scene = new SceneFX(root, arenaWidth, arenaHeight);

            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();

            engine.init();

            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    if(scene.getBackMenu()) {
                        mainMenuPrinter.requireMenu();
                        engine.reinitScores();
                        engine.reinit();
                    }
                    if(mainMenuPrinter.getMainMenuPanelRequired()){
                        scene.setRoot(window.getMainPanel(scene.getShrinkX(), scene.getShrinkY()));
                    } else if (mainMenuPrinter.getGamePanelRequired()) {
                        engine.setPseudo(mainMenuPrinter.getPseudo());
                        scene.setRoot(window.getGamePanel(scene.getShrinkX(), scene.getShrinkY()));
                        engine.run(scene.getCommand());
                    }
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
