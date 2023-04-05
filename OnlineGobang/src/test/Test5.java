package test;

import java.awt.event.WindowEvent;

import java.awt.event.WindowListener;

import javax.swing.JFrame;



public class Test5 extends JFrame {
    Test5() {
// this对象时JFrame的子类，而JFrame 类是Window体系中的一员所以具备添加窗口事件的方法
        this.addWindowListener(new MyWindow());
    }
    public static void main(String[] args) {
        Test5 frame = new Test5();
        frame.setTitle("我的框架");
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
// 实现WindowListener
class MyWindow implements WindowListener {
// 激活窗口
    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("激活窗口");
    }
// 关闭窗口
    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("关闭窗口");
    }
// 正在关闭窗口
    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("正在关闭窗口");
    }
// 变为非活动窗口
    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("变为非活动窗口");
    }
// 还原窗口
    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("还原窗口");
    }
// 最小化窗口
    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println(" 最小化窗口");
    }
// 打开窗口
    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("打开窗口");
    }
}
