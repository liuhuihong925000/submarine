package cn.tedu.submarine;

import javax.swing.*;

public class MineSubmarine extends SeaObject implements EnemyLife{


    public MineSubmarine() {
        super(63,19);

    }
    public void move() {
        x+= speed; //x+ (向右)
    }

    public ImageIcon getImage(){
        return Images.minesubm;

    }
    public Mine shootMine() {
        return new Mine(this.x + this.width, this.y - 11); //this 指代水雷艇
    }

    public int getLife() {
        return 1;  //打掉水雷艇，战舰得1条命。
    }
}
