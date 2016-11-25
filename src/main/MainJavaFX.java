package main;

import controller.Engine;
import controller.GameController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.*;
import model.Character;
import view.*;

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


            //instantiate game engine and set Observers
            Engine engine = new Engine(player,(Bot)bot, item, slashFrames, spinFrames, goldFrames, arenaWidth, arenaHeight);
            player.addObserver(engine);
            bot.addObserver(engine);
            player.addObserver(playerObs);
            bot.addObserver(botObs);
            engine.addObserver(scorePrinter);


            // set window size
            stage.setWidth(arenaWidth+(25f/100f)*arenaWidth);
            stage.setHeight(arenaHeight);
            stage.setTitle("Fight Arena");

            MainMenuPrinter mainMenuPrinter = new MainMenuPrinter();
            RightMenu rightMenu = new RightMenu(arenaWidth,arenaHeight);

            WindowViewer window = new WindowViewer(arenaWidth, arenaHeight, spriteScale, mainMenuPrinter, rightMenu);
            window.addGamePrinter(playerObs);
            window.addGamePrinter(botObs);
            window.addGamePrinter(itemPrinter);
            window.addScorePrinter(scorePrinter);
            window.addLogPrinter(logPrinter);



            Group root = new Group();
            SceneFX scene = new SceneFX(root, arenaWidth, arenaHeight);
            scene.setFill(Color.rgb(0,0,0,0.7));
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();

            GameController timer = new GameController(scene,engine,window);
            scene.bindTimer(timer);
            rightMenu.bindTimer(timer);

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
