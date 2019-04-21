package characters.rule;

import characters.Actor;
import characters.floor.Floor;
import resource.util.ResourcesManager;

import java.awt.image.BufferedImage;

public class NormalRule implements Rule {
    private static String imagePath = "src/resources/Floor1.png";
    private static BufferedImage image = ResourcesManager.getInstance().getImage(imagePath);
    private static final int[] choosingImagesMode = {0};
    // 還需加上移動模式 固定不動 or 左右來回移動

    @Override
    public void setFloorState(Floor floor) {
        floor.getFloorImages().add(image);
        floor.setChoosingImagesMode(choosingImagesMode);
        floor.imageWidth = floor.drawWidth = floor.getFloorImages().get(0).getWidth();
        floor.imageHeight = floor.drawHeight = floor.getFloorImages().get(0).getHeight();
        floor.speedY = 0;
        floor.setBoundary();
    }

    @Override
    public void execute(Floor floor, Actor player) {

    }
}
