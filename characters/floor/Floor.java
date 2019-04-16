package characters.floor;

import characters.GameObject;
import characters.rule.MovementRule;

import java.awt.*;

public class Floor extends GameObject {
    private int generationRate;
    private MovementRule movementRule;

    public Floor(){

    }

    public Floor(int x, int y, int imageWidth, int imageHeight){
        super(x, y, imageWidth, imageHeight);
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(image, )
    }
}
