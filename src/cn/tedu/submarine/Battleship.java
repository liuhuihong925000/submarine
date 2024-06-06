package cn.tedu.submarine;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Battleship extends SeaObject {

   private int life;


    public Battleship() {  //构造方法，与类同名，构造的目的是给实例变量赋值，
        super(66,26,270,124,10);

        life = 5;
     }

    public void move()   {
              }
    //战舰右移
    public void moveRight() {
        if(x<=World.WIDTH-this.width) {
            x += speed;
        }
    }
    //战舰左移
    public void moveLeft(){
        if(x>=0) {
            x -= speed;
        }
    }
   //重写getImage() 获取图片
   public ImageIcon getImage(){
        return Images.battleship;   //返回战舰图片
   }

   public Bomb shootBomb() {
        return new Bomb(this.x, this.y );
   }


//战舰增命
   public void addlife(int num) {
        life += num;
//       System.out.println(life);
   }

//   战舰减命
    public void minusLife() {
        life --;
    }
   public int getLife() {
        return life;
   }


}



