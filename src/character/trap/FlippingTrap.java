package character.trap;

import character.Actor;
import character.Floor;
import frame.scene.Scene;
import util.ResourcesManager;

public class FlippingTrap implements Trap {
    private final String[] imagePaths = {"floor/FlippingFloor_1.png", "floor/FlippingFloor_2.png"};
    private static final int[] CHOOSING_IMAGES_MODE_FLIP = {1};
    public static final int[] CHOOSING_IMAGES_MODE_BASE = {0};
    public static boolean FlipState;//是否翻轉
    private int executeDelayCount;

    @Override
    public void setFloorState(Floor floor) {
        floor.setImage(ResourcesManager.getInstance().getImage("floor/FlippingFloor_1.png"));
        for (int i = 0; i < imagePaths.length; i++) {
            floor.getFloorImages().add(ResourcesManager.getInstance().getImage(imagePaths[i]));
        }
        // 設定基礎圖寬高
        floor.setDrawWidth(floor.getFloorImages().get(0).getWidth());
        floor.setDrawHeight(floor.getFloorImages().get(0).getHeight());
        // 設定選圖模式
        floor.setChoosingImagesMode(CHOOSING_IMAGES_MODE_BASE);
        // 繪製動畫延遲
        floor.setDrawingDelay(20);
        executeDelayCount = 0;
        FlipState = false;
    }

    @Override
    public void execute(Actor player, Floor floor, Scene scene) {
        // 飢餓值小於40，踩到翻轉，翻後掉落
        if (++executeDelayCount == 5 && player.getHunger() < 40){
                floor.setChoosingImagesMode(CHOOSING_IMAGES_MODE_FLIP);
                player.setY(player.getY()+30);
                executeDelayCount = 0;
                FlipState = true;
        }
    }
}
