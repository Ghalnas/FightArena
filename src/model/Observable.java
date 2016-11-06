package model;

/**
 * Interface Observable
 */
public interface Observable
{
    public void addObserver(Observer observer);
    public void removeObserver();
    public void notifyObserver(String str);
}
