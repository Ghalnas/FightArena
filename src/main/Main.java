package main;

import controller.Engine;
import model.*;
import model.Character;
import view.CharacterObserver;
import view.MyPanel;
import view.Window;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main
{
    public static void main(String[] args)
    {
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

            //instanciate models
            Arena arena = new Arena(arenaWidth,arenaHeight);
            Character player = new Player(new Position(playerX,playerY));
            Character bot = new Bot(new Position(botX,botY));
            Logger logger = Logger.getInstance();

            //instantiate game engine
            Engine engine = new Engine(player,(Bot)bot);

            //instanciate gui setting ui observers
            MyPanel panel = new MyPanel(arena.getWidth(),arena.getHeight());
            panel.addObserverSwing(new CharacterObserver(player));
            panel.addObserverSwing(new CharacterObserver(bot));

            // set logic observers
            player.addObserver((Bot)bot);
            player.addObserver(panel);
            player.addObserver(logger);
            bot.addObserver(panel);
            bot.addObserver(logger);

            //instanciate frame
            Window win = new Window(engine,panel);
            engine.run();
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
