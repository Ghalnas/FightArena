package model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class FXPlayer
{
    private MediaPlayer swordSound;
    private MediaPlayer spin;
    private MediaPlayer lightning;
    private MediaPlayer gold;
    private MediaPlayer heal;
    private MediaPlayer death;
    private Media swordMedia;
    private Media lightningMedia;
    private Media healMedia;
    private double volFx;

    public FXPlayer(double volFx)
    {
        spin = new MediaPlayer(new Media(new File("assets/music/spin.mp3").toURI().toString()));
        lightning = new MediaPlayer(new Media(new File("assets/music/lightning.mp3").toURI().toString()));
        gold = new MediaPlayer(new Media(new File("assets/music/gold.mp3").toURI().toString()));
        heal =new MediaPlayer(new Media(new File("assets/music/heal.mp3").toURI().toString()));
        death =new MediaPlayer(new Media(new File("assets/music/death.mp3").toURI().toString()));
        lightningMedia = new Media(new File("assets/music/lightning.mp3").toURI().toString());
        lightning = new MediaPlayer(lightningMedia);
        healMedia = new Media(new File("assets/music/heal.mp3").toURI().toString());
        heal = new MediaPlayer(healMedia);
        swordMedia = new Media(new File("assets/music/slash.mp3").toURI().toString());
        swordSound = new MediaPlayer(swordMedia);
        setVolFx(volFx);
    }

    public void playSpin()
    {
        spin.play();
    }

    public void playLightning()
    {
        lightning = new MediaPlayer(lightningMedia);
        lightning.setVolume(volFx);
        lightning.play();
    }

    public void playGold()
    {
        gold.play();
    }


    public void playHeal()
    {
        heal = new MediaPlayer(healMedia);
        heal.setVolume(volFx);
        heal.play();
    }

    public void swordSound()
    {
        swordSound = new MediaPlayer(swordMedia);
        swordSound.setVolume(volFx);
        swordSound.play();
    }

    public void death()
    {
        death.play();
    }

    public void reinit()
    {
        spin.stop();
        gold.stop();
        death.stop();
    }

    public void setVolFx(double volFx) {
        this.volFx = volFx;
        spin.setVolume(volFx);
        gold.setVolume(volFx);
        death.setVolume(volFx);
    }
}
