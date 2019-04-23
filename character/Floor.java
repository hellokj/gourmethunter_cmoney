package character;


import character.trap.Trap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Floor extends GameObject {
    private ArrayList<BufferedImage> floorImages; // 統一圖片回傳類型(處理地板有動畫的機制，取代GameObject只存一張圖)

    // 依照回傳的選圖模式來印出不同圖片
    private int[] choosingImagesMode;

    private Trap trapFunction; // 機關
    private boolean isTriggered; // 角色在階梯上，觸發機關

    // 目前未用到的建構子
    public Floor(int x, int y, int drawWidth, int drawHeight, String imagePath){
        super(x, y, drawWidth, drawHeight, imagePath); // 此圖用來當未被觸發的基礎圖
    }

    public Floor(int x, int y, Trap trapFunction){
        super(x, y);
        this.speedY = -0.1f;
        this.trapFunction = trapFunction;
        this.floorImages = new ArrayList<>();
        // 傳入陷阱類型，設定自己的狀態
        this.trapFunction.setFloorState(this);
        this.setBoundary();
    }


    // getter and setter
    public ArrayList<BufferedImage> getFloorImages(){
        return this.floorImages;
    }
    public void setChoosingImagesMode(int[] choosingImagesMode){
        this.choosingImagesMode = choosingImagesMode;
    }

    // 未被觸發的動畫選圖
    // 某些地板被觸發才播動畫，可能不適用此方法
    public void stay(){
        this.choosingImagesCounter = choosingImagesCounter % choosingImagesMode.length;
        this.choosingImagesCounter++;
    }

    public void isBeenTouched(Actor player){
        this.isTriggered = true;
        executeTrap(player);
    }

    public boolean isTriggered(){
        return isTriggered;
    }

    // 發動機關
    public void executeTrap(Actor player){
        if (isTriggered){
            this.trapFunction.execute(player, this);
            // 觸發狀態設回未觸發
            isTriggered = false;
        }
    }

    @Override
    public void update(){
        y += speedY;
    }

    @Override
    public void paint(Graphics g){
        setBoundary();
        // 加入不同地板動畫後，畫圖模式(測試)
        try {
            //  有機會畫到還未重設的畫圖模式 會報錯
            g.drawImage(floorImages.get(choosingImagesMode[choosingImagesCounter-1]), x, y - floorImages.get(choosingImagesMode[choosingImagesCounter-1]).getHeight() + drawHeight, x + drawWidth, y + drawHeight, 0, 0, floorImages.get(choosingImagesMode[choosingImagesCounter-1]).getWidth(), floorImages.get(choosingImagesMode[choosingImagesCounter-1]).getHeight(), null);
        }catch (ArrayIndexOutOfBoundsException e){
            g.drawImage(floorImages.get(choosingImagesMode[0]), x, y - floorImages.get(choosingImagesMode[0]).getHeight() + drawHeight, x + drawWidth, y + drawHeight, 0, 0, floorImages.get(choosingImagesMode[0]).getWidth(), floorImages.get(choosingImagesMode[0]).getHeight(), null);
        }
    }

    public BufferedImage getCurrentDrawingImage(){
        return floorImages.get(choosingImagesMode[choosingImagesCounter-1]);
    }
}
