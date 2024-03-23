package cn.tedu.submarine;

import javax.swing.*;
import java.util.Random;

public class ObserveSubmarine extends SeaObject implements EnemyScore{


    public ObserveSubmarine() {
        super(63,19);  //调用超类的构造函数（方法）
    }

    public void move() {
          x += speed; //x+（向右）

     }  //重写 override

    public ImageIcon getImage(){
        return Images.obsersubm;  //返回的图片地址，路径


     }
//    public boolean isOutOfBounds() {
//  重写getScore()    得分
     public int getScore() {
        return  10; //打掉侦察潜艇，得10分
     }
//}
}
