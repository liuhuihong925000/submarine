package cn.tedu.submarine;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

//**整个游戏的窗口 * /
public class World  extends JPanel {
    private Battleship ship = new Battleship(); //申明 ship 战舰对象
    private SeaObject[] submarines = {};
    private Mine[] mines = { //元素与元素用逗号隔开
    }; //水雷组
    private Bomb[] bombs = {}; //炸弹组


    private SeaObject nextSubmarines() { //随机生成潜艇(鱼雷潜艇，水雷潜艇，侦查潜艇)对象
        Random rand = new Random();
        int type = rand.nextInt(20); //生成0～19之间的随机数
        if (type < 9) {
            return new ObserveSubmarine();
        } else if (type < 16) {
            return new TorpedoSubmarine();
        } else {
            return new MineSubmarine();
        }
    }

    private int submarineEnterIndex = 0;

    private void submarineEnterAction() { //10ms 执行一次
        submarineEnterIndex++;
        if (submarineEnterIndex % 40 == 0) { //每400ms 走一次
            SeaObject obj = nextSubmarines();//此处调用方法。获取潜艇
            //调用方法的规则：有返回值，必须申明变量；无返回值，直接调用。
            //同一类（class)当中的方法，直接写方法名调用。
            //不同类当中：
            //   实例方法，引用打点调用
            //   静态方法，类名打点打点调用
            submarines = Arrays.copyOf(submarines, submarines.length + 1);
            //扩容, 其中submarines是数组名。
            submarines[submarines.length - 1] = obj;
            //把新生成的obj放入submarines数组的最后一位
        }
    }


    int mineEnterIndex = 0;

    private void mineEnterAction() {
        mineEnterIndex++; //每10ms 增加1，
        if (mineEnterIndex % 100 == 0) { //每1000ms 触发一次
            for (int i = 0; i < submarines.length; i++) {
                if (submarines[i] instanceof MineSubmarine) { //若潜艇类对象（该超类）能转为水雷艇对象
                    MineSubmarine ms = (MineSubmarine) submarines[i];//将潜艇强制转为水雷艇
                    Mine obj = ms.shootMine();
                    ////此处调用方法。获取水雷对象
                    //调用方法的规则：有返回值，必须申明变量；无返回值，直接调用。
                    mines = Arrays.copyOf(mines, mines.length + 1);
                    mines[mines.length - 1] = obj;  //将obj添加到最后一个元素上
                }
            }
        }
    }

    private void moveAction() {
        for (int i = 0; i <= submarines.length - 1; i++) {
            submarines[i].move();  //潜艇动
        }

        for (int i = 0; i <= mines.length - 1; i++) {
            mines[i].move();  //水雷动
        }

        for (int i = 0; i <= bombs.length - 1; i++) {
            bombs[i].move();  //炸弹动
        }
    }


    //删除所有越界海洋对象
    private void OutofBoundsAction() { //被检测中，每10ms扫描一次
        for (int i = 0; i <= submarines.length - 1; i++) {  //遍历所有潜艇对象
            if (submarines[i].isOutOfBounds() || submarines[i].isDead()) {    //i元素，越界了
                submarines[i] = submarines[submarines.length - 1]; //i元素被最后一个元素取代
                submarines = Arrays.copyOf(submarines, submarines.length - 1);//缩容
            }
        }

        for (int i = 0; i <= mines.length - 1; i++) {
            if (mines[i].isOutOfBounds() || mines[i].isDead()) {
                mines[i] = mines[mines.length - 1];
                mines = Arrays.copyOf(mines, mines.length - 1);
            }
        }

        for (int i = 0; i <= bombs.length - 1; i++) {
            if (bombs[i].isOutOfBounds() || bombs[i].isDead()) {
                bombs[i] = bombs[bombs.length - 1];
                bombs = Arrays.copyOf(bombs, bombs.length - 1);
            }
        }
    }

