package character.trap;

import character.Actor;
import character.Floor;
import util.ResourcesManager;

import java.awt.image.BufferedImage;

public class NormalTrap implements Trap {
    private static String imagePath = "Floor1.png";
    private static BufferedImage image = ResourcesManager.getInstance().getImage(imagePath);
    private static final int[] choosingImagesMode = {0};

    @Override
    public void setFloorState(Floor floor) {
        floor.getFloorImages().add(image);
        floor.setChoosingImagesMode(choosingImagesMode);
        floor.setDrawWidth(image.getWidth());
        floor.setDrawHeight(image.getHeight());
    }

    @Override
    public void execute(Actor player, Floor floor) {

    }
}
