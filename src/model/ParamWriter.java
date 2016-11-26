package model;

import java.io.*;
import java.util.Properties;

public class ParamWriter
{
    private Properties prop;
    private File file;

    public double arenaWidth;
    public double arenaHeight;
    public double playerDamage;
    public double botDamage;
    public double playerHealth;
    public double botHealth;
    public double playerX;
    public double playerY;
    public double botX;
    public double botY;
    public int spriteWidth;
    public int spriteHeight;
    public double spriteScale;
    public double playerSpeed;
    public double botSpeed;
    public int slashFrames;
    public int spinFrames;
    public int goldFrames;

    public int botDifficulty;
    public int selectedRes;
    public boolean fullscreen;
    public double musicVolume;
    public double fxVolume;
    public double windowWidth;
    public double windowHeight;

    public ParamWriter()
    {
        prop = new Properties();
        file = new File("app/config/parameters.properties");
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            prop.load(input);
            arenaWidth = Integer.parseInt(prop.getProperty("arena_width"));
            arenaHeight = Integer.parseInt(prop.getProperty("arena_height"));
            playerDamage = Double.parseDouble(prop.getProperty("player_damage"));
            botDamage = Double.parseDouble(prop.getProperty("bot_damage"));
            playerHealth = Double.parseDouble(prop.getProperty("player_health"));
            botHealth = Double.parseDouble(prop.getProperty("bot_health"));
            playerX = (arenaWidth/800) * Double.parseDouble(prop.getProperty("player_start_x"));
            playerY = (arenaHeight/600) * Double.parseDouble(prop.getProperty("player_start_y"));
            botX = (arenaWidth/800) * Double.parseDouble(prop.getProperty("bot_start_x"));
            botY = (arenaHeight/600) * Double.parseDouble(prop.getProperty("bot_start_y"));
            spriteWidth = Integer.parseInt(prop.getProperty("sprite_width"));
            spriteHeight = Integer.parseInt(prop.getProperty("sprite_height"));
            spriteScale = Double.parseDouble(prop.getProperty("sprite_scale"));
            playerSpeed = (arenaWidth/800) * Double.parseDouble(prop.getProperty("player_speed"));
            botSpeed = (arenaWidth/800) * Double.parseDouble(prop.getProperty("bot_speed"));
            slashFrames = Integer.parseInt(prop.getProperty("slash_frames"));
            spinFrames = Integer.parseInt(prop.getProperty("spin_frames"));
            goldFrames = Integer.parseInt(prop.getProperty("gold_frames"));

            botDifficulty = Integer.parseInt(prop.getProperty("bot_difficulty_index"));
            selectedRes = Integer.parseInt(prop.getProperty("window_size_index"));
            fullscreen = Boolean.parseBoolean((prop.getProperty("fullscreen")));
            musicVolume = Double.parseDouble(prop.getProperty("music_volume"));
            fxVolume = Double.parseDouble(prop.getProperty("fx_volume"));
            windowWidth = Double.parseDouble(prop.getProperty("window_width"));
            windowHeight = Double.parseDouble(prop.getProperty("window_height"));
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

    public void writeWidth(double width)
    {
        windowWidth = width;
        write("window_width", Double.toString(width));
    }

    public void writeHeight(double height)
    {
        windowHeight = height;
        write("window_height", Double.toString(height));
    }

    public void writeBodDifficulty(int index)
    {
        botDifficulty = index;
        write("bot_difficulty_index", Integer.toString(index));
    }

    public void writeSelectedRes(int index)
    {
        selectedRes = index;
        write("window_size_index", Integer.toString(index));
    }

    public void writeFullscreen(boolean bool)
    {
        fullscreen = bool;
        write("fullscreen", Boolean.toString(bool));
    }

    public void writeMusicVol(double value)
    {
        musicVolume = value;
        write("music_volume", String.format("%.0f", value));
    }

    public void writeFxVol(double value)
    {
        fxVolume = value;
        write("fx_volume", String.format("%.0f", value));
    }

    private void write(String key, String value)
    {
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            prop.setProperty(key,value);
            prop.store(out,null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
