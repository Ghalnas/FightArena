package model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class StatsWriter
{
    private Map<String, Stat> map;
    private Gson gson;

    public StatsWriter()
    {
        gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            File f = new File("var/log/stats.json");
            if(!f.exists() && !f.createNewFile()) {
                throw new IOException("Error creating new file: " + f.getAbsolutePath());
            }
            BufferedReader br = new BufferedReader(new FileReader(f));
            map = gson.fromJson(br, new TypeToken<HashMap<String,Stat>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addVictory(String pseudo)
    {
        Stat stat = map.putIfAbsent(pseudo,new Stat());
        stat.setVictories(stat.getVictories()+1);
    }

    public void addLose(String pseudo)
    {
        Stat stat = map.putIfAbsent(pseudo,new Stat());
        stat.setLoses(stat.getLoses()+1);
    }

    public void addHit(String pseudo)
    {
        Stat stat = map.putIfAbsent(pseudo,new Stat());
        stat.setHit(stat.getHit()+1);
    }

    public void addMiss(String pseudo)
    {
        Stat stat = map.putIfAbsent(pseudo,new Stat());
        stat.setMisses(stat.getMisses()+1);
    }

    public void addHitTaken(String pseudo)
    {
        Stat stat = map.putIfAbsent(pseudo,new Stat());
        stat.setHitTaken(stat.getHitTaken()+1);
    }

    public void addSpinTime(String pseudo, int time)
    {
        Stat stat = map.putIfAbsent(pseudo,new Stat());
        stat.setSpinTime(stat.getSpinTime()+time);
    }

    public void addSpinVictory(String pseudo)
    {
        Stat stat = map.putIfAbsent(pseudo,new Stat());
        stat.setSpinVictory(stat.getSpinVictory()+1);
    }

    public void addSpinLose(String pseudo)
    {
        Stat stat = map.putIfAbsent(pseudo,new Stat());
        stat.setSpinLose(stat.getSpinLose()+1);
    }

    public void addLightningWin(String pseudo)
    {
        Stat stat = map.putIfAbsent(pseudo,new Stat());
        stat.setLightningWin(stat.getLightningWin()+1);
    }

    public void addLightningUse(String pseudo)
    {
        Stat stat = map.putIfAbsent(pseudo,new Stat());
        stat.setLightningUsed(stat.getLightningUsed()+1);
    }

    public void addLightningLose(String pseudo)
    {
        Stat stat = map.putIfAbsent(pseudo,new Stat());
        stat.setLightningLose(stat.getLightningLose()+1);
    }

    public void addGoldVictory(String pseudo)
    {
        Stat stat = map.putIfAbsent(pseudo,new Stat());
        stat.setGoldVictory(stat.getGoldVictory()+1);
    }

    public void addGoldLose(String pseudo)
    {
        Stat stat = map.putIfAbsent(pseudo,new Stat());
        stat.setGoldLose(stat.getGoldLose()+1);
    }

    public void addGoldTime(String pseudo, int value)
    {
        Stat stat = map.putIfAbsent(pseudo,new Stat());
        stat.setGoldTime(stat.getGoldTime()+value);
    }

    public void addHealReceived(String pseudo, int value)
    {
        Stat stat = map.putIfAbsent(pseudo,new Stat());
        stat.setHealReceived(stat.getHealReceived()+value);
    }

    public void persist()
    {
        try (Writer writer = new FileWriter("var/log/stats.json")) {
            gson.toJson(map, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
