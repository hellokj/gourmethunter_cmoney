package characters;

import characters.floor.Floor;

import javax.xml.stream.FactoryConfigurationError;
import java.awt.*;
import java.util.ArrayList;

public class Actor extends GameObject{
    public static final int MOVE_DOWN = 0;
    public static final int MOVE_RIGHT = 1;
    public static final int MOVE_UP = 2;
    public static final int MOVE_LEFT = 3;
    public static final int[] MOVING_PATTERN = {0, 1, 2, 3, 2, 1}; // 走路模式
    public static int FALLING_SPEED = 4;
    public static int MOVING_SPEED = 8;

    private int direction;
    private int movingAction;

    public Actor(){

    }

    public Actor(int x, int y, int imageWidth, int imageHeight){
        super(x, y, imageWidth, imageHeight);
        direction = MOVE_DOWN;
        dy = FALLING_SPEED;
    }

    @Override
    public void move(){
        x += dx;
        y += dy;
        stay();
    }

    public void changeDir(int direction){
        this.direction = direction;
    }

    // 停止狀態 原地踏步
    public void stay(){
        this.imageOffsetX = MOVING_PATTERN[movingAction % MOVING_PATTERN.length];
        this.movingAction = movingAction % MOVING_PATTERN.length;
        this.movingAction++;
    }

    public boolean checkOnFloor(Floor floor){
//        boolean onFloor = false;
        if(this.bottom > floor.bottom){
            dy = FALLING_SPEED;
            return false;
        }
        if(this.right > floor.left || this.left < floor.right){
            if(this.left > floor.right || this.right < floor.left){
                dy = FALLING_SPEED;
                return false;
            }else {
                if(this.bottom + dy > floor.top){
                    dy = floor.dy;
                    y = floor.top - imageHeight;
                    return true;
                }
            }
        }else {
            dy = FALLING_SPEED;
            return false;
        }
        return false;
    }

    public boolean checkOnFloor(ArrayList<Floor> floors){
        for (Floor floor : floors) {
            checkOnFloor(floor);
        }
        return false;
    }

    @Override
    public void paint(Graphics g){
        setBoundary();
        g.drawImage(image, x, y, x + imageWidth, y + imageHeight,
                direction*4*imageWidth + imageWidth*imageOffsetX, imageOffsetY,
                direction*4*imageWidth + imageWidth*imageOffsetX + imageWidth, imageOffsetY + imageHeight, null);
    }
}
