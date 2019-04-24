package character.trap;

import character.Actor;
import character.Floor;

public class DancingTrap implements Trap {
    public static final int generationRate = 50;

    @Override
    public void setFloorState(Floor floor) {

    }

    @Override
    public void execute(Actor player, Floor floor) {
        // 先記錄角色原始狀態
        // 將角色設為不能移動
        // 輸入完成後再回復原始狀態
    }
}
