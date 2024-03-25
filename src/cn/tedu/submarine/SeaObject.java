package cn.tedu.submarine;

import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class SeaObject { //抽象类，不能直接new对象，必须被继承，后重写，再new对象
    public static final int Live=0;
    public static final int Dead=1;

    private int state = Live;






   protected int width;  //成员变量,写在方法外边的
//    protected修饰符，它主要的作用就是用来保护子类的。
//    它的含义在于子类可以用它修饰的成员，其他的不可以，它相当于传递给子类的一种继承的东西。
   protected int height;
    protected int x;
    protected int y;
    protected int speed;


    public SeaObject(int width, int height) {   //专门给Submarine 类使用的
        this.width = width;    //局部变量
        this.height = height;
        x = -width;
        Random rand = new Random();
        y = rand.nextInt(World.HEIGHT-height-150+1)+150;  // 取值 150～ 窗口高- 潜艇高
        speed = rand.nextInt(3)+1; //1～3之间的随机数
    }

    public SeaObject(int width, int height, int x, int y, int speed) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public abstract void move() ;
    //抽象方法，由 abstract 修饰
    //只有方法的定义（），没有具体的实现，连方法体{}都没有
    //抽象方法，为所有派生类提供统一的入口--（向上造型后能点出来），同时达到强制必须重写的目的
    //

    //    {
    //        System.out.println("push me");
    //    }

    public abstract ImageIcon getImage();
    //没有void表示 有返回值；返回值的class叫ImageIcon



    public boolean isLive() {
        return state == Live; //若state为Live，表示活的，返回true, 否则false
    }

    public boolean isDead() {
        return state == Dead; //若state为Dead,表示死的，返回true,否则false
    }


    //画对象 g:画笔
   public void paintImage(Graphics g) {
        if (this.isLive()){
            this.getImage().paintIcon(null,g,this.x,this.y);  //
        }
    }


   public boolean isOutOfBounds() {  //检测海洋对象越界
        return this.x>= World.WIDTH; //若潜艇动x>=窗口的宽，表示越界
        }

   public boolean isHit(SeaObject other) {
        //this 指代 潜艇； other 指代 炸弹
        int x1 = this.x - other.width;
        int x2 = this.x + this. width;
        int y1 = this.y - other.height;
        int y2 = this.y + this.height;
        int x = other.x;  // x: 炸弹的x
        int y = other.y;  //  y: 炸弹的y
        return x>=x1 && x<=x2
                &&
                y>=y1 && y<=y2;
   }

   public void goDead() {
        state = Dead;   //当前状态修改为Dead
   }

   public int getScore(){
        return 0;
   };
}



