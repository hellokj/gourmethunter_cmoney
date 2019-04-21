package frame;

import characters.Actor;
import characters.GameObject;
import characters.floor.Floor;
import characters.rule.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static characters.Actor.*;

public class MainPanel extends JPanel {
    private GameObject background;
    private GameObject roof;
    private Actor player;
    private ArrayList<Floor> floors;
    private NormalRule normalRule;
    private RunningRule runningRule1, runningRule2, runningRule3;
    private StoneRule stoneRule;
    private SpringRule springRule;
//    private FloorGenerator floorGenerator;
    private int key;

    public MainPanel(){
        this.addKeyListener(new KeyListener());
        this.setFocusable(true);

        player = new Actor(250, 400, 32, 32);
        player.setImage("src/resources/Actor.png");

        background = new GameObject(0, 0, 1417, 1984, 500, 700);
        background.setImage("src/resources/EgyptBackground.png");

        roof = new GameObject(0, 0, 500, 32, 500, 32);
        roof.setImage("src/resources/Roof.png");

        // rule 測試區
        normalRule = new NormalRule();
        runningRule1 = new RunningRule(6);
        runningRule2 = new RunningRule(6);
        runningRule3 = new RunningRule(6);
        stoneRule = new StoneRule();
        springRule = new SpringRule();


        floors = new ArrayList<>();
//        floorGenerator = new FloorGenerator();
        floors.add(new Floor(player.x - 16, player.y + 32, springRule)); // 初始站立的階梯 0
        floors.add(new Floor(player.x - 16 - 64, player.y + 32, springRule)); // 初始站立的階梯 1
        floors.add(new Floor(player.x - 16 - 64 - 64, player.y + 32, runningRule2)); // 初始站立的階梯 2
        floors.add(new Floor(player.x - 16 - 64 - 64 - 64, player.y + 32, runningRule3)); // 初始站立的階梯 3
        floors.add(new Floor(player.x - 16 + 64, player.y + 32, stoneRule)); // 初始站立的階梯 4
        floors.add(new Floor(player.x - 16 + 64 + 64, player.y + 32, runningRule1)); // 初始站立的階梯 5
        floors.add(new Floor(player.x - 16 + 64 + 64 + 64, player.y + 32, normalRule)); // 初始站立的階梯 6
//        floors.add(new Floor(100, 250, runningRule));
        for (int i = 0; i < 10; i++) {
//            floors.add(floorGenerator.genFloor(floors.get(i)));
        }

        // Timer
        Timer t1 = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 階梯超出上邊界後消失
                for (int i = 0; i < floors.size(); i++) {
                    floors.get(i).stay();
                    if(floors.get(i).checkTopBoundary()){
                        floors.remove(i);
                    }
                }
                // 遊戲中(非畫面中)的階梯總數至少有10個
//                player.stay();
                if(floors.size() < 10){
                    for (int i = floors.size(); i < 10; i++) {
//                        floors.add(floorGenerator.genFloor(floors));
                    }
                }
                // 確認人物是否站立於階梯上
                boolean isOnFloor = false;
                for (int i = 0; i < floors.size(); i++){
                    isOnFloor = player.checkOnFloor(floors.get(i));
                    if (isOnFloor){
                        player.testGravity = 0;
                    }
                }
                if (!isOnFloor){
                    player.fall();
                }
                if(!player.checkLeftRightBoundary(MainPanel.this)){
                    player.move();
                }else {
                    player.stay();
                }

                for (int i = 0; i < floors.size(); i++){
                    if (!floors.get(i).checkOn(player)){
                        floors.get(i).reset();
                    }
                }
                // 階梯持續上升
                for (Floor floor : floors) {
                    floor.rise();
                }
            }
        });
        t1.start();
        // end game stage
    }

    class KeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            key = e.getKeyCode();
            // 上下左右
//            if (key == KeyEvent.VK_DOWN){
//                player.changeDir(Actor.MOVE_DOWN);
//            }
            if (key == KeyEvent.VK_RIGHT){
                player.changeDir(Actor.MOVE_RIGHT);
                if (player.getState()){
                    player.speedX = MOVING_SPEED_FAT;
                }else {
                    player.speedX = MOVING_SPEED_SLIM;
                }
            }
//            if (key == KeyEvent.VK_UP){
//                player.changeDir(Actor.MOVE_UP);
//            }
            if (key == KeyEvent.VK_LEFT){
                player.changeDir(Actor.MOVE_LEFT);
//                player.choosingImagesCounter++;
                if (player.getState()){
                    player.speedX = MOVING_SPEED_FAT;
                }else {
                    player.speedX = MOVING_SPEED_SLIM;
                }
            }
            if (key == KeyEvent.VK_R){ // 上方重來
                player.x = 250;
                player.y = 100;
                player.testGravity = 0;
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
            if(key == e.getKeyCode()){
                key = -1;
                player.speedX = 0;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        background.paint(g);
        for (int i = 0; i < floors.size(); i++){
            floors.get(i).paint(g);
        }

        player.paint(g);
        roof.paint(g);
    }
}
