package cn.tedu.submarine;

import javax.swing.*;
//图片类型： image, imageIcon, Icon, BufferedImage
public class Images {
    public static ImageIcon sea;
    public static ImageIcon bomb;
    public static ImageIcon gameover;
    public static ImageIcon mine;
    public static ImageIcon minesubm;
    public static ImageIcon obsersubm;
    public static ImageIcon torpesubm;
    public static ImageIcon battleship;

    static {
        sea = new ImageIcon("img/sea.png");
        bomb = new ImageIcon("img/bomb.png");
        gameover = new ImageIcon("img/gameover.png");
        mine = new ImageIcon("img/mine.png");
        minesubm = new ImageIcon("img/minesubm.png");
        obsersubm = new ImageIcon("img/obsersubm.png");
        torpesubm = new ImageIcon("img/torpesubm.png");
        battleship = new ImageIcon("img/battleship.png");
    }

//    public static void main(String[] args) {
//        System.out.println(sea.getImageLoadStatus());
//        System.out.println(bomb.getImageLoadStatus());
//        System.out.println(gameover.getImageLoadStatus());
//
//        System.out.println(mine.getImageLoadStatus());
//        System.out.println(minesubm.getImageLoadStatus());
//        System.out.println(obsersubm.getImageLoadStatus());
//
//        System.out.println(torpesubm.getImageLoadStatus());
//        System.out.println(battleship.getImageLoadStatus());
//
//    }
}
