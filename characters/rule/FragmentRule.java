package characters.rule;

import characters.Actor;
import characters.floor.Floor;
import resource.util.ResourcesManager;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FragmentRule implements Rule {
    private static final String imagePath = "src/resources/f_1.png";
    private static final BufferedImage brick = ResourcesManager.getInstance().getImage(imagePath);
    private static ArrayList<BufferedImage> bricks; // 預計掉落的四塊brick

    private static final int  HUNGER_LIMIT = 25; // 體重超過，開始掉落
    private static final int  TIME_LIMIT = 5; // 站立時間超過5次刷新，開始掉落
    private static final int FAIIING_DELAY = 3; // 掉落3次刷新後，移除

    public FragmentRule(){
        bricks = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            bricks.add(brick);
        }
    }

    @Override
    public void setFloorState(Floor floor) {
        floor.setFloorImages(bricks); // 預計掉落的四塊brick
        floor.dy = 0;
        floor.setBoundary();
    }

    @Override
    public void execute(Floor floor, Actor player) {

    }
}
