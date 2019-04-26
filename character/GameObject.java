package character;

import util.ResourcesManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {
    protected int x, y; // 圖片座標
    protected int top, bottom, left, right; // 圖片本身邊界
    // 適用於 不同圖
    protected int drawWidth, drawHeight; // 畫出來的長寬
    // 適用於 同張圖
    protected int imageOffsetX, imageOffsetY; // 選擇圖片 偏移量
    protected BufferedImage image; // 圖片

    protected float speedX, speedY; // x, y軸速度

    protected int choosingImagesCounter; // 用來選圖的計數器

    public GameObject(){

    }

    public GameObject(int x, int y){
        this.x = x;
        this.y= y;
    }

    public GameObject(int x, int y, int drawWidth, int drawHeight){
        this(x, y);
        this.drawWidth = drawWidth;
        this.drawHeight = drawHeight;
        setBoundary(); // 生成即設定此圖邊界
    }

    public GameObject(int x, int y, int drawWidth, int drawHeight, String imageName){
        this(x, y, drawWidth, drawHeight);
        this.image = ResourcesManager.getInstance().getImage(imageName);
    }

    // 更新每次座標
    protected void update(){
        setBoundary();
    }

    public void setBoundary(){
        this.top = y;
        this.bottom = y + drawHeight;
        this.left = x;
        this.right = x + drawWidth;
    }

    // 碰撞檢測
    public boolean checkCollision(GameObject gameobject){
        if(this.left > gameobject.right){
            return false;
        }
        if(this.right < gameobject.left){
            return false;
        }
        if(this.bottom < gameobject.top){
            return false;
        }
        if(this.top > gameobject.bottom){
            return false;
        }
        return true;
    }

    // getter and setter
    public int getDrawWidth() {
        return drawWidth;
    }
    public void setDrawWidth(int drawWidth) {
        this.drawWidth = drawWidth;
    }
    public int getDrawHeight() {
        return drawHeight;
    }
    public void setDrawHeight(int drawHeight) {
        this.drawHeight = drawHeight;
    }
    public int getTop() {
        return top;
    }
    public void setTop(int top) {
        this.top = top;
    }
    public int getBottom() {
        return bottom;
    }
    public void setBottom(int bottom) {
        this.bottom = bottom;
    }
    public int getLeft() {
        return left;
    }
    public void setLeft(int left) {
        this.left = left;
    }
    public int getRight() {
        return right;
    }
    public void setRight(int right) {
        this.right = right;
    }
    public float getSpeedX() {
        return speedX;
    }
    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }
    public float getSpeedY() {
        return speedY;
    }
    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setImage(BufferedImage image){
        this.image = image;
    }
    public int getImageOffsetX() {
        return imageOffsetX;
    }
    public void setImageOffsetX(int imageOffsetX) {
        this.imageOffsetX = imageOffsetX;
    }
    public int getImageOffsetY() {
        return imageOffsetY;
    }
    public void setImageOffsetY(int imageOffsetY) {
        this.imageOffsetY = imageOffsetY;
    }

    public void paint(Graphics g){
        g.drawImage(image, x, y, x + drawWidth, y + drawHeight, 0, 0, image.getWidth(), image.getHeight(), null);
    }
}
