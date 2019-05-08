/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame.scene;

import character.Actor;
import character.Button;
import character.GameObject;
import frame.MainPanel;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author s7207
 */
public class ModeScene extends Scene{
    private GameObject background, road, hole_top, hole;
    private GameObject Actor_1,Actor_2,Actor_3,Actor_4;
    private GameObject Frame;
    private GameObject ChooseFrame;
    private GameObject one,two,person;
    int x;
    private Button buttonStory, buttonInfinity, button2P;
    private Actor player;
    private int countM,countI,count2;
    
    public ModeScene(MainPanel.GameStatusChangeListener gsChangeListener){
        super(gsChangeListener);
        this.background = new GameObject(0,-22,500, 700, "background/MenuBackground.png");
        this.ChooseFrame = new GameObject(29,110,400,200,"background/ChooseFrame.png");
        this.Frame = new GameObject(45,195,74,74,"background/Frame.png");
        this.one = new GameObject(45,270,35,35,"background/1P.png");
        this.x = 45;
        this.Actor_1 = new GameObject(50,200,64,64,"Actor/Actor_1.png");
        this.Actor_2 = new GameObject(150,200,64,64,"Actor/Actor_2.png");
        this.Actor_3 = new GameObject(250,200,64,64,"Actor/Actor_3.png");
        this.Actor_4 = new GameObject(350,200,64,64,"Actor/Actor_4.png");
        this.road = new GameObject(0, 644, 600, 44, "background/Road.png");
        this.hole_top = new GameObject(400,630,64,32,"background/hole_top.png");
        this.hole = new GameObject(400,630,64,32,"background/hole.png");
        this.buttonStory = new Button(60,400, 100, 75, 150, 100, "button/Button_Story.png");
        this.buttonInfinity = new Button(190,400,100, 75, 150, 100,"button/Button_Infinity.png");
        this.button2P = new Button(320,400,100, 75, 150, 100,"button/Button_2P.png");
        this.player = new Actor(250, 700, 32, 32);
        this.person = this.one;
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
                    case KeyEvent.VK_1:
                        Frame.setX(45);
                        person.setX(x);
                        break;
                    case KeyEvent.VK_2:
                        Frame.setX(145);
                        person.setX(100+x);
                        break;
                    case KeyEvent.VK_3:
                        Frame.setX(245);
                        person.setX(200+x);
                        break;
                    case KeyEvent.VK_4:
                        Frame.setX(345);
                        person.setX(300+x);
                        break;
                    case KeyEvent.VK_ENTER:
                        if(person==two){
                            
                        }
                        x = 80;
                        two = new GameObject(Frame.getX()+35,270,35,35,"background/2P.png");
                        person = two;
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
        buttonStory.setImageOffsetX(0);
        buttonInfinity.setImageOffsetX(0);
        button2P.setImageOffsetX(0);
        player.update();
//        player.setBoundary(); // 更新完座標後，設定邊界
        player.stay();
        // 按鈕碰撞換圖
        if(buttonStory.checkCollision(player)){
            buttonStory.setImageOffsetX(1);
            if (countM++ == 1){ // 一個延遲後切換場景
                hole_top.setX(450);
                countM = 0;
                // 切換至遊戲場景
                //gsChangeListener.changeScene(MainPanel.GAME_SCENE);
            }
        }
        if(hole_top.getX()==450&&hole.checkCollision(player)){
            if (countM++ == 6){
                gsChangeListener.changeScene(MainPanel.LOADING_SCENE);
                countM = 0;
            }
        }
        if(buttonInfinity.checkCollision(player)){
            buttonInfinity.setImageOffsetX(1);
            if (countM++ == 1){ // 一個延遲後切換場景
                hole_top.setX(451);
                countM = 0;
                // 切換至遊戲場景
                //gsChangeListener.changeScene(MainPanel.GAME_SCENE);
                }
            }
        if(hole_top.getX()==451&&hole.checkCollision(player)){
            if (countM++ == 6){
                gsChangeListener.changeScene(MainPanel.MENU_SCENE);
                countM = 0;
            }
        }
        if(button2P.checkCollision(player)){
            button2P.setImageOffsetX(1);
            if (countM++ == 1){ // 一個延遲後切換場景
                hole_top.setX(452);
                countM = 0;
                // 切換至遊戲場景
                //gsChangeListener.changeScene(MainPanel.GAME_SCENE);
            }
        }
        if(hole_top.getX()==452&&hole.checkCollision(player)){
            if (countM++ == 6){
                gsChangeListener.changeScene(MainPanel.LOADING_SCENE);
                countM = 0;
            }
        }
            // 切換至排行場景
            // ...待補
    }
    


    @Override
    public void paint(Graphics g) {
        background.paint(g);
        ChooseFrame.paint(g);
        Frame.paint(g);
        one.paint(g);
        if(person==two){
            two.paint(g);
        }
        Actor_1.paint(g);
        Actor_2.paint(g);
        Actor_3.paint(g);
        Actor_4.paint(g);
        road.paint(g);
        buttonStory.paint(g);
        buttonInfinity.paint(g);
        button2P.paint(g);
        hole.paint(g);
        hole_top.paint(g);
        player.paint(g);
    }
}
