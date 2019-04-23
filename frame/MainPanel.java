package frame;

import frame.scene.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class MainPanel extends javax.swing.JPanel {
    public static final int MENU_SCENE = 0;
    public static final int GAME_SCENE = 1;
    public static final int END_SCENE = 2;

    public interface GameStatusChangeListener{
        void changeScene(int sceneId);
    }

    // 現在場景
    private KeyListener kl;
    private Scene currentScene;
    private GameStatusChangeListener gsChangeListener;

    public MainPanel(){
        gsChangeListener = new GameStatusChangeListener() {
            @Override
            public void changeScene(int sceneId) {
                changeCurrentScene(genSceneById(sceneId));
            }
        };

        changeCurrentScene(genSceneById(GAME_SCENE));

        // delay 25ms
        Timer t1 = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentScene != null){
                    currentScene.logicEvent();
                }
            }
        });
        t1.start();
    }

    @Override
    public void paintComponent(Graphics g){
        currentScene.paint(g);
    }

    private void changeCurrentScene(Scene scene) {
        if (scene == null){
            return;
        }
        this.removeKeyListener(kl);
        currentScene = scene;
        kl = scene.genKeyListener();
        this.addKeyListener(kl);
        this.setFocusable(true);
    }

    private frame.scene.Scene genSceneById(int id) {
        switch (id){
            case MENU_SCENE:
                return new MenuScene(gsChangeListener);
            case GAME_SCENE:
                return new GameScene(gsChangeListener);
            case END_SCENE:
                return new EndScene(gsChangeListener);
        }
        return null;
    }

}
