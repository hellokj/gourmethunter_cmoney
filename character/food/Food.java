package character.food;

import character.Floor;
import character.GameObject;

public class Food extends GameObject {
    private Floor floor; // 在的那塊地板
    private boolean isEaten; // 是否被吃
    private int heal; // 回復的飢餓值
    private int offset; // 在階梯上位置

    public Food(int foodCode, Floor floor, int offset){
        super(floor.getX() + 24*offset, floor.getY() - 24, 24, 24, "food/farm_product"+ foodCode +".png");
        this.offset = offset;
        this.isEaten = false;
        this.floor = floor;
        this.heal = (int)(Math.random()*100);
    }

    @Override
    public void update(){
        this.x = floor.getX() + 24*offset;
        this.y = floor.getY() - 24;
        this.setBoundary();
    }

    public void eaten() {
        isEaten = true;
    }

    public boolean isEaten() {
        return isEaten;
    }

    public int getHeal() {
        return heal;
    }

}
