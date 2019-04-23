package character;

import frame.scene.GameScene;

import java.awt.*;

public class Actor extends GameObject{
    public static final int MOVE_DOWN = 0;
    public static final int MOVE_RIGHT = 1;
    public static final int MOVE_UP = 2;
    public static final int MOVE_LEFT = 3;
    private static final int[] MOVING_PATTERN = {0, 1, 2, 3, 2, 1}; // 走路模式

    // 身材速度上限
    public static final int MAX_SPEED_FAT = 40;
    public static final int MAX_SPEED_SLIM = 6;
    // 變換方向初速度
    private static int CHANGE_DIRECTION_INITIAL_SPEED = 2;

    private int direction;
    private int hunger; // 飢餓程度
    private boolean state; // 胖瘦狀態 true:肥 false:瘦
    private boolean isOnFloor; // 當前是否有在階梯上

    // delay
    private int stayDelayCount, stayDelay;

    public Actor(int x, int y, int drawWidth, int drawHeight, String imageName){
        super(x, y, drawWidth, drawHeight, imageName);
        this.direction = MOVE_RIGHT;
        this.hunger = 0;
        this.state = true;
    }

    // getter and setter
    public int getDirection(){
        return this.direction;
    }


    public void reset(){
        this.speedY = 0;
        this.x = 250;
        this.y = 100;
    }

    @Override
    public void update(){
        if (!isOnFloor){
            speedY += GameScene.GRAVITY;
        }
        x += speedX;
        y += speedY;
        setBoundary(); // 更新完座標後，設定邊界
    }

    // 原地踏步
    public void stay(){
        stayDelay = 5; // 設定原地踏步延遲機制
        if(stayDelayCount++ % stayDelay == 0){
            this.imageOffsetX = MOVING_PATTERN[choosingImagesCounter % MOVING_PATTERN.length];
            this.choosingImagesCounter = choosingImagesCounter % MOVING_PATTERN.length;
            this.choosingImagesCounter++;
        }
    }

    public void changeDir(int direction){
        // 轉向後設定速度初值
        if (this.direction != direction){
            switch (direction){
                case MOVE_RIGHT:
                    speedX = CHANGE_DIRECTION_INITIAL_SPEED;
//                    speedX = 0;
                    break;
                case MOVE_LEFT:
                    speedX = -CHANGE_DIRECTION_INITIAL_SPEED;
//                    speedX = 0;
                    break;
            }
        }
        // 變換方向
        this.direction = direction;
        // 設定速度上限
        if (speedX >= MAX_SPEED_FAT){
            speedX = MAX_SPEED_FAT;
            return;
        }
        if (speedX <= -MAX_SPEED_FAT){
            speedX = -MAX_SPEED_FAT;
            return;
        }
        // 持續按壓 加速度
        switch (direction){
            case MOVE_RIGHT:
                speedX += 0.3f;
                break;
            case MOVE_LEFT:
                speedX -= 0.3f;
                break;
        }
    }

    public boolean checkOnFloor(Floor floor){
        // 確認已完全低於此階梯
        // 確認完全走出階梯範圍
        if(this.left > floor.right || this.right < floor.left || this.bottom > floor.bottom){
            isOnFloor = false;
            return false;
        }
        // 於階梯上
        if(this.bottom + speedY > floor.top){
            y = floor.top - drawHeight; // 需修改
            speedY = floor.speedY;
            isOnFloor = true;
            // 人物去碰觸地板，將地板狀態設為被接觸，並由地板觸發機關
            floor.isBeenTouched(this);
            return true;
        }
        isOnFloor = false;
        return false;
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(image, x, y, x + drawWidth, y + drawHeight,
                direction*4* drawWidth + drawWidth*imageOffsetX, imageOffsetY,
                direction*4* drawWidth + drawWidth*imageOffsetX + drawWidth, imageOffsetY + drawHeight, null);
    }
}
