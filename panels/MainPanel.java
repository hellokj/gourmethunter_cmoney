package panels;

import characters.Actor;
import characters.GameObject;
import characters.floor.Floor;
import characters.floor.FloorGenerator;
import resource.util.ResourcesManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static characters.Actor.MOVING_SPEED;

public class MainPanel extends JPanel {
    private GameObject background;
    private Actor player;
    private ArrayList<Floor> floors;
    private FloorGenerator floorGenerator;
    private int key;

    public MainPanel(){
        background = new GameObject();
        background.setImage("src/resources/EgyptBackground.png");
        floors = new ArrayList<>();
        floorGenerator = new FloorGenerator();
        for (int i = 0; i < 5; i++) {
            floors.add(floorGenerator.genFloor());
        }

        player = new Actor(250, 30, 32, 32);
        player.setImage("src/resources/Actor.png");
        this.addKeyListener(new KeyListener());
        this.setFocusable(true);

        // Timer
        Timer t1 = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < floors.size(); i++) {
                    if(floors.get(i).checkTopBoundary()){
                        floors.remove(i);
                    }
                    if(floors.size() < 5){
                        floors.add(floorGenerator.genFloor());
                    }
                }
                if (!player.checkLeftRightBoundary(MainPanel.this)){
                    if (player.checkOnFloor(floors)){
                        player.stay();
                    }else {
                        player.move();
                    }
                }else {
                    player.stay();
                }
                for (Floor floor : floors) {
                    floor.rise();
                }
            }
        });
        t1.start();
    }

    class KeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            key = e.getKeyCode();
            if (key == KeyEvent.VK_DOWN){
                player.changeDir(Actor.MOVE_DOWN);
            }
            if (key == KeyEvent.VK_RIGHT){
                player.changeDir(Actor.MOVE_RIGHT);
                player.dx = MOVING_SPEED;
            }
            if (key == KeyEvent.VK_UP){
                player.changeDir(Actor.MOVE_UP);
            }
            if (key == KeyEvent.VK_LEFT){
                player.changeDir(Actor.MOVE_LEFT);
                player.dx = -MOVING_SPEED;
            }
            if (key == KeyEvent.VK_R){ // 上方重來
                player.x = 250;
                player.y = 0;
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
            if(key == e.getKeyCode()){
                key = -1;
                player.dx = 0;
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
    }
}
