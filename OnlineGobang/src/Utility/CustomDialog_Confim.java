package Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/01/14:28
 * @Description: 为人所不为, 能人所不能
 */
public class CustomDialog_Confim extends JDialog implements ActionListener {
    String title;
    String content;
    String ok = "确定";
    String cancel = "取消";
    private boolean isComfir;

    public boolean isComfir() {
        return isComfir;
    }

    public CustomDialog_Confim(String title, String content) {
        this.title = title;
        this.content = content;
        int width = 50, height = 50;
//         创建1个图标实例,注意image目录要与src同级
        ImageIcon iconImage = ImageUtility.getImageIcon("images/问号.jpg");
        iconImage.setImage(iconImage.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        // 1个图片标签,显示图片
        JLabel jlImg = new JLabel();
        jlImg.setIcon(iconImage);
        jlImg.setSize(width, height);
        jlImg.setBounds(140, 44, width, height);
        // 1个文字标签,显示文本
        JLabel jLabel = new JLabel(content);
        jLabel.setFont(new Font("", Font.PLAIN, 14));
        // 设置文字的颜色为蓝色
        jLabel.setFont(new Font("宋体",Font.PLAIN,25));
        jLabel.setForeground(Color.black);
        jLabel.setBounds(200, 50, 180, 45);
        JButton okBut = new JButton(ok);
        JButton cancelBut = new JButton(cancel);
        okBut.setBackground(Color.LIGHT_GRAY);
        okBut.setBorderPainted(false);
        okBut.setBounds(100, 150, 130, 45);
        cancelBut.setBounds(280, 150, 130, 45);
        cancelBut.setBackground(Color.LIGHT_GRAY);
        cancelBut.setBorderPainted(false);
        // 给按钮添加响应事件
        okBut.addActionListener(this);
        cancelBut.addActionListener(this);
        // 向对话框中加入各组件
        add(jlImg);
        add(jLabel);
        add(okBut);
        add(cancelBut);
        // 对话框流式布局
        setLayout(null);
        // 窗口左上角的小图标
        setIconImage(iconImage.getImage());
        // 设置标题
        setTitle(title);
        // 设置为模态窗口,此时不能操作父窗口
        setModal(true);
        // 设置对话框大小
        setSize(500, 300);
        // 对话框局域屏幕中央
        setLocationRelativeTo(null);
        // 对话框不可缩放
        setResizable(false);
        // 点击对话框关闭按钮时,销毁对话框
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 判断是不是确定按钮被点击
        if (ok.equals(e.getActionCommand())) {
            // 对话框不可见
            this.setVisible(false);
            System.out.println("成功退出!!!");
            isComfir = true;
        }else {
            this.setVisible(false);
            isComfir = false;
        }
    }
}
