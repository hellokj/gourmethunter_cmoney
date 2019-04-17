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
    private int[] FLOOR_TYPE = {FLOOR_NORMAL, FLOOR_STONE};

    public FloorGenerator(){
        floorImages = new ArrayList<>();
//        floorImages.add(ResourcesManager.getInstance().getImage("src/resources/Floor1.png"));
        floorImages.add(ResourcesManager.getInstance().getImage("src/resources/Floor1.png"));
        floorImages.add(ResourcesManager.getInstance().getImage("src/resources/StoneFloor.png"));
    }

    public Floor genFloor(ArrayList<Floor> floors){
        Floor lastFloor;
        Floor newFloor = new Floor(getRandom(64, GameFrame.FRAME_WIDTH) - 64, getRandom(GameFrame.FRAME_HEIGHT, GameFrame.FRAME_HEIGHT + 100), 64, 16, "src/resources/Floor1.png");
        boolean isCollision;
        for (int i = 0; i < floors.size(); i++) {
            isCollision = checkCollision(newFloor, floors.get(i));
            if(isCollision){
                lastFloor = floors.get(i);
                newFloor = genFloor(lastFloor);
            }
        }
        return newFloor;
    }

    public Floor genFloor(Floor floor){
//        System.out.println(floor.bottom);
        Floor newFloor = new Floor(getRandom(64, GameFrame.FRAME_WIDTH) - 64, floor.bottom + getRandom(0, 50), 64, 16, "src/resources/Floor1.png");
//        if (checkCollision(floor, newFloor)){
//            newFloor = new Floor(getRandom(64, GameFrame.FRAME_WIDTH) - 64, floor.bottom + getRandom(-50, 50), 64, 16, "src/resources/Floor1.png");
//        }
        return newFloor;
    }

    public Floor genFloor(JPanel panel, ArrayList<Floor> floors){
        int count = 0;
        for (int i = 0; i < floors.size(); i++) {
            if(floors.get(i).top > 0 && floors.get(i).bottom < panel.getHeight()){
                count++;
            }
        }
        return genFloor(floors);
    }

    public boolean checkCollision(Floor floor, Floor target){
        boolean isCollision = false;
//        int nextTop = floor.top + floor.dy, nextBottom = floor.bottom + floor.dy, nextLeft = floor.left + floor.dx, nextRight = floor.right + floor.dx;
        if(floor.top < target.bottom){
            isCollision = true;
//            if(floor.bottom > target.top){

//                if(floor.left < target.right){
//                    if (floor.right > target.left){
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
