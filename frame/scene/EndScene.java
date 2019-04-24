package frame.scene;

import frame.MainPanel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EndScene extends Scene {

    public EndScene(MainPanel.GameStatusChangeListener gsChangeListener) {
        super(gsChangeListener);
    }

    @Override
    public KeyListener genKeyListener() {
        return new KeyAdapter() {

        };
    }

    @Override
    public void logicEvent() {

    }

    @Override
    public void paint(Graphics g) {

    }
}
