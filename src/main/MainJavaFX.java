package main;

import controller.Engine;
import controller.GameController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.*;
import model.Character;
import view.*;

public class MainJavaFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ParamWriter params = new ParamWriter();

        Arena arena = new Arena(params.arenaWidth, params.arenaHeight);
        Character player = new Player(new Position(params.playerX, params.playerY), params.playerSpeed, params.playerDamage, params.playerHealth);
        Character bot = new Bot(new Position(params.botX, params.botY), params.botSpeed, params.botDamage, params.botHealth);
        Item item = new Item();

        Logger logger = Logger.getInstance();
        LogPrinter logPrinter = new LogPrinter((25f/100f)*params.arenaWidth,params.arenaWidth,params.arenaHeight/2);
        logger.addObserver(logPrinter);

        ScorePrinter scorePrinter = new ScorePrinter((25f/100f)*params.arenaWidth,params.arenaWidth,0);

        CharacterPrinter playerObs = new CharacterPrinter(player, params.spriteWidth, params.spriteHeight, params.slashFrames);
        CharacterPrinter botObs = new CharacterPrinter(bot, params.spriteWidth, params.spriteHeight, params.slashFrames);
        ItemPrinter itemPrinter = new ItemPrinter(item);
        item.addObserver(itemPrinter);
        StatsWriter statsWriter = new StatsWriter();

        //instantiate game engine and set Observers
        Engine engine = new Engine(player,(Bot)bot, item, statsWriter, params.botDifficulty, params.slashFrames, params.spinFrames, params.goldFrames, params.arenaWidth, params.arenaHeight, params.fxVolume/100);
        player.addObserver(engine);
        bot.addObserver(engine);
        player.addObserver(playerObs);
        bot.addObserver(botObs);
        engine.addObserver(scorePrinter);


        // set window size
        stage.setTitle("Fight Arena");
        stage.setWidth(params.windowWidth);
        stage.setHeight(params.windowHeight);
        stage.setFullScreen(params.fullscreen);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        MainMenuPrinter mainMenuPrinter = new MainMenuPrinter();
        RightMenu rightMenu = new RightMenu(params.arenaWidth,params.arenaHeight, params.botDifficulty, params.selectedRes, params.fullscreen, params.musicVolume, params.fxVolume);

        StatsPrinter statsPrinter = new StatsPrinter(statsWriter.getMap(), params.arenaWidth,0,0);

        WindowViewer window = new WindowViewer(params.arenaWidth, params.arenaHeight, params.spriteScale, mainMenuPrinter, rightMenu, statsPrinter);
        window.addGamePrinter(playerObs);
        window.addGamePrinter(botObs);
        window.addGamePrinter(itemPrinter);
        window.addScorePrinter(scorePrinter);
        window.addLogPrinter(logPrinter);

        Group root = new Group();
        SceneFX scene = new SceneFX(root, params.arenaWidth, params.arenaHeight);
        scene.setFill(Color.rgb(0,0,0,0.7));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        GameController timer = new GameController(stage, scene,engine,window, params, params.musicVolume/100);
        scene.bindTimer(timer);
        rightMenu.bindTimer(timer);
    }
}
