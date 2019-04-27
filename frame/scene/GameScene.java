package frame.scene;

import character.Actor;
import character.Floor;
import character.GameObject;
import character.trap.TrapGenerator;
import frame.GameFrame;
import frame.MainPanel;
import sun.applet.Main;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameScene extends Scene {
    private GameObject background_0, background_1, background_end, roof, hungerCount, hungerBack;
    private GameObject fire_left, fire_right;
    private Actor player;
    private ArrayList<Floor> floors;

    private int time;
    private int minute, second; // 印出時間
    private String colon; // 冒號
    private int timeCount; // 時間刷新delay

    private int key; // 鍵盤輸入值
    private int count; // 死亡跳起計數器

    public GameScene(MainPanel.GameStatusChangeListener gsChangeListener) {
        super(gsChangeListener);
        // 場景物件
        background_0 = new GameObject(0, -22, 500, 700, "background/EgyptBackground_0.png");
        background_1 = new GameObject(0, -22 + 700, 500, 700, "background/EgyptBackground_0.png");
        background_end = new GameObject(0, -22+700, 500, 700, "background/EgyptBackground_1.png");
        fire_left = new GameObject(0, 10, 100, 100, "background/Fire.png");
        fire_right = new GameObject(490, 10, 100, 100, "background/Fire.png");
        roof = new GameObject(0, 70, 500, 32, "background/Roof.png");
        player = new Actor(250, 100, 32, 32);
        // 飢餓值
        hungerBack = new GameObject(100, 30, 100, 20, "background/Hunger.png");
        hungerCount = new GameObject(100, 30, 100, 20, "background/HungerCount.png");
        // 時間相關
        time = 60;
        colon = " : ";
        // 測試用地板
        floors = new ArrayList<>();
        floors.add(new Floor(200, 500, TrapGenerator.getInstance().genSpecificTrap(0)));
        floors.add(new Floor(200 + 64, 500, TrapGenerator.getInstance().genSpecificTrap(4)));
        floors.add(new Floor(200 + 64 + 64, 500, TrapGenerator.getInstance().genSpecificTrap(3)));
        floors.add(new Floor(200 + 64 + 64 + 64, 400, TrapGenerator.getInstance().genSpecificTrap(3)));
        floors.add(new Floor(200 - 64, 400, TrapGenerator.getInstance().genSpecificTrap(1)));
        floors.add(new Floor(200 - 64 - 64, 400, TrapGenerator.getInstance().genSpecificTrap(2)));
        floors.add(new Floor(200 - 64 - 64 - 64, 500, TrapGenerator.getInstance().genSpecificTrap(4)));
    }

    @Override
    public KeyListener genKeyListener() {

        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                key = e.getKeyCode();
                switch (key){
                    case KeyEvent.VK_RIGHT:
                        player.changeDir(Actor.MOVE_RIGHT);
                        player.setSpeedX(player.getSpeedX()+3);
                        break;
                    case KeyEvent.VK_LEFT:
                        player.changeDir(Actor.MOVE_LEFT);
                        player.setSpeedX(player.getSpeedX()-3);
                        break;
                    case KeyEvent.VK_UP:
                        player.changeDir(Actor.MOVE_UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        player.changeDir(Actor.MOVE_DOWN);
                        break;
                    case KeyEvent.VK_R:
                        player.reset();
                        break;
                }
            }

            @Override
            public  void keyReleased(KeyEvent e){
//                player.setSpeedX(0);
            }
        };
    }

    @Override
    public void logicEvent() {
        if (!player.isDie()){ // 還沒死亡的狀態
            MainPanel.checkLeftRightBoundary(player);
            // 逆向摩擦力
            friction(player);
            if (checkTopBoundary(player)){
                player.touchRoof();
            }else {
                player.stay();
            }
            for (int i = 0; i < floors.size(); i++) {
                player.checkOnFloor(floors.get(i));
                floors.get(i).stay();
                if (checkTopBoundary(floors.get(i))){
                    floors.remove(i);
                }
            }
            // 人物飢餓
//            player.hunger();
            // 繪製現在飢餓值
            hungerCount.setDrawWidth(player.getHunger());
            // 每次都要更新此次座標
            for (Floor floor : floors) {
                floor.update();
            }
            player.update();
            // 掉落死亡 or 餓死後落下
            if (player.getBottom() > GameFrame.FRAME_HEIGHT-22){
                player.die();
            }
            // 時間刷新
            updateTime();
        }else {
            // 死亡跳起後落下
            if (count++ < 20){
                player.setSpeedX(0);
                player.setY(player.getY()-5);
            }else {
                player.setSpeedX(0);
                player.update();
                // 完全落下後切場景
                if (player.getBottom() > GameFrame.FRAME_HEIGHT){
                    gsChangeListener.changeScene(MainPanel.GAME_OVER_SCENE);
                }
            }
        }
        updateBackgroundImage();
    }

    @Override
    public void paint(Graphics g) {
        background_0.paint(g);
        background_1.paint(g);
        fire_left.paint(g);
        fire_right.paint(g);
        roof.paint(g);
        hungerBack.paint(g);
        hungerCount.paint(g);
        for (Floor floor : floors) {
            floor.paint(g);
        }
        player.paint(g);

        // 印出時間
        Font font = g.getFont().deriveFont(20.0f);
        g.setFont(font);
        g.setColor(Color.RED);
        FontMetrics fm = g.getFontMetrics();
        int msgWidth = fm.stringWidth(String.valueOf(minute));
        int msgAscent = fm.getAscent();
        g.drawString(String.valueOf(minute), 300, 30);
        g.drawString(colon, 300 + msgWidth, 30);
        g.drawString(String.valueOf(second), 300 + 2*msgWidth, 30);
    }

    // 比天花板高就消失
    private boolean checkTopBoundary(GameObject gameObject){
        return gameObject.getTop() < this.roof.getBottom();
    }

    // 更新背景圖
    private void updateBackgroundImage(){
        if (background_0.getBottom() < 0){
            background_0 = new GameObject(0, -22 + 700, 500, 700, "background/EgyptBackground_0.png");
        }
        if (background_1.getBottom() < 0){
            background_1 = new GameObject(0, -22 + 700, 500, 700, "background/EgyptBackground_0.png");
        }
        background_0.setY(background_0.getY() - 3);
        background_1.setY(background_1.getY() - 3);
//        if (time == 20){
//            background_end.setY(background_end.getY() - 1);
//        }
        background_0.setBoundary();
        background_1.setBoundary();
    }

    // 更新時間
    private void updateTime(){
        minute = time / 60;
        second = time % 60;
        if(++timeCount % 40 == 0){
            time -= 1;
        }
    }
}
