package characters.rule;

import characters.Actor;
import characters.floor.Floor;
import resource.util.ResourcesManager;

public class StoneRule implements Rule{
    private static final String[] imagePaths = {"src/resources/StoneFloor_0.png", "src/resources/StoneFloor_1.png"};
    private static final int[] choosingImagesMode = {0, 1};

    @Override
    public void setFloorState(Floor floor) {
        for (int i = 0; i < imagePaths.length; i++) {
            floor.getFloorImages().add(ResourcesManager.getInstance().getImage(imagePaths[i]));
        }
        floor.imageWidth = floor.drawWidth = floor.getFloorImages().get(0).getWidth();
        floor.imageHeight = floor.drawHeight = floor.getFloorImages().get(0).getHeight();
        floor.setChoosingImagesMode(choosingImagesMode);
        floor.speedY = 0;
        floor.setBoundary();
        floor.delay = 5;
    }

    @Override
    public void execute(Floor floor, Actor player) {

    }
}
