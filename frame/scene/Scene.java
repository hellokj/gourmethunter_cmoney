package frame.scene;

import frame.MainPanel;

import java.awt.*;
import java.awt.event.KeyListener;

public abstract class Scene {
    MainPanel.GameStatusChangeListener gsChangeListener;

    public Scene(MainPanel.GameStatusChangeListener gsChangeListener){
        this.gsChangeListener = gsChangeListener;
    }

    abstract public KeyListener genKeyListener();
    abstract public void paint(Graphics g);
    abstract public void logicEvent();
}
