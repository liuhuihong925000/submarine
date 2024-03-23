package cn.tedu.submarine;

import javax.swing.*;
import java.util.Random;

public class Mine extends SeaObject {


    public Mine (int x, int y) {
//        System.out.println("hello!!");
//        System.out.println("start to build mine!");//eg: Mine m = new Mine(100,200);
        super(11,11,x,y,1)  ;

    }

    public void move() {
        y-= speed; //y- (向上)
    }

    public ImageIcon getImage(){
        return Images.mine;
    }

    public boolean isOutOfBounds() {
        return this.y<= 150-this.height; //若水雷的y<=海平面-水雷的高，即位越界
    }

}


