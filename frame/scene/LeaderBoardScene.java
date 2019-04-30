package frame.scene;

import character.Actor;
import character.Button;
import character.GameObject;
import frame.MainPanel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LeaderBoardScene extends Scene {
    private GameObject background,paper,road;
    private Actor player;
    private Button buttonBack;
    int count;
    
    public LeaderBoardScene(MainPanel.GameStatusChangeListener gsChangeListener) {
        super(gsChangeListener);
        this.background = new GameObject(0,-22,500, 700, "background/MenuBackground.png");
        this.road = new GameObject(0, 654, 500, 10, "floor/Floor.png");
        this.paper = new GameObject(50,100,350,450, "background/Paper.png");
        this.buttonBack = new Button(300,475, 150, 100, "button/Button_Back.png");
        this.player = new Actor(250, 622, 32, 32);
    }

    @Override
    public KeyListener genKeyListener() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
                switch (key){
                    case KeyEvent.VK_RIGHT:
                        player.changeDir(Actor.MOVE_RIGHT);
                        player.setSpeedX(player.getSpeedX()+5);
                        break;
                    case KeyEvent.VK_LEFT:
                        player.changeDir(Actor.MOVE_LEFT);
                        player.setSpeedX(player.getSpeedX()-5);
                        break;
                    case KeyEvent.VK_SPACE:
                        if (player.canJump()){
                            player.jump();
                        }
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e){
//                player.setSpeedX(0);
            }
        };
    }
    @Override
    public void logicEvent() {
        MainPanel.checkLeftRightBoundary(player);
        friction(player);
        if (player.checkOnObject(road)){
            player.setCanJump(true); // 將可以跳躍設回true
            player.setSpeedY(0); // 落到地板上，
        }
        player.update();
        // 設定按鈕圖片
        buttonBack.setImageOffsetX(0);
        if(buttonBack.checkCollision(player)){
            buttonBack.setImageOffsetX(1);
            if (count++ == 10){ // 一個延遲後切換場景
                gsChangeListener.changeScene(MainPanel.MENU_SCENE);
                count = 0;
            }
        }
    }
    
    @Override
    public void paint(Graphics g) {
        background.paint(g);
        road.paint(g);
        paper.paint(g);
        buttonBack.paint(g);
        player.paint(g);
    }    
}
