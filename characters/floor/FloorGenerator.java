package characters.floor;

import frame.GameFrame;
import resource.util.ResourcesManager;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FloorGenerator {
    private ArrayList<BufferedImage> floorImages;
    private int FLOOR_NORMAL = 0;
    private int FLOOR_STONE = 1;
    private int FLOOR_FRAGMENT = 2;
    private int FLOOR_RUNNING = 3;
    private int[] FLOOR_TYPE = {FLOOR_NORMAL, FLOOR_STONE};

    public FloorGenerator(){
        floorImages = new ArrayList<>();
//        floorImages.add(ResourcesManager.getInstance().getImage("src/resources/Floor1.png"));
        floorImages.add(ResourcesManager.getInstance().getImage("src/resources/Floor1.png"));
        floorImages.add(ResourcesManager.getInstance().getImage("src/resources/StoneRule.png"));
    }

    // 判定 新生成階梯與原所有階梯是否有重疊
    // 若有 新生成階梯 生於該階梯下
//    public Floor genFloor(ArrayList<Floor> floors){
//        Floor lastFloor;
//        Floor newFloor = new Floor(getRandom(64, GameFrame.FRAME_WIDTH) - 64, getRandom(GameFrame.FRAME_HEIGHT, GameFrame.FRAME_HEIGHT + 100), 64, 16, "src/resources/Floor1.png");
//        boolean isCollision;
//        for (int i = 0; i < floors.size(); i++) {
//            isCollision = checkCollision(newFloor, floors.get(i));
//            if(isCollision){
//                lastFloor = floors.get(i);
//                newFloor = genFloor(lastFloor);
//            }
//        }
//        return newFloor;
//    }

    // 生成階梯必在傳入階梯底下 (機制須修改)
//    public Floor genFloor(Floor floor){
//        Floor newFloor = new Floor(getRandom(64, GameFrame.FRAME_WIDTH) - 64, floor.bottom + getRandom(0, 50), 64, 16, "src/resources/Floor1.png");
//        if (checkCollision(Brick, newFloor)){
//            newFloor = new Floor(getRandom(64, GameFrame.FRAME_WIDTH) - 64, Brick.bottom + getRandom(-50, 50), 64, 16, "src/resources/Floor1.png");
//        }
//        return newFloor;
//    }

    // (預計) 傳入 畫面大小 判定畫面中至少有一定數量階梯 並生成至一定數量
//    public Floor genFloor(JPanel panel, ArrayList<Floor> floors){
//        int count = 0;
//        for (int i = 0; i < floors.size(); i++) {
//            if(floors.get(i).top > 0 && floors.get(i).bottom < panel.getHeight()){
//                count++;
//            }
//        }
//        return genFloor(floors);
//    }

    // 簡易 階梯碰撞判定
    public boolean checkCollision(Floor floor, Floor target){
        boolean isCollision = false;
//        int nextTop = Brick.top + Brick.speedY, nextBottom = Brick.bottom + Brick.speedY, nextLeft = Brick.left + Brick.speedX, nextRight = Brick.right + Brick.speedX;
        if(floor.top < target.bottom){
            isCollision = true;
//            if(Brick.bottom > target.top){

//                if(Brick.left < target.right){
//                    if (Brick.right > target.left){
//
//                    }
//                }
//            }
        }
        return isCollision;
    }

    public int floorChooser(){
        // 生成的地板種類
        // 有無達到地板的生成機率
        // 無就生 普通地板

        return 0;
    }

    public static int getRandom(int min, int max){
        return (int)(Math.random()*(max - min + 1) + min);
    }
}
