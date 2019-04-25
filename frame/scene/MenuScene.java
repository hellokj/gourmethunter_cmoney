package frame.scene;

import character.Actor;
import character.GameObject;
import frame.GameFrame;
import frame.MainPanel;
import static frame.scene.GameScene.checkLeftRightBoundary;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuScene extends Scene{
    GameObject background;
    GameObject start;
    GameObject leader;
    GameObject logo;
    Actor player;
    int count;
    static int jumpState;

    public MenuScene(MainPanel.GameStatusChangeListener gsChangeListener){
        super(gsChangeListener);
        this.jumpState = 0;
        this.count = 0;
        this.background = new GameObject(0,0,495,655,0,0,500,700,"menu/menu_background.png");
        this.start = new GameObject(50,375,150,75,0,0,150,100,"menu/Button_start.png");
        this.leader = new GameObject(280,375,150,75,0,0,150,100,"menu/Button_LB.png");
        this.logo = new GameObject(40,60,400,200,0,0,300,100,"menu/Logo.png");
        player = new Actor(250, 600, 32, 32);
    }

    @Override
    public KeyListener genKeyListener() {
        return new KeyAdapter() {
            int keyin;
            @Override
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
                switch (key){
                    case KeyEvent.VK_RIGHT:
                        player.changeDir(Actor.MOVE_RIGHT);
                        
                        break;
                    case KeyEvent.VK_LEFT:
                        player.changeDir(Actor.MOVE_LEFT);
                        
                        break;
                    case KeyEvent.VK_UP:
                        if(player.getY()==600){
                            jumpState = 1;
                        }
                        if(player.getDirection()==Actor.MOVE_RIGHT){
                            player.setSpeedX(1);
                        }
                        else if(player.getDirection()==Actor.MOVE_LEFT){
                            player.setSpeedX(-1);
                        }
                        
                        break;
                }

            }
            @Override
            public void keyReleased(KeyEvent e){
                player.setSpeedX(0);
            }
        };
    }
    
    public static void setJumpState(int state){
        jumpState = state;
    }
    @Override
    public void paint(Graphics g) {
        background.paint(g);
        start.paint(g);
        leader.paint(g);
        logo.paint(g);
        player.paint(g);
        
    }

    @Override
    public void logicEvent() {
        checkLeftRightBoundary(player);
        start.setImageX(0);
        leader.setImageX(0);
        if(jumpState==1){
            player.jump();
            if(start.checkTouch(player)){
                count++;
                start.setImageX(150);
                if(count>1){
                    gsChangeListener.changeScene(MainPanel.GAME_SCENE);
                    count = 0;
                }
            }
            if(leader.checkTouch(player)){
                leader.setImageX(150);
            }
        }else{
            
            if(player.getY()<600){
                player.update();
            }else{
                player.setY(600);
                player.setSpeedY(0);
            }
        }
        player.walk();
        player.stay();
    }
}