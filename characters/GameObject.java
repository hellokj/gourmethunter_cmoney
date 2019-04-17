package characters;

import frame.GameFrame;
import resource.util.ResourcesManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {
    
    public int x, y; // 圖片座標
    public int imageWidth, imageHeight; // 圖片長寬
    public int imageOffsetX, imageOffsetY; // 選擇圖片 偏移量
    public int dx, dy; // 方向速度
    public int top, bottom, left, right; // 圖片本身邊界
    public int delayCount;
    public int delay;
    public BufferedImage image; // 圖片

    public GameObject(){

    }

    public GameObject(int x, int y, int imageWidth, int imageHeight){
        this.x = x;
        this.y = y;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        setBoundary();
    }

    public void move(){
        x += dx;
        y += dy;
        setBoundary();
    }

    public boolean delay(){
        System.out.println(delayCount);
        if(delayCount == delay){
            delayCount = 0;
            return true;
        }else {
            delayCount++;
            return false;
        }
    }

    public void setImage(String imagePath){
        this.image = ResourcesManager.getInstance().getImage(imagePath);
    }

    public void setBoundary(){
        this.top = y;
        this.bottom = y + imageHeight;
        this.left = x;
        this.right = x + imageWidth;
    }

    public boolean checkLeftRightBoundary(JPanel panel){
        int nextRight = right + dx;
        int nextLeft = left + dx;
        if (nextLeft < 0 || nextRight > panel.getWidth()){
            dx = 0;
            return true;
        }
        return false;
    }

    public void paint(Graphics g){
        g.drawImage(image, 0, 0, imageWidth, imageHeight, 0, 0, image.getWidth(), image.getHeight(), null);
    }
}