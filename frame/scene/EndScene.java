package frame.scene;

import character.Actor;
import character.GameObject;
import frame.MainPanel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class EndScene extends Scene {
    GameObject background;
    GameObject light1;
    GameObject light2;
    GameObject food;
    GameObject player;
    int lightCount;
    ArrayList<String> foods;
    int foodKind = 9;

    public EndScene(MainPanel.GameStatusChangeListener gsChangeListener) {
        super(gsChangeListener);
        this.lightCount = 0;
        this.foods = new ArrayList();
        for(int i=0;i<foodKind;i++){
            foods.add("food/food"+(i+1)+".png");
        }
        int r = (int)(Math.random()*foodKind);
        this.background = new GameObject(0,0,495,655,"background/EndBackground.png");
        this.light1 = new GameObject(80,80,375,375,"background/light1.png");
        this.light2 = new GameObject(80,80,375,375,"background/light2.png");
        this.food = new GameObject(175,175,200,200,foods.get(r));
        this.player = new GameObject(-200,450,175,200,"actor/ActorBack.png");
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
        background.paint(g);
        light1.paint(g);
        light2.paint(g);
        lightCount++;
        if(lightCount<6){
            light2.setImageOffsetX(1);
            light1.setImageOffsetX(0);
        }
        else if(lightCount>=6){
            light2.setImageOffsetX(0);
            light1.setImageOffsetX(1);
            if(lightCount==12){
                lightCount = 0;
            }
        }
        food.paint(g);
        player.paint(g);
        player.setX(player.getX()+8);
            if(player.getX()>0){
                player.setX(0);
            }
    }
}
