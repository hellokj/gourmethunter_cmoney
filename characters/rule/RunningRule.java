package characters.rule;

import characters.Actor;
import characters.floor.Floor;
import resource.util.ResourcesManager;

public class RunningRule implements Rule {
    private static final String[] imagePaths = {"src/resources/RunningFloor_1.png", "src/resources/RunningFloor_2.png", "src/resources/RunningFloor_3.png", "src/resources/RunningFloor_4.png"};
//    private static final String imagePath = "src/resources/RunningFloor.png";

    private boolean rotateDirection; // 轉動的方向 順時針or逆時針
    // 不同轉動方向，不同的選圖模式
    private static final int[] CHOOSING_MODE_CLOCKWISE = {0, 1, 2, 3};
    private static final int[] CHOOSING_MODE_COUNTERCLOCKWISE = {3, 2, 1, 0};
    private int rotateSpeed; // 本身的轉速 影響人物增減的速度


    public RunningRule(int rotateSpeed){
        this.rotateSpeed = rotateSpeed;
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
        for (int i = 0; i < imagePaths.length; i++) {
            floor.getFloorImages().add(ResourcesManager.getInstance().getImage(imagePaths[i]));
        }
        // 不同轉動方向，不同的選圖模式
        if(rotateDirection){ // 順時針
            floor.setChoosingImagesMode(CHOOSING_MODE_CLOCKWISE);
        }else { // 逆時針
            floor.setChoosingImagesMode(CHOOSING_MODE_COUNTERCLOCKWISE);
        }
        floor.imageWidth = floor.drawWidth = floor.getFloorImages().get(0).getWidth();
        floor.imageHeight = floor.drawHeight = floor.getFloorImages().get(0).getHeight();

        floor.speedY = 0;
        floor.setBoundary();
    }

    @Override
    public void execute(Floor floor, Actor player) {
        if(rotateDirection){ // 順時針
            // 方式一：將人物往右移
            player.x += rotateSpeed;
            // 方式二：將人物速度減少(失敗)
//            if(player.getDirection() == Actor.MOVE_LEFT){
//                player.speedX -= rotateSpeed;
//            }else if(player.getDirection() == Actor.MOVE_RIGHT){
//                player.speedX += rotateSpeed;
//            }
        }else { // 逆時針
            // 方式一：將人物往左移
            player.x -= rotateSpeed;
            // 方式二：將人物速度減少(失敗)
//            if(player.getDirection() == Actor.MOVE_LEFT){
//                player.speedX += rotateSpeed;
//            }else if(player.getDirection() == Actor.MOVE_RIGHT){
//                player.speedX -= rotateSpeed;
//            }
        }
    }
}
