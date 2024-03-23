package cn.tedu.submarine;

import javax.swing.*;

public class Bomb extends SeaObject {


    private int[] arr = new int[10];

    public Bomb(int x,int y) {
      super(9,12,x,y,3);
    }

    public void move() {
        y += speed; //y+ (向下)
    }  //函数重写

    public ImageIcon getImage(){
        return Images.bomb;
    }


    public boolean isOutOfBounds() {
        return this.y>= World.HEIGHT; //若潜艇动x>=窗口的宽，表示越界
    }
}