    //炸弹与潜艇的碰撞
    private int score=0; // 玩家得分
    private void bombBangAction() {  //每10ms走一次


        for (int i = 0; i <= bombs.length - 1; i++) {
            Bomb b = bombs[i]; // 获取每一个炸弹
            for (int j = 0; j <= submarines.length - 1; j++) {
                SeaObject s = submarines[j];
                if (s.isLive() && b.isLive() && s.isHit(b)) {
                    s.goDead();
                    b.goDead();

                    score += submarines[j].getScore();;

//                    System.out.println(score);

                   if(s instanceof EnemyLife) {  //接口增命的写法
                       EnemyLife el = (EnemyLife)s;
                       int num = el.getLife();
                       ship.addlife(num);
//                       System.out.println(Battleship.getLife);

                }


                }
            }
        }

    }

    private void action() {
        //侦听器对象
        KeyAdapter k = new KeyAdapter() {
            @Override ////重写keypressed()
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) { //按空格出炸弹
                    //shootBomb在战舰类中，不同类中的调用，对象ship打点去调用
                    Bomb obj = ship.shootBomb();

                    bombs = Arrays.copyOf(bombs, bombs.length + 1); //扩容
                    bombs[bombs.length - 1] = obj; //将obj添加到最后一个元素上

                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    ship.moveLeft();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    ship.moveRight();
                }
            }
        };

        this.addKeyListener(k);  //添加侦听器


        Timer timer = new Timer(); //定时器对象; Timer是java自带的类
        int interval = 10; //定时器间隔 10 ms
        timer.schedule(new TimerTask() {
            @Override  //执行时间间隔10ms
            public void run() {
                //潜艇入场、水雷
                submarineEnterAction();//潜艇入场
                mineEnterAction();    //水雷入场
                moveAction();         //海洋对象的移动
                OutofBoundsAction();//删除越界的海洋对象

                bombBangAction();    //  炸弹与潜艇碰撞
                repaint(); // 超类中有个repaint()方法，10       ms页面重载
            }
        }, interval, interval);  //定时计划表
    }

    public static final int WIDTH = 641; //窗口宽
    public static final int HEIGHT = 479; //窗口高

    //   重写paint()画  g:画笔
    public void paint(Graphics g) {
//        this.getImage().paintIcon(c：null, g, this.x, this.y)

        Images.sea.paintIcon(null, g, 0, 0);//画海洋图
//        Images.battleship.paintIcon(null,g,270,124);
        ship.paintImage(g);   //画战舰

//        Images.mine.paintIcon(null,g,200,300);

        for (int i = 0; i <= submarines.length - 1; i++) {//遍历所有的潜艇
            submarines[i].paintImage(g);   //画潜艇
        }
        for (int i = 0; i <= mines.length - 1; i++) { // 遍历所有水雷
            mines[i].paintImage(g);        //画水雷
        }

        for (int i = 0; i <= bombs.length - 1; i++) {   //遍历所有炸弹
            bombs[i].paintImage(g);        //画炸弹
        }
        g.drawString("SCORE:" + score, 200, 50);
        g.drawString("LIFE"+ ship.getLife(), 400, 50);
    }

//    方法里面都是局部的

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        World world = new World(); //new 了上面那一堆对象
        world.setFocusable(true);
        frame.add(world);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH + 6, HEIGHT + 39);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);  //自动调用paint()方法, 开始画喽

        world.action();//程序去调用 action，启动

//       SeaObject[] submarines = new SeaObject[5];
//        submarines[0] = new ObserveSubmarine();//向上造型
//        submarines[1] = new ObserveSubmarine(); //new派生类对象
//        submarines[2] = new TorpedoSubmarine();
//        submarines[3] = new TorpedoSubmarine();
//        submarines[4] = new Battleship();
//       submarines[5] = new MineSubmarine();
//        for(int i =0; i <= submarines.length-1; i++) {
//            submarines[i].move();
//        }

    }
}


