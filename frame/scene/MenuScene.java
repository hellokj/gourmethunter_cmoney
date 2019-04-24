package frame.scene;

import frame.MainPanel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuScene extends Scene{

    public MenuScene(MainPanel.GameStatusChangeListener gsChangeListener){
        super(gsChangeListener);
    }

    @Override
    public KeyListener genKeyListener() {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e){
                switch (e.getKeyCode()){
                    case KeyEvent.VK_0:
                        gsChangeListener.changeScene(MainPanel.MENU_SCENE);
                        break;
                    case KeyEvent.VK_1:
                        gsChangeListener.changeScene(MainPanel.GAME_SCENE);
                        break;
                    case KeyEvent.VK_2:
                        gsChangeListener.changeScene(MainPanel.LEADER_BOARD_SCENE);
                        break;
                    case KeyEvent.VK_3:
                        gsChangeListener.changeScene(MainPanel.END_SCENE);
                        break;
                }
            }
        };
    }

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public void logicEvent() {

    }
}
