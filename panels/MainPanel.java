package panels;

import characters.Actor;
import characters.GameObject;
import characters.floor.Floor;
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
    private Floor floor;
    private int key;

    public MainPanel(){
        background = new GameObject();
        background.setImage("src/resources/Background.png");
        floor = new Floor(250, 600,64, 16);
        floor.setImage("src/resources/Floor1.png");
        player = new Actor(30, 30, 32, 32);
        player.setImage("src/resources/Actor.png");
        this.addKeyListener(new KeyListener());
        this.setFocusable(true);

        // Timer
        Timer t1 = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!player.checkLeftRightBoundary(MainPanel.this)){
                    player.move();
                }else {
                    player.stay();
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
        player.paint(g);
    }
}
