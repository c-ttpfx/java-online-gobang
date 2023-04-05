package test;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

//以下为一个框体小程序  
public class _001// _001为自定义的主类名  
{
    public static void main(String[] args) {
        JFrame newFrame=new JFrame("funBox");
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);          //定义JFrame关闭时的操作（必需），有效避免不能关闭后台当前框体进程的问题
        newFrame.setSize(400, 400);         //定义JFrame的相关属性
        newFrame.setLocation(200, 200);
        newFrame.setVisible(true);
        newThread n1= new newThread();           //线程的运行，将需要呈现的图像添加进JFrame中
        newFrame.add(n1);
        Thread t1 = new Thread(n1);
        t1.start();

    }
}

class newThread extends JPanel implements Runnable //Java类中只能继承一个类，但是可以实现多个接口，此处newThread 为自定义新建类名  
{
    Graphics g;    //此处定义Graphics对象 g;
    private static final long serialVersionUID = 1L;
    @Override
    public void run()   //进程run()方法重写
    {
        g=getGraphics();   //Graphics对象 g的获取
        for(int i=0;i<100;)
        {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
// TODO Auto-generated catch block  
                e.printStackTrace();
            }
            this.update(g) ;       //update()方法的调用，刷新图像，使得图像不会重叠显现
            g.setColor(Color.green);   //绘制（0，0）开始移动的20*20绿色小块
            g.fillRect(i, i, 20, 20);
            i+=20;
        }
    }
}