package characters.floor;

import characters.Actor;
import characters.GameObject;
import characters.rule.MovementMode;
import characters.rule.Rule;
import resource.util.ResourcesManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Floor extends GameObject {
    private Rule functionRule;
    private ArrayList<BufferedImage> floorImages; // 統一圖片回傳類型(處理地板有動畫的機制，取代GameObject只存一張圖)
    private int[] choosingImagesMode; // 依照回傳的選圖模式來印出不同圖片

    private boolean hadBeenExecuted;


    // 建構地板 參數
    public Floor(int x, int y, Rule rule){
        super(x, y);
        floorImages = new ArrayList<>();
        functionRule = rule;
        executeRule(rule); // 建構地板時，即將其屬性設定
        hadBeenExecuted = false;
    }

    public Floor(int x, int y, int imageWidth, int imageHeight, Rule rule, MovementMode movementMode){
        super(x, y, imageWidth, imageHeight, imageWidth, imageHeight);
//        dy = -1;
        executeRule(rule); // 建構地板時，即將其屬性設定
//        movementMode = movementMode;
    }

    public Floor(int x, int y, int imageWidth, int imageHeight){
        super(x, y, imageWidth, imageHeight, imageWidth, imageHeight);
        dy = -(int)(Math.random()*4 + 1);
    }

    public Floor(int x, int y, int imageWidth, int imageHeight, String imagePath){
        this(x, y, imageWidth, imageHeight);
        setImage(imagePath);
    }

    public void stay(){
        this.choosingImagesCounter = choosingImagesCounter % choosingImagesMode.length;
        this.choosingImagesCounter++;
    }

    public ArrayList<BufferedImage> getFloorImages() {
        return floorImages;
    }

    public void setFloorImages(ArrayList<BufferedImage> floorImages) {
        this.floorImages = floorImages;
    }

    public Rule getFunctionRule() {
        return functionRule;
    }

    public void setFunctionRule(Rule functionRule) {
        this.functionRule = functionRule;
    }

    public int[] getChoosingImagesMode() {
        return choosingImagesMode;
    }

    public void setChoosingImagesMode(int[] choosingImagesMode) {
        this.choosingImagesMode = choosingImagesMode;
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

    public boolean checkOn(Actor player){
        return player.checkOnFloor(this);
    }

    // 根據傳入機制 來實行不同地板
    private void executeRule(Rule rule){
        rule.setFloorState(this);
    }

    public void execute(Actor player){
        this.functionRule.execute(this, player);
    }

    @Override
    public void paint(Graphics g){
//        System.out.println();
//        choosingImagesMode[choosingImagesCounter];
        g.drawImage(floorImages.get(0), x, y, x + imageWidth, y + imageHeight, 0, 0, imageWidth, imageHeight, null);
    }
}
