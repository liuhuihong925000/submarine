package cn.tedu.submarine;

import javax.swing.*;

public class TorpedoSubmarine extends SeaObject implements EnemyScore  {



    public TorpedoSubmarine() {
        super(64,20);

    }



    public void move() {
        x+= speed; //x+ (向右)
    }

    public ImageIcon getImage(){
        return Images.torpesubm;
    }

    public int getScore() {
        return 40;  //打掉鱼雷艇，得四十分
    }
}
