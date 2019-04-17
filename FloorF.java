/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghtest;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author s7207
 */
public class FloorF extends GameObject{
    ArrayList<floor> floors;
    Actor actor;
    int count;
    int state;
    
    //一個磚塊
    public class floor{
        BufferedImage img;
        int x;
        int y;
        int count = 0;
        
        public floor(int x,int y){
            this.x = x;
            this.y = y;
            try {
            img = ImageIO.read(getClass().getResource("/f_1.png"));
            } catch (IOException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //掉落速度
        public boolean fall(){
            y += 10; 
            if(y>700){
                return false;
            }
            return true;
        }
        public void paint(Graphics g){
            g.drawImage(img, x, y,null);
        }
    }
    
    public FloorF(int x, int y, int imageWidth, int imageHeight){
        super(x, y, imageWidth, imageHeight);
        this.count = 0;
        this.state = 0;
        this.actor = new Actor();
        this.floors = new ArrayList();
        int x1 = x;
        for(int i=0;i<4;i++){
            floor f = new floor(x1,y);
            floors.add(f);
            x1 += 16;
        }
        
    }
    //判斷人物碰到地板
    public boolean floorTouch(Actor actor){
        int left1,left2;
        int right1,right2;
        int top1,top2;
        int bottom1,bottom2;
        
        left1 = x;
        left2 = actor.x;
        right1 = x+64;
        right2 = actor.x+ actor.imageWidth;
        top1 = y;
        top2 = actor.y;
        bottom1 = y+16;
        bottom2 = actor.y+ actor.imageHeight;
        
        if(bottom1<top2){
            return false;
        }
        if(top1>bottom2){
            return false;
        }
        if(right1<left2){
            return false;
        }
        if(left1>right2){
            return false;
        }
        this.state = 1;
        return true;
    }
    @Override
    public void paint(Graphics g){
        for(int i=0;i<floors.size();i++){
            floors.get(i).paint(g);
        }
        if(state==1){
            count++;
            if(count>20&&floors.size()>3){
                floors.get(3).fall();
            }
            if(count>15&&floors.size()>2){
                floors.get(2).fall();
            }
            if(count>10&&floors.size()>1){
                floors.get(1).fall();
            }
            if(count>5&&floors.size()>0){
                if(floors.get(0).fall()==false){
                    floors.remove(0);
                }
            } 
            if(floors.size()==0){
                count = 0;
                state = 0;
            }
        }
    }
}
