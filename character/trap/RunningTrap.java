package character.trap;

import character.Actor;
import character.Floor;
import util.ResourcesManager;

public class RunningTrap implements Trap {
    public static final int generationRate = 20;

    private static final String[] imagePaths =
            {"RunningFloor_1.png", "RunningFloor_2.png", "RunningFloor_3.png", "RunningFloor_4.png"};
    private boolean rotateDirection; // 轉動的方向 順時針or逆時針
    // 不同轉動方向，不同的選圖模式
    private static final int[] CHOOSING_MODE_CLOCKWISE = {0, 1, 2, 3};
    private static final int[] CHOOSING_MODE_COUNTERCLOCKWISE = {3, 2, 1, 0};
    private float rotateSpeed; // 本身的轉速 影響人物增減的速度

    public RunningTrap(){
        // 轉動的方向
        if ((int)(Math.random()*100) % 2 == 0){
            rotateDirection = true; // 順時針
        }else {
            rotateDirection = false; // 逆時針
        }
        // 預設為轉速0.2
        this.rotateSpeed = 2;
    }

    public RunningTrap(int rotateSpeed){
        this();
        // 也可以自行設定轉速
        this.rotateSpeed = rotateSpeed;
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
        floor.setDrawWidth(floor.getFloorImages().get(0).getWidth());
        floor.setDrawHeight(floor.getFloorImages().get(0).getHeight());
        floor.setDrawingDelay(3);
    }

    @Override
    public void execute(Actor player, Floor floor) {
        // 待修改
    }
}
