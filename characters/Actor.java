package characters;

import java.awt.*;

public class Actor extends GameObject{
    public static final int MOVE_DOWN = 0;
    public static final int MOVE_RIGHT = 1;
    public static final int MOVE_UP = 2;
    public static final int MOVE_LEFT = 3;
    public static final int[] MOVING_PATTERN = {0, 1, 2, 3, 2, 1}; // 走路模式
    public static int FALLING_SPEED = 8;
    public static int MOVING_SPEED = 8;

    private int direction;
    private int movingAction;

    public Actor(){

    }

    public Actor(int x, int y, int imageWidth, int imageHeight){
        super(x, y, imageWidth, imageHeight);
        direction = MOVE_DOWN;
    }

    public void move(){
        x += dx;
        dy = FALLING_SPEED;
        y += dy;
        stay();
        setBoundary();
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

    @Override
    public void paint(Graphics g){
        g.drawImage(image, x, y, x + imageWidth, y + imageHeight,
                direction*4*imageWidth + imageWidth*imageOffsetX, imageOffsetY,
                direction*4*imageWidth + imageWidth*imageOffsetX + imageWidth, imageOffsetY + imageHeight, null);
    }
}
