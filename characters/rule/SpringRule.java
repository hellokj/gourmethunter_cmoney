package characters.rule;

import characters.Actor;
import characters.floor.Floor;
import resource.util.ResourcesManager;

public class SpringRule implements Rule{
    private static final String[] imagePaths = {"src/resources/SpringFloor_0.png", "src/resources/SpringFloor_1.png", "src/resources/SpringFloor_2.png", "src/resources/SpringFloor_3.png"};
    private static final int[] choosingImagesMode = {1, 2, 3, 3, 3, 2};
    private static final int[] choosingImagesModeBase = {1};

    @Override
    public void setFloorState(Floor floor) {
        for (int i = 0; i < imagePaths.length; i++) {
            floor.getFloorImages().add(ResourcesManager.getInstance().getImage(imagePaths[i]));
        }
        floor.imageWidth = floor.drawWidth = ResourcesManager.getInstance().getImage(imagePaths[0]).getWidth();
        floor.imageHeight = floor.drawHeight = ResourcesManager.getInstance().getImage(imagePaths[0]).getHeight();
//        floor.setChoosingImagesMode(choosingImagesMode);
        floor.setChoosingImagesMode(choosingImagesModeBase);
        floor.speedY = 0;
        floor.setBoundary();
    }

    @Override
    public void execute(Floor floor, Actor player) {
        if (floor.isTriggered()){
            floor.setChoosingImagesMode(choosingImagesMode);
            floor.setDrawing(choosingImagesMode.length);
        }
    }
}
