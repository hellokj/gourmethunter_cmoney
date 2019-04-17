package characters.floor;

import characters.GameObject;
import characters.rule.MovementRule;

import java.awt.*;

public class Floor extends GameObject {
    public static int RISING_SPEED = -4;
    private int generationRate;
    private MovementRule movementRule;


    public Floor(){

    }

    public Floor(int x, int y, int imageWidth, int imageHeight){
        super(x, y, imageWidth, imageHeight);
//        dy = RISING_SPEED;
        dy = -1;
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
