package model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;

public class StatsReader
{
    private Gson gson;
    private final String path = "var/stat/stats.json";
    private Map<String, Stat> map;

    public StatsReader()
    {
        try{
            gson = new Gson();
            File f = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(f));
            map = gson.fromJson(br, new TypeToken<HashMap<String,Stat>>(){}.getType());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean existPlayer(String player){
        if(map.get(player) != null){
            return true;
        }else{
            return false;
        }
    }

    public int getVictory(String element)
    {
        Stat stat = map.get(element);
        return stat.getVictories();
    }

    public int getLose(String element)
    {
        Stat stat = map.get(element);
        return stat.getLoses();
    }

    public int getHit(String element)
    {
        Stat stat = map.get(element);
        return stat.getHit();
    }

    public int getMiss(String element)
    {
        Stat stat = map.get(element);
        return stat.getMisses();
    }

    public int getHitTaken(String element)
    {
        Stat stat = map.get(element);
        return stat.getHitTaken();
    }

    public int getSpinTime(String element)
    {
        Stat stat = map.get(element);
        return stat.getSpinTime();
    }

    public int getSpinVictory(String element)
    {
        Stat stat = map.get(element);
        return stat.getSpinVictory();
    }

    public int getSpinLose(String element)
    {
        Stat stat = map.get(element);
        return stat.getSpinLose();
    }

    public int getLightningWin(String element)
    {
        Stat stat = map.get(element);
        return stat.getLightningWin();
    }

    public int getLightningUse(String element)
    {
        Stat stat = map.get(element);
        return stat.getLightningUsed();
    }

    public int getLightningLose(String element)
    {
        Stat stat = map.get(element);
        return stat.getLightningLose();
    }

    public int getGoldVictory(String element)
    {
        Stat stat = map.get(element);
        return stat.getGoldVictory();
    }

    public int getGoldLose(String element)
    {
        Stat stat = map.get(element);
        return stat.getGoldLose();
    }

    public int getGoldTime(String element)
    {
        Stat stat = map.get(element);
        return stat.getGoldTime();
    }

    public double getHealReceived(String element)
    {
        Stat stat = map.get(element);
        return stat.getHealReceived();
    }

}
