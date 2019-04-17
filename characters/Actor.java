package characters;

import characters.floor.Floor;

import java.awt.*;
import java.util.ArrayList;

public class Actor extends GameObject{
    private final int HUNGER_MODE = 75; // 轉換模式 飢餓程度數值
    public static final int MOVE_DOWN = 0;
    public static final int MOVE_RIGHT = 1;
    public static final int MOVE_UP = 2;
    public static final int MOVE_LEFT = 3;
    public static final int[] MOVING_PATTERN = {0, 1, 2, 3, 2, 1}; // 走路模式
    public static int FALLING_SPEED_FAT = 4;
    public static int MOVING_SPEED_FAT = 3;
    public static int FALLING_SPEED_SLIM = 2;
    public static int MOVING_SPEED_SLIM = 8;

    private int hunger; // 飢餓程度
    private boolean state; // 胖瘦狀態 true:肥 false:瘦
    private int direction;
    private int movingAction;

    public Actor(){

    }

    public Actor(int x, int y, int imageWidth, int imageHeight){
        super(x, y, imageWidth, imageHeight);
        direction = MOVE_DOWN;
        dy = FALLING_SPEED_FAT;
        hunger = 0;
        delay = 5;
        state = true;
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

    public void changeState(){
        if (hunger > HUNGER_MODE){
            state = false;
        }else {
            state = true;
        }
    }

    public boolean getState(){
        return this.state;
    }

    private boolean checkOnFloor(Floor floor){
        if(this.bottom > floor.bottom){
            if(this.state){
                dy = FALLING_SPEED_FAT;
            }else {
                dy = FALLING_SPEED_SLIM;
            }
            return false;
        }
        if(this.right > floor.left || this.left < floor.right){
            if(this.left > floor.right || this.right < floor.left){
                if(this.state){
                    dy = FALLING_SPEED_FAT;
                }else {
                    dy = FALLING_SPEED_SLIM;
                }
                return false;
            }
            if(this.bottom > floor.top){
                dy = floor.dy;
                y = floor.top - imageHeight + dy;
                return true;
            }
        }else {
            if(this.state){
                dy = FALLING_SPEED_FAT;
            }else {
                dy = FALLING_SPEED_SLIM;
            }
            return false;
        }
        return false;
    }

    public boolean checkOnFloor(ArrayList<Floor> floors){
        boolean isOnFloor = false;
        for (Floor floor : floors) {
            isOnFloor = checkOnFloor(floor);
        }
        return isOnFloor;
    }

    @Override
    public void paint(Graphics g){
        setBoundary();
        g.drawImage(image, x, y, x + imageWidth, y + imageHeight,
                direction*4*imageWidth + imageWidth*imageOffsetX, imageOffsetY,
                direction*4*imageWidth + imageWidth*imageOffsetX + imageWidth, imageOffsetY + imageHeight, null);
    }
}
