package characters.floor;

import characters.Actor;
import characters.GameObject;
import characters.rule.MovementMode;
import characters.rule.Rule;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Floor extends GameObject {
    private Rule functionRule;
    private ArrayList<BufferedImage> floorImages; // 統一圖片回傳類型(處理地板有動畫的機制，取代GameObject只存一張圖)
    private int[] choosingImagesMode; // 依照回傳的選圖模式來印出不同圖片

    private boolean isTriggered;
    private int delayCount;
    private int drawingCount, drawing; // 將每張動畫畫完

    // 建構地板 參數
    public Floor(int x, int y, Rule rule){
        super(x, y);
        floorImages = new ArrayList<>();
        functionRule = rule;
        executeRule(rule); // 建構地板時，即將其屬性設定
    }

    public Floor(int x, int y, int imageWidth, int imageHeight, Rule rule, MovementMode movementMode){
        super(x, y, imageWidth, imageHeight, imageWidth, imageHeight);
//        speedY = -1;
        executeRule(rule); // 建構地板時，即將其屬性設定
//        movementMode = movementMode;
    }

    public Floor(int x, int y, int imageWidth, int imageHeight){
        super(x, y, imageWidth, imageHeight, imageWidth, imageHeight);
        speedY = -(int)(Math.random()*4 + 1);
    }

    public void reset(){
        isTriggered = false;
        executeRule(functionRule);
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

    public int getDrawingCount() {
        return drawingCount;
    }

    public void setDrawingCount(int drawingCount) {
        this.drawingCount = drawingCount;
    }

    public int getDrawing() {
        return drawing;
    }

    public void setDrawing(int drawing) {
        this.drawing = drawing;
    }

    public void setChoosingImagesMode(int[] choosingImagesMode) {
        this.choosingImagesMode = choosingImagesMode;
    }

    public void rise(){
        y += speedY;
        setBoundary();
    }

    // 機關狀態設回未觸發
    public boolean isTriggered() {
        return isTriggered;
    }

    public void setTriggered(boolean triggered) {
        isTriggered = triggered;
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
        if (isTriggered){
            this.functionRule.execute(this, player);
            isTriggered = false;
        }
    }

    @Override
    public void paint(Graphics g){
        setBoundary();
        // 加入不同地板動畫後，畫圖模式(測試)
        try {
           //  有機會畫到還未重設的畫圖模式 會報錯
            g.drawImage(floorImages.get(choosingImagesMode[choosingImagesCounter-1]), x, y - floorImages.get(choosingImagesMode[choosingImagesCounter-1]).getHeight() + imageHeight, x + imageWidth, y + imageHeight, 0, 0, floorImages.get(choosingImagesMode[choosingImagesCounter-1]).getWidth(), floorImages.get(choosingImagesMode[choosingImagesCounter-1]).getHeight(), null);
        }catch (ArrayIndexOutOfBoundsException e){
            g.drawImage(floorImages.get(choosingImagesMode[0]), x, y - floorImages.get(choosingImagesMode[0]).getHeight() + imageHeight, x + imageWidth, y + imageHeight, 0, 0, floorImages.get(choosingImagesMode[0]).getWidth(), floorImages.get(choosingImagesMode[0]).getHeight(), null);
        }
    }
}
