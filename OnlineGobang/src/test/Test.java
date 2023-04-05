package test;

import javax.swing.*;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/09/30/1:02
 * @Description: 为人所不为, 能人所不能
 */
public class Test {
    public static void main(String[] args) {
        // 创建JFrame
        JFrame frame = new JFrame("hello,world");
        // 创建按钮
        JButton button = new JButton("OK");
        // 向frame中添加一个按钮
        frame.add(button);
        // 设置尺寸
        frame.setSize(500, 500);
        // JFrame在屏幕居中
        frame.setLocationRelativeTo(null);
        // JFrame关闭时的操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 显示JFrame
        frame.setVisible(true);

    }
}
