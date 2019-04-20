package characters.rule;

import characters.Actor;
import characters.floor.Floor;
import resource.util.ResourcesManager;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class RunningRule implements Rule {
    private static final String[] imagePaths = {"src/resources/Floor1.png"};

    private static ArrayList<BufferedImage> images;
    private boolean rotateDirection; // 轉動的方向 順時針or逆時針
    // 不同轉動方向，不同的選圖模式
    private static final int[] CHOOSING_MODE_CLOCKWISE = {0};
    private static final int[] CHOOSING_MODE_COUNTERCLOCKWISE = {0};
    private int rotateSpeed; // 本身的轉速 影響人物增減的速度

    private boolean hadBeenExecute; // 記錄有無被觸發過 只會觸發一次

    public RunningRule(int rotateSpeed){
        this.rotateSpeed = rotateSpeed;
        this.hadBeenExecute = false;
        // 跑步機的用圖
        images = new ArrayList<>();
        for (int i = 0; i < imagePaths.length; i++) {
            images.add(ResourcesManager.getInstance().getImage(imagePaths[i]));
        }
        // 轉動的方向
        if ((int)(Math.random()*100) % 2 == 0){
            rotateDirection = true; // 順時針
        }else {
            rotateDirection = false; // 逆時針
        }
    }

    @Override
    public void setFloorState(Floor floor) {
        // 設定圖片
        floor.setFloorImages(images);
        // 不同轉動方向，不同的選圖模式
        if(rotateDirection){ // 順時針
            floor.setChoosingImagesMode(CHOOSING_MODE_CLOCKWISE);
        }else { // 逆時針
            floor.setChoosingImagesMode(CHOOSING_MODE_COUNTERCLOCKWISE);
        }
        floor.imageWidth = floor.drawWidth = floor.getFloorImages().get(0).getWidth();
        floor.imageHeight = floor.drawHeight = floor.getFloorImages().get(0).getHeight();
        floor.dy = 0;
        floor.setBoundary();
    }

    @Override
    public void execute(Floor floor, Actor player) {
        if(rotateDirection){ // 順時針
            // 方式一：將人物往右移
            player.x += rotateSpeed;
            // 方式二：將人物速度減少
//            if(player.getDirection() == Actor.MOVE_LEFT){
//                player.speedX -= rotateSpeed;
//            }else if(player.getDirection() == Actor.MOVE_RIGHT){
//                player.speedX += rotateSpeed;
//            }
        }else { // 逆時針
            // 方式一：將人物往左移
            player.x -= rotateSpeed;
            // 方式二：將人物速度減少
//            if(player.getDirection() == Actor.MOVE_LEFT){
//                player.speedX += rotateSpeed;
//            }else if(player.getDirection() == Actor.MOVE_RIGHT){
//                player.speedX -= rotateSpeed;
//            }
        }
    }
}
