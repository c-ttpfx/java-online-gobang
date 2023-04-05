package view;

import Utility.CustomDialog_Confim;
import Utility.MessageUtility;
import clientUserService.ClientConnectServiceThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/09/30/0:50
 * @Description: 为人所不为, 能人所不能
 */
public class StartView extends JFrame {
    private JButton loginButton;
    private JButton registerButton;
    private JButton offlineModeButton;
    private JButton exitTheGameButton;
    private SpringLayout springLayout;
    private JPanel jPanel;
    private LoginView loginView;
    private RegisterView registerView;
    private StartView startView;

    public void setStartView(StartView startView) {
        this.startView = startView;
    }

    public StartView() {
        //设置标题
        super("欢迎开始联机五子棋游戏!!!");
        //设置布局
        springLayout = new SpringLayout();
        jPanel = new JPanel(springLayout);

        //初始化登录按钮
        loginButton = new JButton("登    录");
        registerButton = new JButton("注    册");
        offlineModeButton = new JButton("离线游戏");
        exitTheGameButton = new JButton("退出游戏");

        setButtonSize();
        setButtonPosition();
        addEvent();

        //获得窗体的 contentPane 对象
        Container contentPane = getContentPane();
        //添加按钮
        jPanel.add(loginButton);
        jPanel.add(registerButton);
        jPanel.add(offlineModeButton);
        jPanel.add(exitTheGameButton);

        contentPane.add(jPanel);
        panelSetting();
    }

    public void setButtonSize() {
        //设置尺寸
        Dimension preferredSize = new Dimension(300, 50);
        loginButton.setPreferredSize(preferredSize);
        registerButton.setPreferredSize(preferredSize);
        offlineModeButton.setPreferredSize(preferredSize);
        exitTheGameButton.setPreferredSize(preferredSize);
        //设置按钮文字大小
        Font font = new Font("宋体", Font.PLAIN, 20);
        loginButton.setFont(font);
        registerButton.setFont(font);
        offlineModeButton.setFont(font);
        exitTheGameButton.setFont(font);

    }

    public void setButtonPosition() {
        //设置位置
        SpringLayout.Constraints login = springLayout.getConstraints(loginButton);
        login.setX(Spring.constant(290));
        login.setY(Spring.constant(80));
        SpringLayout.Constraints register = springLayout.getConstraints(registerButton);
        register.setX(Spring.constant(login.getX().getPreferredValue()));
        register.setY(Spring.constant(login.getY().getPreferredValue() + 100));
        SpringLayout.Constraints offlineMode = springLayout.getConstraints(offlineModeButton);
        offlineMode.setX(Spring.constant(register.getX().getPreferredValue()));
        offlineMode.setY(Spring.constant(register.getY().getPreferredValue() + 100));
        SpringLayout.Constraints exitTheGame = springLayout.getConstraints(exitTheGameButton);
        exitTheGame.setX(Spring.constant(offlineMode.getX().getPreferredValue()));
        exitTheGame.setY(Spring.constant(offlineMode.getY().getPreferredValue() + 100));
    }

    public void panelSetting() {
        //设置图标
        URL url = StartView.class.getClassLoader().getResource("images/登录图标.png");
        Image image = new ImageIcon(url).getImage();
        setIconImage(image);
        //设置背景图片
        // 设置背景
        // 创建一个标签组件对象
        JLabel lblBackground = new JLabel();
        // 获取背景图片路径
        URL resource = StartView.class.getClassLoader().getResource("images/登录图片.jpg");
        // 创建背景图片对象
        ImageIcon icon = new ImageIcon(resource);
        // 设置标签组件要显示的图标
        lblBackground.setIcon(icon);
        // 设置组件的显示位置及大小
        lblBackground.setBounds(0, 0, 900, 600);
        jPanel.add(lblBackground);
        //设置大小
        setSize(900, 600);
        //固定窗口大小
        setResizable(false);
        //屏幕居中
        setLocationRelativeTo(null);
        //关闭窗口
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置可见
        setVisible(true);
    }

    public void addEvent() {
        loginButtonEvent();
        exitTheGameButtonEvent();
        registerButtonEvent();
    }
    public void registerButtonEvent(){
        registerButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (registerView == null){
                   registerView = new RegisterView(startView);
                   registerView.setRegisterView(registerView);
               }
                setVisible(false);
                registerView.setVisible(true);
            }
        });
    }
    public void loginButtonEvent() {
        loginButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginView == null){
                    loginView = new LoginView(startView);
                    loginView.setLoginView(loginView);
                }
                setVisible(false);
                loginView.setVisible(true);
            }
        });
    }

    public void exitTheGameButtonEvent() {
        exitTheGameButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                CustomDialog_Confim customDialog = new CustomDialog_Confim("look me", "确认退出吗");
                if (customDialog.isComfir()){
                   System.exit(0);
                }else {
                    setVisible(true);
                }

            }
        });
    }

}
