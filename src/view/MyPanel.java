package view;

import model.Arena;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MyPanel extends JPanel implements Observer
{
    private ArrayList<ObserverSwing> listObservers;

    public MyPanel(int width, int height)
    {
        listObservers = new ArrayList<>();
        this.setPreferredSize(new Dimension(width,height));
    }

    public void addNotify()
    {
        super.addNotify();
        requestFocus();
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
        for (ObserverSwing o : listObservers) {
            o.print(g);
        }
    }



    public void addObserverSwing(ObserverSwing o)
    {
        listObservers.add(o);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        repaint();
    }

}