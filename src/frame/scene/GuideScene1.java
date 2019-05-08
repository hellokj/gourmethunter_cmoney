/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame.scene;
import character.Actor;
import character.AnimationGameObject;
import character.Button;
import character.Floor;
import frame.MainPanel;

import character.GameObject;
import character.trap.FlashTrap;
import character.trap.TrapGenerator;
import frame.MainPanel;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author s7207
 */
public class GuideScene1 extends Scene {
    private GameObject background;
    private GameObject frameS,frameG,frameS2,frameG2;
    private GameObject hint,num1,num2,num3,num4;
    private Floor floorSpring,floorRunning,floorFlashing;
    private Actor player1,player2,player3,player4;
    private int flashcount; //閃光延遲
    private AnimationGameObject DanceTest;
    private int choose;
    //private int count;
    private Button menu,next;
    
    public GuideScene1(MainPanel.GameStatusChangeListener gsChangeListener) {
        super(gsChangeListener);
        this.background = new GameObject(0,0,500,700,"background/EndBackground.png");
        this.frameS = new GameObject(80,70,150,250,"background/frameS.png");
        this.frameG = new GameObject(250,70,150,250,"background/frameG.png");
        this.frameG2 = new GameObject(80,330,150,250,"background/frameG.png");
        this.frameS2 = new GameObject(250,330,150,250,"background/frameS.png");
        this.hint = new GameObject(10,10,300,50,"background/Guide.png");
        this.num1 = new GameObject(90,80,30,30,"background/num1.png");
        this.num2 = new GameObject(260,80,30,30,"background/num2.png");
        this.num3 = new GameObject(90,340,30,30,"background/num3.png");
        this.num4 = new GameObject(260,340,30,30,"background/num4.png");
        floorRunning = new Floor(130, 270, TrapGenerator.getInstance().genSpecificTrap(1));
        floorSpring = new Floor(130, 530, TrapGenerator.getInstance().genSpecificTrap(4));
        floorFlashing = new Floor(300, 530, TrapGenerator.getInstance().genSpecificTrap(5));
        DanceTest = new AnimationGameObject(300, 230, 64, 80, 64, 80,"floor/DanceTest.png");
        int[] moving = {0,1,2,3,4,5};
        DanceTest.setMovingPattern(moving);
        DanceTest.setStayDelay(15);
        player1 = new Actor(140, 130, 32, 32);
        player2 = new Actor(310, 130, 32, 32);
        player3 = new Actor(140, 390, 32, 32);
        player4 = new Actor(310, 390, 32, 32);
        menu = new Button(0,580,120,70,150,100,"button/Button_Menu_L.png");
        next = new Button(360,580,120,70,150,100,"button/Button_Next_R.png");
    }
    @Override
    public KeyListener genKeyListener() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
                switch (key){                   
                    case KeyEvent.VK_1:
                        setChoose(1);
                        break;
                    case KeyEvent.VK_2:
                        setChoose(2);
                        break;
                    case KeyEvent.VK_3:
                        setChoose(3);
                        break;
                    case KeyEvent.VK_4:
                        setChoose(4);
                        break;
                    case KeyEvent.VK_RIGHT:
                        next.setImageOffsetX(1);
                        break;
                    case KeyEvent.VK_LEFT:
                        menu.setImageOffsetX(1);
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e){
                int key = e.getKeyCode();
                if(key==KeyEvent.VK_RIGHT){
                    next.setImageOffsetX(0);
                    gsChangeListener.changeScene(MainPanel.MENU_SCENE);
                }
                if(key==KeyEvent.VK_LEFT){
                    menu.setImageOffsetX(0);
                    gsChangeListener.changeScene(MainPanel.MENU_SCENE);
                }
            }
        };
    }
    private void setChoose(int choose){
        this.choose = choose;
    }
    private void playerStop(Actor player,int x,int y){
        player.setX(x);
        player.setY(y);
    }

    @Override
    public void logicEvent() {
        floorRunning.stay();
        floorSpring.stay();
        floorFlashing.stay();
        if(choose==2){
            player2.stay();
            player2.update();
            if(player2.checkOnObject(DanceTest)){
                DanceTest.stay();
                playerStop(player1, 140, 130);
                playerStop(player3, 140, 390);
                playerStop(player4, 310, 390);
            }
        }
        else if(choose==1){
            this.outofFrame(player1, floorRunning, frameS, 140, 150);
            playerStop(player2, 310, 130);
            playerStop(player3, 140, 390);
            playerStop(player4, 310, 390);
            DanceTest.setimageOffsetX(0);
        }
        else if(choose==3){
            this.outofFrame(player3, floorSpring, frameG2, 140, 410);
            playerStop(player1, 140, 130);
            playerStop(player2, 310, 130);
            playerStop(player4, 310, 390);
            DanceTest.setimageOffsetX(0);
        }
        else if(choose==4){
            this.outofFrame(player4, floorFlashing, frameS2, 310, 410);
            playerStop(player1, 140, 130);
            playerStop(player2, 310, 130);
            playerStop(player3, 140, 390);
            DanceTest.setimageOffsetX(0);
        }
         
    }
    public void outofFrame(Actor player,Floor floor,GameObject frame,int x,int y){
        player.stay();
        player.update();
        player.checkOnFloor(floor);
        if(player.getBottom()>frame.getBottom()||player.getLeft()<frame.getLeft()||
            player.getRight()>frame.getRight()){
            player.setX(x);
            player.setY(y);
            player.setSpeedX(0);
        }
    }
    @Override
    public void paint(Graphics g) {
        background.paint(g);
        frameS.paint(g);
        frameG.paint(g);
        frameS2.paint(g);
        frameG2.paint(g);
        hint.paint(g);
        num1.paint(g);
        num2.paint(g);
        num3.paint(g);
        num4.paint(g);
        floorRunning.paint(g);
        DanceTest.paint(g);
        floorSpring.paint(g);
        floorFlashing.paint(g);
        player1.paint(g);
        player2.paint(g);
        player3.paint(g);
        player4.paint(g);
        menu.paint(g);
        next.paint(g);
        
        //閃光開始
        if(FlashTrap.getFlashState()){
            flashcount++;
        }//閃光持續
        if(flashcount<15 && flashcount>0){
           FlashTrap.getFlash().setCounter(flashcount-1);
           //System.out.println("**"+flashcount);
           FlashTrap.getFlash().paint(g);
        }//閃光結束
        else if(flashcount>=15){
            FlashTrap.setFlashState(false);
            flashcount = 0;
        }
    }
}
