package character.trap;

import character.Actor;
import character.Floor;
import util.ResourcesManager;

public class FragmentTrap implements Trap {
    public static final int generationRate = 25;

    private static final String[] imagePaths = {"floor/F_1.png"};    
    
    private static final int[] choosingImagesMode = {0};
    
    private int state = 0;

    @Override
    public void setFloorState(Floor floor) {
        for (int i = 0; i < imagePaths.length; i++) {
            floor.getFloorImages().add(ResourcesManager.getInstance().getImage(imagePaths[i]));
        }
        floor.setChoosingImagesMode(choosingImagesMode);
        floor.setDrawWidth(ResourcesManager.getInstance().getImage(imagePaths[0]).getWidth());
        floor.setDrawHeight(ResourcesManager.getInstance().getImage(imagePaths[0]).getHeight());
    }

    @Override
    public void execute(Actor player, Floor floor) {
        // 判定角色狀態or站立時間
        // 判定碰觸到的是哪塊
        // 從那塊開始掉落->再來是那塊的兩側->最後一塊掉落
        if (floor.isTriggered()){
            floor.setFragmentState(state++);
            //System.out.println(fragmentCount);
            if(state==5){
                floor.setSpeedY(3);
            }
        }
    }
}
