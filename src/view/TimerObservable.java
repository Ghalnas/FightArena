package view;

import model.TimerEvent;

public interface TimerObservable
{
    public void bindTimer(TimerObserver o);
    public void notifyTimer(TimerEvent e);
}
