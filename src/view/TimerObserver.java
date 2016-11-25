package view;

import model.TimerEvent;

public interface TimerObserver
{
    public void update(TimerEvent e);
    public void update(TimerEvent e, String pseudo);
}
