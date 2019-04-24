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
    public static final float GRAVITY = 0.5f;
    private GameObject background, roof, hungerCount, hungerBack;
    private Actor player;
    private ArrayList<Floor> floors;

    private int key; // 鍵盤輸入值
    private int count; // 死亡跳起

    public GameScene(MainPanel.GameStatusChangeListener gsChangeListener) {
        super(gsChangeListener);
        // 場景物件
        background = new GameObject(0, 0, 500, 700, "background/EgyptBackground.png");
        roof = new GameObject(0, 70, 500, 32, "background/Roof.png");
        player = new Actor(250, 100, 32, 32);
        hungerBack = new GameObject(100, 30, 100, 20, "background/Hunger.png");
        hungerCount = new GameObject(100, 30, 100, 20, "background/HungerCount.png");
        floors = new ArrayList<>();
        // 測試用地板
        floors.add(new Floor(200, 500, TrapGenerator.getInstance().genSpecificTrap(0)));
        floors.add(new Floor(200 + 64, 500, TrapGenerator.getInstance().genSpecificTrap(4)));
        floors.add(new Floor(200 + 64 + 64, 500, TrapGenerator.getInstance().genSpecificTrap(0)));
        floors.add(new Floor(200 + 64 + 64 + 64, 400, TrapGenerator.getInstance().genSpecificTrap(3)));
        floors.add(new Floor(200 - 64, 400, TrapGenerator.getInstance().genSpecificTrap(1)));
        floors.add(new Floor(200 - 64 - 64, 400, TrapGenerator.getInstance().genSpecificTrap(4)));
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
                        break;
                    case KeyEvent.VK_LEFT:
                        player.changeDir(Actor.MOVE_LEFT);
                        break;
                    case KeyEvent.VK_R:
                        player.reset();
                        break;
                }
            }

            @Override
            public  void keyReleased(KeyEvent e){

            }
        };
    }

    @Override
    public void logicEvent() {
        if (!player.isDie()){ // 還沒死亡的狀態
            checkLeftRightBoundary(player);
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

            // 每次都要更新此次座標
            for (Floor floor : floors) {
                floor.update();
            }
            player.update();
            if (player.getBottom() > GameFrame.FRAME_HEIGHT){
                // 掉落死亡 or 餓死後落下
                player.die();
            }
        }else {
            // 死亡跳起後落下
            if (count++ < 20){
                player.setY(player.getY()-5);
            }else {
                player.setY(player.getY()+10);
            }
        }
        hungerCount.setDrawWidth(player.getHunger());
        player.setBoundary();
        // 死亡後切場景
        if (player.getTop() > GameFrame.FRAME_HEIGHT){
            gsChangeListener.changeScene(MainPanel.END_SCENE);
        }
    }

    @Override
    public void paint(Graphics g) {
        background.paint(g);
        roof.paint(g);
        hungerBack.paint(g);
        hungerCount.paint(g);
        for (Floor floor : floors) {
            floor.paint(g);
        }
        player.paint(g);
    }

    // 由場景來設定邊界判定
    private void checkLeftRightBoundary(GameObject gameObject){
        if (gameObject.getLeft() > GameFrame.FRAME_WIDTH){
            gameObject.setX(0);
        }
        if (gameObject.getRight() < 0){
            gameObject.setX(GameFrame.FRAME_WIDTH - gameObject.getDrawWidth());
        }
    }

    // 比天花板高就消失
    private boolean checkTopBoundary(GameObject gameObject){
        return gameObject.getTop() < this.roof.getBottom();
    }

    // 顯示時間
    private void showTime(){

    }
}
