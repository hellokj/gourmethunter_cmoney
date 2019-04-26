package frame.scene;

import character.Actor;
import character.Button;
import character.GameObject;
import frame.MainPanel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuScene extends Scene{
    private GameObject background, logo, road;
    private Button buttonStart, buttonLeader;
    private Actor player;
    private int count; // 碰觸按鈕延遲

    public MenuScene(MainPanel.GameStatusChangeListener gsChangeListener){
        super(gsChangeListener);
        this.background = new GameObject(0,-22,500, 700, "background/MenuBackground.png");
        this.logo = new GameObject(50,60,400,200,"background/Logo.png");
        this.road = new GameObject(0, 654, 500, 10, "floor/Floor.png");
        this.buttonStart = new Button(50,375, 150, 100, "button/Button_start.png");
        this.buttonLeader = new Button(280,375,150,100,"button/Button_LB.png");
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
        // 設定按鈕圖片
        buttonStart.setImageOffsetX(0);
        buttonLeader.setImageOffsetX(0);
        player.update();
//        player.setBoundary(); // 更新完座標後，設定邊界
        player.stay();
        // 按鈕碰撞換圖
        if(buttonStart.checkCollision(player)){
            buttonStart.setImageOffsetX(1);
            if (count++ == 1){ // 一個延遲後切換場景
                // 切換至遊戲場景
                gsChangeListener.changeScene(MainPanel.GAME_SCENE);
            }
        }
        if(buttonLeader.checkCollision(player)){
            buttonLeader.setImageOffsetX(1);
            // 切換至排行場景
            // ...待補
        }
    }

    @Override
    public void paint(Graphics g) {
        background.paint(g);
        road.paint(g);
        buttonStart.paint(g);
        buttonLeader.paint(g);
        logo.paint(g);
        player.paint(g);
    }
}