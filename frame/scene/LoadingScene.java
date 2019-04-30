package frame.scene;

import character.GameObject;
import frame.MainPanel;

import java.awt.*;
import java.awt.event.KeyListener;

public class LoadingScene extends Scene {

    public LoadingScene(MainPanel.GameStatusChangeListener gsChangeListener) {
        super(gsChangeListener);

    }

    @Override
    public KeyListener genKeyListener() {
        return null;
    }

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public void logicEvent() {

    }
}
