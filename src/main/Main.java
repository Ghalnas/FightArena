package main;

import controller.Engine;
import model.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main
{
    private static Logger LOGGER = Logger.getInstance();

    public static void main(String[] args)
    {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("app/config/parameters.properties");
            prop.load(input);
            int arenaWidth = Integer.parseInt(prop.getProperty("arena_width"));
            int arenaHeight = Integer.parseInt(prop.getProperty("arena_height"));
            int playerX = Integer.parseInt(prop.getProperty("player_start_x"));
            int playerY = Integer.parseInt(prop.getProperty("player_start_y"));
            int botX = Integer.parseInt(prop.getProperty("bot_start_x"));
            int botY = Integer.parseInt(prop.getProperty("bot_start_y"));

            Engine engine = new Engine(arenaWidth, arenaHeight, playerX, playerY, botX, botY);
            LOGGER.addObserver(engine);
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
