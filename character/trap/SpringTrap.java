package character.trap;

import character.Actor;
import character.Floor;
import util.ResourcesManager;

public class SpringTrap implements Trap {
    public static final int generationRate = 25;

    private static final String[] imagePaths =
            {"SpringFloor_0.png", "SpringFloor_1.png", "SpringFloor_2.png", "SpringFloor_3.png"};
    private static final int[] choosingImagesMode = {1, 2, 3, 3, 3, 2};
    private static final int[] choosingImagesModeBase = {1};

    @Override
    public void setFloorState(Floor floor) {
        for (int i = 0; i < imagePaths.length; i++) {
            floor.getFloorImages().add(ResourcesManager.getInstance().getImage(imagePaths[i]));
        }
        floor.setDrawWidth(ResourcesManager.getInstance().getImage(imagePaths[0]).getWidth());
        floor.setDrawHeight(ResourcesManager.getInstance().getImage(imagePaths[0]).getHeight());
        floor.setChoosingImagesMode(choosingImagesModeBase);
        floor.setImage(ResourcesManager.getInstance().getImage(imagePaths[0]));
        floor.setDrawingDelay(6);
    }

    @Override
    public void execute(Actor player, Floor floor) {
        if (floor.isTriggered()){
            floor.setChoosingImagesMode(choosingImagesMode);
            // 彈簧機制
            // 依照現在畫的圖的高度做偏移
            player.setY(floor.getY() + floor.getDrawHeight() - floor.getCurrentDrawingImage().getHeight() - player.getDrawHeight());
            player.setSpeedY(-10);
        }
    }
}
