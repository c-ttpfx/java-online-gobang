package view;

import clientUserService.ClientManage;
import clientUserService.ClientUserServer;
import message.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.Socket;
import java.net.URL;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/09/30/0:54
 * @Description: 为人所不为, 能人所不能
 */
public class LoginView extends JFrame {
    private User user = new User();
    private String title = "我亦无他,唯手熟尔";
    private JLabel titleLable;
    private JLabel nameLable;
    private JLabel passwordLable;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton returnButton;
    private SpringLayout springLayout;
    private JPanel jPanel;
    private Container contentPane;
    private StartView startView;
    private GameView gameView;
    private LoginView loginView;

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public LoginView(StartView startView) {
        super("登录界面");
        this.startView = startView;
        contentPane = getContentPane();
        springLayout = new SpringLayout();
        jPanel = new JPanel(springLayout);

        setComponentSize();
        addEvent();
        setPosition();
        addAssembly();
        panelSetting();

    }

    public void addAssembly() {
        jPanel.add(nameLable);
        jPanel.add(nameField);
        jPanel.add(passwordLable);
        jPanel.add(passwordField);
        jPanel.add(loginButton);
        jPanel.add(returnButton);
        contentPane.add(jPanel, BorderLayout.CENTER);
        contentPane.add(titleLable, BorderLayout.NORTH);
    }

    public void setPosition() {
        /*
            设置位置
         */
        //用户栏
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(nameLable), Spring.width(nameField)),
                Spring.constant(40));
        int offset = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, nameLable, -offset,
                SpringLayout.HORIZONTAL_CENTER, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, nameLable, 100, SpringLayout.NORTH, jPanel);

        springLayout.putConstraint(SpringLayout.WEST, nameField, 40,
                SpringLayout.EAST, nameLable);
        springLayout.putConstraint(SpringLayout.NORTH, nameField, 0, SpringLayout.NORTH, nameLable);
        //密码栏
        springLayout.putConstraint(SpringLayout.WEST, passwordLable, 0,
                SpringLayout.WEST, nameLable);
        springLayout.putConstraint(SpringLayout.NORTH, passwordLable, 60,
                SpringLayout.NORTH, nameLable);

        springLayout.putConstraint(SpringLayout.WEST, passwordField, 0,
                SpringLayout.WEST, nameField);
        springLayout.putConstraint(SpringLayout.NORTH, passwordField, 0,
                SpringLayout.NORTH, passwordLable);
        //按钮
        springLayout.putConstraint(SpringLayout.WEST, loginButton, 150,
                SpringLayout.WEST, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, loginButton, 100,
                SpringLayout.NORTH, passwordLable);
        springLayout.putConstraint(SpringLayout.EAST, returnButton, -150,
                SpringLayout.EAST, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, returnButton, 0,
                SpringLayout.NORTH, loginButton);
    }

    public void setComponentSize() {
        //自定义文本框大小
        Dimension dimension = new Dimension(300, 40);
        Font font = new Font("微软雅黑", Font.PLAIN, 30);
        Font buttonFont = new Font("微软雅黑", Font.PLAIN, 25);
        Font fontText = new Font("宋体", Font.PLAIN, 30);

        //设置标题大小
        titleLable = new JLabel(title, JLabel.CENTER);
        titleLable.setFont(new Font("华文行楷", Font.PLAIN, 80));

        nameLable = new JLabel("用户名:");
        nameLable.setFont(font);
        // 文本域
        nameField = new JTextField();
        nameField.setPreferredSize(dimension);
        nameField.setFont(fontText);

        passwordLable = new JLabel("密   码:");
        passwordLable.setFont(font);
        // 密码域
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(dimension);
        passwordField.setFont(fontText);

        //两个按钮
        loginButton = new JButton("登录");
        returnButton = new JButton("返回");
        loginButton.setFont(buttonFont);
        returnButton.setFont(buttonFont);
        Dimension preferredSize = new Dimension(250, 50);
        loginButton.setPreferredSize(preferredSize);
        returnButton.setPreferredSize(preferredSize);
    }

    public void panelSetting() {
        setSize(900, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //图标
        setVisible(true);
        URL url = StartView.class.getClassLoader().getResource("images/登录图标.png");
        Image image = new ImageIcon(url).getImage();
        setIconImage(image);
    }

    public void addEvent() {
        loginButtonEvent();
        returnButtonEvent();
    }

    public void loginButtonEvent() {
        loginButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setName(nameField.getText());
                user.setPassword(new String(passwordField.getPassword()));
                System.out.println(user.getName()+"想要登录");
                if (gameView == null){
                    gameView = new GameView();
                    gameView.setLoginView(loginView);
                    gameView.setGameView(gameView);
                    ManageViewAssembly.gameView = gameView;
                }
                ClientUserServer clientUserServer = new ClientUserServer(user);
                int logIn = clientUserServer.checkLogIn();
                /*
                 *   0表示服务器同意登录,1表示重复登录,2表示用户名或者密码错误
                 */

                System.out.println(logIn);
                if (logIn == 0){
                    System.out.println("登录成功");
                    setVisible(false);
                    Socket socket = clientUserServer.getSocket();
                    ClientManage.socket = socket;
                    gameView.setVisible(true);
                    gameView.setSocket(socket);
                }else if (logIn == 2){
                    System.out.println("登录失败");
                    JOptionPane.showMessageDialog(null, "用户名或密码错误,登录失败!!!",
                            "登录失败",JOptionPane.ERROR_MESSAGE);
                }else if (logIn == 1){
                    JOptionPane.showMessageDialog(null, "请不要重复登录!!!",
                            "登录失败",JOptionPane.ERROR_MESSAGE);
                }


            }
        });
    }

    public void returnButtonEvent() {
        returnButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                startView.setVisible(true);
            }
        });
    }


}
