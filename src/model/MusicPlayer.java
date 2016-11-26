package model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicPlayer
{
    private MediaPlayer gameMusic;
    private MediaPlayer menuMusic;

    public MusicPlayer(double volMusic)
    {
        gameMusic = new MediaPlayer(new Media(new File("assets/music/game-music.mp3").toURI().toString()));
        gameMusic.setCycleCount(MediaPlayer.INDEFINITE);
        menuMusic = new MediaPlayer(new Media(new File("assets/music/menu-music.mp3").toURI().toString()));
        menuMusic.setCycleCount(MediaPlayer.INDEFINITE);
        setVolMusic(volMusic);
    }

    public void startGame()
    {
        menuMusic.stop();
        gameMusic.play();
    }

    public void startMenu()
    {
        gameMusic.stop();
        menuMusic.play();
    }

    public void setVolMusic(double volMusic) {
        gameMusic.setVolume(volMusic);
        menuMusic.setVolume(volMusic);
    }
}
