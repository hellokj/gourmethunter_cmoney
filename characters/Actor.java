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
//    public static final int[] MOVING_PATTERN = {0, 1, 2, 1}; // 走路模式
    public static final int GRAVITY = 3;
    public static int MOVING_SPEED_FAT = 2;
    public static int MOVING_SPEED_SLIM = 2;

    private int hunger; // 飢餓程度
    private boolean state; // 胖瘦狀態 true:肥 false:瘦
    private int imageDelayCount, imageDelay; // 畫圖延遲

    public Actor(){

    }

    public Actor(int x, int y, int imageWidth, int imageHeight){
        super(x, y, imageWidth, imageHeight, imageWidth, imageHeight);
        direction = MOVE_DOWN;
        dy = GRAVITY;
        dx = 0;
        hunger = 0;
        delay = 5;
        imageDelayCount = 0;
        imageDelay = 5;
        state = false;
    }

    public void reset(){
        dy = GRAVITY;
    }

    @Override
    public void move(){
        stay();
        if (direction == MOVE_RIGHT){
            x += dx;
        }
        if (direction == MOVE_LEFT){
            x -= dx;
        }
    }

    public void fall(){
        y += dy;
    }

    public void changeDir(int direction){
        if (this.direction != direction){
            this.direction = direction;
            this.choosingImagesCounter = 0;
        }
    }

    // 停止狀態 原地踏步
    public void stay(){
        if(++imageDelayCount % imageDelay == 0) {
            this.imageOffsetX = MOVING_PATTERN[choosingImagesCounter % MOVING_PATTERN.length];
            this.choosingImagesCounter = choosingImagesCounter % MOVING_PATTERN.length;
            this.choosingImagesCounter++;
        }
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

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean checkOnFloor(Floor floor){
        // 確認已完全低於此階梯
        if(this.bottom > floor.bottom){
            return false;
        }
        // 確認完全走出階梯範圍
        if(this.left > floor.right || this.right < floor.left){
            return false;
        }
        // 於階梯上
        if(this.bottom + GRAVITY > floor.top){
            y = floor.top - imageHeight; // 需修改
            dy = floor.dy;
            return true;
        }
        return false;
    }

    // 傳入地圖中所有階梯，判斷角色有無在階梯上
    public boolean checkOnFloor(ArrayList<Floor> floors){
        boolean isOnFloor = false;
        for (Floor floor : floors) {
            isOnFloor = checkOnFloor(floor);
            if (isOnFloor){
                floor.execute(this);
            }
        }
        // 不在階梯上，回復重力
        if (!isOnFloor){
            this.reset();
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
