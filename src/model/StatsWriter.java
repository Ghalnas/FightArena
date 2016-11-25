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
    private final String path = "var/stat/stats.json";

    public StatsWriter()
    {
        gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            File f = new File(path);
            if(!f.exists()) {
                if(f.getParentFile().mkdirs() && f.createNewFile()) {
                    HashMap<String, Stat> tmp = new HashMap<>();
                    tmp.put("General",new Stat());
                    map = tmp;
                    persist();
                } else {
                    throw new IOException("Error creating new file: " + f.getAbsolutePath());
                }
            } else {
                BufferedReader br = new BufferedReader(new FileReader(f));
                map = gson.fromJson(br, new TypeToken<HashMap<String,Stat>>(){}.getType());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addVictory(String pseudo)
    {
        Stat stat = map.get("General");
        stat.setVictories(stat.getVictories()+1);
        if (pseudo != null) {
            map.putIfAbsent(pseudo, new Stat());
            Stat specific = map.get(pseudo);
            specific.setVictories(specific.getVictories() + 1);
        }
    }

    public void addLose(String pseudo)
    {
        Stat stat = map.get("General");
        stat.setLoses(stat.getLoses()+1);
        if (pseudo != null) {
            map.putIfAbsent(pseudo, new Stat());
            Stat specific = map.get(pseudo);
            specific.setLoses(specific.getLoses() + 1);
        }
    }

    public void addHit(String pseudo, int value)
    {
        Stat stat = map.get("General");
        stat.setHit(stat.getHit()+value);
        if (pseudo != null) {
            map.putIfAbsent(pseudo, new Stat());
            Stat specific = map.get(pseudo);
            specific.setHit(specific.getHit() + value);
        }
    }

    public void addMiss(String pseudo, int value)
    {
        Stat stat = map.get("General");
        stat.setMisses(stat.getMisses()+value);
        if (pseudo != null) {
            map.putIfAbsent(pseudo, new Stat());
            Stat specific = map.get(pseudo);
            specific.setMisses(specific.getMisses() + value);
        }
    }

    public void addHitTaken(String pseudo, int value)
    {Stat stat = map.get("General");
        stat.setHitTaken(stat.getHitTaken()+value);
        if (pseudo != null) {
            map.putIfAbsent(pseudo, new Stat());
            Stat specific = map.get(pseudo);
            specific.setHitTaken(specific.getHitTaken() + value);
        }
    }

    public void addSpinTime(String pseudo, int value)
    {
        Stat stat = map.get("General");
        stat.setSpinTime(stat.getSpinTime()+value);
        if (pseudo != null) {
            map.putIfAbsent(pseudo, new Stat());
            Stat specific = map.get(pseudo);
            specific.setSpinTime(specific.getSpinTime() + value);
        }
    }

    public void addSpinVictory(String pseudo)
    {
        Stat stat = map.get("General");
        stat.setSpinVictory(stat.getSpinVictory()+1);
        if (pseudo != null) {
            map.putIfAbsent(pseudo, new Stat());
            Stat specific = map.get(pseudo);
            specific.setSpinVictory(specific.getSpinVictory() + 1);
        }
    }

    public void addSpinLose(String pseudo)
    {
        Stat stat = map.get("General");
        stat.setSpinLose(stat.getSpinLose()+1);
        if (pseudo != null) {
            map.putIfAbsent(pseudo, new Stat());
            Stat specific = map.get(pseudo);
            specific.setSpinLose(specific.getSpinLose() + 1);
        }
    }

    public void addLightningWin(String pseudo)
    {
        Stat stat = map.get("General");
        stat.setLightningWin(stat.getLightningWin()+1);
        if (pseudo != null) {
            map.putIfAbsent(pseudo, new Stat());
            Stat specific = map.get(pseudo);
            specific.setLightningWin(specific.getLightningWin() + 1);
        }
    }

    public void addLightningUse(String pseudo, int value)
    {
        Stat stat = map.get("General");
        stat.setLightningUsed(stat.getLightningUsed()+value);
        if (pseudo != null) {
            map.putIfAbsent(pseudo, new Stat());
            Stat specific = map.get(pseudo);
            specific.setLightningUsed(specific.getLightningUsed() + value);
        }
    }

    public void addLightningLose(String pseudo)
    {
        Stat stat = map.get("General");
        stat.setLightningLose(stat.getLightningLose()+1);
        if (pseudo != null) {
            map.putIfAbsent(pseudo, new Stat());
            Stat specific = map.get(pseudo);
            specific.setLightningLose(specific.getLightningLose() + 1);
        }
    }

    public void addGoldVictory(String pseudo)
    {
        Stat stat = map.get("General");
        stat.setGoldVictory(stat.getGoldVictory()+1);
        if (pseudo != null) {
            map.putIfAbsent(pseudo, new Stat());
            Stat specific = map.get(pseudo);
            specific.setGoldVictory(specific.getGoldVictory() + 1);
        }
    }

    public void addGoldLose(String pseudo)
    {
        Stat stat = map.get("General");
        stat.setGoldLose(stat.getLoses()+1);
        if (pseudo != null) {
            stat = map.putIfAbsent(pseudo, new Stat());
            stat.setGoldLose(stat.getGoldLose() + 1);
        }
    }

    public void addGoldTime(String pseudo, int value)
    {
        Stat stat = map.get("General");
        stat.setGoldTime(stat.getGoldTime()+value);
        if (pseudo != null) {
            map.putIfAbsent(pseudo, new Stat());
            Stat specific = map.get(pseudo);
            specific.setGoldTime(specific.getGoldTime() + value);
        }
    }

    public void addHealReceived(String pseudo, double value)
    {
        Stat stat = map.get("General");
        stat.setHealReceived(stat.getHealReceived()+value);
        if (pseudo != null) {
            map.putIfAbsent(pseudo, new Stat());
            Stat specific = map.get(pseudo);
            specific.setHealReceived(specific.getHealReceived() + value);
        }
    }

    public void persist()
    {
        try (Writer writer = new FileWriter(path)) {
            gson.toJson(map, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
