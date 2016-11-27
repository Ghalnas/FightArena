package model;

public class Stat {
    private int victories, loses, hit, misses, hitTaken, spinVictory, spinLose, lightningWin, lightningUsed,
            lightningLose, goldVictory, goldLose;
    private double healReceived, goldTime, spinTime;

    public Stat(){}

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getMisses() {
        return misses;
    }

    public void setMisses(int misses) {
        this.misses = misses;
    }

    public int getHitTaken() {
        return hitTaken;
    }

    public void setHitTaken(int hitTaken) {
        this.hitTaken = hitTaken;
    }

    public double getSpinTime() {
        return spinTime;
    }

    public void setSpinTime(double spinTime) {
        this.spinTime = spinTime;
    }

    public int getSpinVictory() {
        return spinVictory;
    }

    public void setSpinVictory(int spinVictory) {
        this.spinVictory = spinVictory;
    }

    public int getSpinLose() {
        return spinLose;
    }

    public void setSpinLose(int spinLose) {
        this.spinLose = spinLose;
    }

    public int getLightningWin() {
        return lightningWin;
    }

    public void setLightningWin(int lightningWin) {
        this.lightningWin = lightningWin;
    }

    public int getLightningUsed() {
        return lightningUsed;
    }

    public void setLightningUsed(int lightningUsed) {
        this.lightningUsed = lightningUsed;
    }

    public int getLightningLose() {
        return lightningLose;
    }

    public void setLightningLose(int lightningLose) {
        this.lightningLose = lightningLose;
    }

    public int getGoldVictory() {
        return goldVictory;
    }

    public void setGoldVictory(int goldVictory) {
        this.goldVictory = goldVictory;
    }

    public int getGoldLose() {
        return goldLose;
    }

    public void setGoldLose(int goldLose) {
        this.goldLose = goldLose;
    }

    public double getGoldTime() {
        return goldTime;
    }

    public void setGoldTime(double goldTime) {
        this.goldTime = goldTime;
    }

    public double getHealReceived() {
        return healReceived;
    }

    public void setHealReceived(double healReceived) {
        this.healReceived = healReceived;
    }
}
