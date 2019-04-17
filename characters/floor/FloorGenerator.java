package characters.floor;

import frame.GameFrame;
import resource.util.ResourcesManager;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FloorGenerator {
    private ArrayList<BufferedImage> floorImages;

    public FloorGenerator(){
        floorImages = new ArrayList<>();
//        floorImages.add(ResourcesManager.getInstance().getImage("src/resources/Floor1.png"));
        floorImages.add(ResourcesManager.getInstance().getImage("src/resources/Floor1.png"));
    }

    public Floor genFloor(ArrayList<Floor> floors){
        Floor newFloor = new Floor(getRandom(64, GameFrame.FRAME_WIDTH) - 64, getRandom(GameFrame.FRAME_HEIGHT, GameFrame.FRAME_HEIGHT + 100), 64, 16, "src/resources/Floor1.png");
        boolean isCollision = false;
        for (int i = 0; i < floors.size(); i++) {
            isCollision = checkCollision(newFloor, floors.get(i));
        }
//        if
        return newFloor;
    }

    public Floor genFloor(){
        Floor newFloor = new Floor(getRandom(64, GameFrame.FRAME_WIDTH) - 64, getRandom(GameFrame.FRAME_HEIGHT, GameFrame.FRAME_HEIGHT + 100), 64, 16, "src/resources/Floor1.png");
        return newFloor;
    }

    public boolean checkCollision(Floor floor, Floor target){
        boolean isCollision = false;
        int nextTop = floor.top, nextBottom = floor.bottom, nextLeft = floor.left, nextRight = floor.right;
        if(nextTop < target.bottom){
            if(nextBottom > target.top){
                if(nextLeft < target.right){
                    if (nextRight > target.left){
                        isCollision = true;
                    }
                }
            }
        }
        return isCollision;
    }

    public static int getRandom(int min, int max){
        return (int)(Math.random()*(max - min + 1) + min);
    }
}
