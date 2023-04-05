package view;

import Utility.MessageUtility;
import clientUserService.ClientManage;

import javax.swing.*;
import java.awt.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/02/12:12
 * @Description: 为人所不为, 能人所不能
 */
public class CheckerBoardView extends JFrame {
    private JPanel topJPanel;
    private JPanel leftJPanel;
    private JPanel rightJPanel;
    private JPanel chessJPanel;
    private JPanel bottomJPanl;
    private Container containerPane;
    private GameView gameView;

    public CheckerBoardView() {
        containerPane = getContentPane();
        settingTopJPanel();
        settingLeftJPanel();
        settingRightJPanel();
        settingChessJPanel();
//        settingBottomJPanel();
        panelSetting();
    }

    public CheckerBoardView(GameView gameView) {
        this.gameView = gameView;
    }

    public void panelSetting() {
        //设置图标
        URL url = CheckerBoardView.class.getClassLoader().getResource("images/登录图标.png");
        Image image = new ImageIcon(url).getImage();
        setIconImage(image);
        //设置大小
        setSize(1600, 1100);
        //固定窗口大小
        setResizable(false);
        //屏幕居中
        setLocationRelativeTo(null);
        //关闭窗口
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置可见
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MessageUtility.close(ClientManage.socket,ClientManage.rivalUser.getName());
            }
        });
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout(0, 0));
    }
    public void settingChessJPanel(){
        chessJPanel = new ChessView();
        containerPane.add(chessJPanel,BorderLayout.CENTER);
    }
    public void settingTopJPanel() {
        topJPanel = new JPanel();
        JLabel title = new JLabel("人活着就是为了樱岛麻衣!!!!");
        title.setFont(new Font("华文行楷", Font.PLAIN, 58));
        topJPanel.add(title);
        topJPanel.setBackground(Color.pink);
        containerPane.add(topJPanel, BorderLayout.NORTH);
    }
    public void settingBottomJPanel() {
        bottomJPanl = new JPanel();
        JLabel title = new JLabel("人活着就是为了樱岛麻衣!!!!");
        title.setFont(new Font("华文行楷", Font.PLAIN, 5));
        topJPanel.add(title);
        topJPanel.setBackground(Color.pink);
        containerPane.add(topJPanel, BorderLayout.SOUTH);
    }
    public void settingLeftJPanel() {
        leftJPanel = new JPanel();
        JLabel title = new JLabel("樱岛麻衣!!!!!!!!");
        title.setFont(new Font("华文行楷", Font.PLAIN, 50));
        leftJPanel.add(title);
        leftJPanel.setBackground(Color.gray);
        containerPane.add(leftJPanel, BorderLayout.WEST);
    }

    public void settingRightJPanel() {
        rightJPanel = new JPanel();
        JLabel title = new JLabel("樱岛麻衣!!!!!!!");
        title.setFont(new Font("华文行楷", Font.PLAIN, 50));
        rightJPanel.add(title);
        rightJPanel.setBackground(Color.gray);
        containerPane.add(rightJPanel, BorderLayout.EAST);
    }



}
