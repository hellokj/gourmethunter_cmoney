package frame.scene;

import character.Actor;
import character.Floor;
import character.GameObject;
import character.trap.NormalTrap;
import character.trap.TrapGenerator;
import frame.GameFrame;
import frame.MainPanel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameScene extends Scene {
    public static final float GRAVITY = 0.5f;
    private GameObject background, roof;
    private Actor player;
    private ArrayList<Floor> floors;

    // trap test
    private NormalTrap normalTrap;

    private int key;

    public GameScene(MainPanel.GameStatusChangeListener gsChangeListener) {
        super(gsChangeListener);
        // 場景物件
        background = new GameObject(0, 0, 500, 700, "EgyptBackground.png");
        roof = new GameObject(0, 0, 500, 32, "Roof.png");
        player = new Actor(250, 100, 32, 32, "Actor.png");
        floors = new ArrayList<>();
        // 測試用地板
        floors.add(new Floor(200, 350, TrapGenerator.getInstance().genSpecificTrap(0)));
        floors.add(new Floor(200 + 64, 350, TrapGenerator.getInstance().genSpecificTrap(1)));
        floors.add(new Floor(200 + 64 + 64, 350, TrapGenerator.getInstance().genSpecificTrap(0)));
        floors.add(new Floor(200 - 64, 350, TrapGenerator.getInstance().genSpecificTrap(1)));
        floors.add(new Floor(200 - 64 - 64, 350, TrapGenerator.getInstance().genSpecificTrap(4)));
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
        checkLeftRightBoundary(player);
        player.stay();
        for (int i = 0; i < floors.size(); i++) {
            player.checkOnFloor(floors.get(i));
            floors.get(i).stay();
        }

        // 每次都要更新此次座標
        for (int i = 0; i < floors.size(); i++) {
            floors.get(i).update();
        }
        player.update();
    }

    @Override
    public void paint(Graphics g) {
        background.paint(g);
        roof.paint(g);
        for (int i = 0; i < floors.size(); i++){
            floors.get(i).paint(g);
        }
        player.paint(g);
    }

    public static void checkLeftRightBoundary(GameObject gameObject){
        if (gameObject.getLeft() > GameFrame.FRAME_WIDTH){
            gameObject.setX(0);
        }
        if (gameObject.getRight() < 0){
            gameObject.setX(GameFrame.FRAME_WIDTH - gameObject.getDrawWidth());
        }
    }
}
