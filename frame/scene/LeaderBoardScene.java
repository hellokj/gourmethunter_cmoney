package frame.scene;

import frame.MainPanel;

import java.awt.*;
import java.awt.event.KeyListener;

public class LeaderBoardScene extends Scene {

    public LeaderBoardScene(MainPanel.GameStatusChangeListener gsChangeListener) {
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
