package characters;

import resource.util.ResourcesManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {
    
    public int x, y; // 圖片座標
    public int imageWidth, imageHeight; // 圖片長寬
    public int drawWidth, drawHeight; // 畫出來的長寬
    public int imageOffsetX, imageOffsetY; // 選擇圖片 偏移量
    public int speedX, speedY; // 方向速度
    public int top, bottom, left, right; // 圖片本身邊界
    public int delayCount, delay; // 延遲
    public BufferedImage image; // 圖片
    public int direction; // 方向

    public int choosingImagesCounter; // 用來選圖的計數器

    public GameObject(){

    }

    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
    }

    public GameObject(int x, int y, int imageWidth, int imageHeight, int drawWidth, int drawHeight){
        this.x = x;
        this.y = y;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.drawWidth = drawWidth;
        this.drawHeight = drawHeight;
        setBoundary(); // 生成即設定此圖邊界
    }

    public void move(){
        x += speedX;
    }

    // 計算物件延遲 達到延遲才做事，並將本身延遲設回０
    public boolean delay(){
//        System.out.println(delayCount);
        if(delayCount >= delay){
            delayCount = 0;
            return true;
        }else {
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
        int nextRight = right;
        int nextLeft = left;
        if (direction == Actor.MOVE_RIGHT){
            nextRight = right + speedX;
        }else {
            nextLeft = left - speedX;
        }
        if (nextLeft < 0 || nextRight > panel.getWidth()){
            speedX = 0;
            return true;
        }
        return false;
    }

    public void paint(Graphics g){
        delayCount++;
        setBoundary();
        g.drawImage(image, x, y, drawWidth, drawHeight, x, y, imageWidth, imageHeight, null);
    }
}