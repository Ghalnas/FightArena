package listener;

import model.Character;

import java.util.EventListener;

public interface CharacterListener extends EventListener
{
    public void hasMoved(Character character);
}
