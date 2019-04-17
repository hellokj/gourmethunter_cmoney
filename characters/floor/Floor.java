package characters.floor;

import characters.GameObject;
import characters.rule.MovementRule;
import resource.util.ResourcesManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Floor extends GameObject {
    public static int RISING_SPEED = -4;
    private int generationRate;
    private MovementRule movementRule;
    private static BufferedImage image = ResourcesManager.getInstance().getImage("src/resources/Floor1.png");


    public Floor(){

    }

    public Floor(int x, int y, int imageWidth, int imageHeight){
        super(x, y, imageWidth, imageHeight);
        dy = -(int)(Math.random()*4 + 1);
//        dy = -1;
    }

    public Floor(int x, int y, int imageWidth, int imageHeight, String imagePath){
        this(x, y, imageWidth, imageHeight);
        setImage(imagePath);
    }

    public void rise(){
        y += dy;
        setBoundary();
    }

    public boolean checkTopBoundary(){
        if (bottom < 0){
            return true;
        }
        return false;
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(image, x, y, x + imageWidth, y + imageHeight, 0, 0, imageWidth, imageHeight, null);
    }
}
