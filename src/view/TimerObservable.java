package view;

public interface TimerObservable
{
    public void bindTimer(TimerObserver o);
    public void notifyTimer(Object arg);
}
