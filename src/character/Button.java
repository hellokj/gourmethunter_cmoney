package character;

import util.ResourcesManager;

import java.awt.*;

public class Button extends AnimationGameObject {

    public Button(int x, int y, int drawWidth, int drawHeight,int imageWidth, int imageHeight, String imagePath){
        super(x, y, drawWidth, drawHeight, imageWidth, imageHeight, imagePath);
        this.image = ResourcesManager.getInstance().getImage(imagePath);
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(image, x, y, x+drawWidth, y+drawHeight,
                imageOffsetX*imageWidth, 0, imageOffsetX*imageWidth + imageWidth, imageHeight
                ,null);
    }
}
