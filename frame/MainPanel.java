package frame;

import character.GameObject;
import frame.scene.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class MainPanel extends javax.swing.JPanel {
    public static final int MENU_SCENE = 0;
    public static final int GAME_SCENE = 1;
    public static final int LEADER_BOARD_SCENE = 2;
    public static final int END_SCENE = 3;
    public static final int GAME_OVER_SCENE = 4;

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

        changeCurrentScene(genSceneById(LEADER_BOARD_SCENE));

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

    // 由場景來設定邊界判定
    public static void checkLeftRightBoundary(GameObject gameObject){
        if (gameObject.getLeft() > GameFrame.FRAME_WIDTH){
            gameObject.setX(0);
        }
        if (gameObject.getRight() < 0){
            gameObject.setX(GameFrame.FRAME_WIDTH - gameObject.getDrawWidth());
        }
    }

    private frame.scene.Scene genSceneById(int id) {
        switch (id){
            case MENU_SCENE:
                return new MenuScene(gsChangeListener);
            case GAME_SCENE:
                return new GameScene(gsChangeListener);
            case LEADER_BOARD_SCENE:
                return new LeaderBoardScene(gsChangeListener);
            case END_SCENE:
                return new EndScene(gsChangeListener);
            case GAME_OVER_SCENE:
                return new GameOverScene(gsChangeListener);
        }
        return null;
    }

}
