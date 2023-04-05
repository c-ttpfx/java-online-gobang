package view;

import Utility.CustomDialog_Confim;
import Utility.ImageUtility;
import Utility.MessageUtility;
import clientUserService.ClientConnectServiceThread;
import clientUserService.ClientManage;
import message.Message;
import message.MessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.net.URL;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/01/15:18
 * @Description: 为人所不为, 能人所不能
 */
public class GameView extends JFrame{
    private JButton startGameButton;
    private JButton friendButton;
    private JButton personalInformationButton;
    private JButton exitTheGameButton;
    private JButton historicalAchievementsButton;
    private JButton logOutButton;
    private SpringLayout springLayout;
    private JPanel jPanel;
    private LoginView loginView;
    private CheckerBoardView checkerBoardView;
    private Socket socket;
    private GameView gameView;
    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public CheckerBoardView getCheckerBoardView() {
        return checkerBoardView;
    }

    public void setCheckerBoardView(CheckerBoardView checkerBoardView) {
        this.checkerBoardView = checkerBoardView;
    }

    public GameView() {
        //设置标题
        super("欢迎开始联机五子棋游戏!!!");
        //设置布局
        springLayout = new SpringLayout();
        jPanel = new JPanel(springLayout);

        //初始化登录按钮
        startGameButton = new JButton("开始游戏");
        friendButton = new JButton("好友列表");
        personalInformationButton = new JButton("个人信息");
        historicalAchievementsButton = new JButton("历史战绩");
        logOutButton = new JButton("退出登录");
        exitTheGameButton = new JButton("退出游戏");
        setButton();
        setButtonPosition();
        addEvent();

        //获得窗体的 contentPane 对象
        Container contentPane = getContentPane();
        //添加按钮
        jPanel.add(startGameButton);
        jPanel.add(friendButton);
        jPanel.add(personalInformationButton);
        jPanel.add(historicalAchievementsButton);
        jPanel.add(logOutButton);
        jPanel.add(exitTheGameButton);

        contentPane.add(jPanel);
        panelSetting();
    }

    public void setButton() {
        //设置尺寸
        Dimension preferredSize = new Dimension(300, 50);
        startGameButton.setPreferredSize(preferredSize);
        friendButton.setPreferredSize(preferredSize);
        personalInformationButton.setPreferredSize(preferredSize);
        historicalAchievementsButton.setPreferredSize(preferredSize);
        logOutButton.setPreferredSize(preferredSize);
        exitTheGameButton.setPreferredSize(preferredSize);
        //设置颜色
        startGameButton.setBackground(Color.LIGHT_GRAY);
        friendButton.setBackground(Color.LIGHT_GRAY);
        personalInformationButton.setBackground(Color.LIGHT_GRAY);
        historicalAchievementsButton.setBackground(Color.LIGHT_GRAY);
        logOutButton.setBackground(Color.LIGHT_GRAY);
        exitTheGameButton.setBackground(Color.LIGHT_GRAY);

    }

    public void setButtonPosition() {
        int padding = 80;
        //设置位置
        SpringLayout.Constraints startGame = springLayout.getConstraints(startGameButton);
        startGame.setX(Spring.constant(140));
        startGame.setY(Spring.constant(300));
        SpringLayout.Constraints friend = springLayout.getConstraints(friendButton);
        friend.setX(Spring.constant(startGame.getX().getPreferredValue()));
        friend.setY(Spring.constant(startGame.getY().getPreferredValue() + padding));
        SpringLayout.Constraints personalInformation = springLayout.getConstraints(personalInformationButton);
        personalInformation.setX(Spring.constant(friend.getX().getPreferredValue()));
        personalInformation.setY(Spring.constant(friend.getY().getPreferredValue() + padding));
        SpringLayout.Constraints historicalAchievenment = springLayout.
                getConstraints(historicalAchievementsButton);
        historicalAchievenment.setX(Spring.constant(personalInformation.getX().getPreferredValue()));
        historicalAchievenment.setY(Spring.constant(personalInformation.getY().getPreferredValue() + padding));
        SpringLayout.Constraints logOut = springLayout.getConstraints(logOutButton);
        logOut.setX(Spring.constant(historicalAchievenment.getX().getPreferredValue()));
        logOut.setY(Spring.constant(historicalAchievenment.getY().getPreferredValue() + padding));
        SpringLayout.Constraints exitTheGame = springLayout.getConstraints(exitTheGameButton);
        exitTheGame.setX(Spring.constant(logOut.getX().getPreferredValue()));
        exitTheGame.setY(Spring.constant(logOut.getY().getPreferredValue() + padding));
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
        ImageIcon iconImage = ImageUtility.getImageIcon("images/游戏界面图.jpg");
        iconImage.setImage(iconImage.getImage().getScaledInstance(600, 900, Image.SCALE_DEFAULT));
        // 设置标签组件要显示的图标
        lblBackground.setIcon(iconImage);
        jPanel.add(lblBackground);
        //设置大小
        setSize(600, 900);
        //固定窗口大小
        setResizable(false);
        //屏幕居中
        setLocationRelativeTo(null);
        //关闭窗口
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addEvent() {
        startGameButtonEvent();
        exitTheGameButtonEvent();
        logOutEvent();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MessageUtility.close(ClientManage.socket);
            }
        });
    }

    public void startGameButtonEvent() {
        startGameButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取在线用户空闲列表
                Message message = new Message(MessageType.FREE_USER);
                message.setSender(ClientManage.user.getName());
                MessageUtility.sendMessage(ClientManage.socket,message);
                JOptionPane.showMessageDialog(null, "请选择空闲的在线用户进行对战....！",
                        "消息提示", JOptionPane.WARNING_MESSAGE);
                //输入对话框
                String [] options = ClientManage.freeUserList.toArray(new String[0]);
                String rival =  (String)JOptionPane.showInputDialog(null,
                        "选择游戏对手：","提示",JOptionPane.QUESTION_MESSAGE,
                        null,options,"请选择");
                if (rival != null) {
                    //发送对战请求
                    message = new Message(ClientManage.user.getName(), rival, MessageType.PLAY_GAME);
                    MessageUtility.sendMessage(ClientManage.socket, message);
                    message = new Message(rival, MessageType.GIVE_RIVAL_INFO);
                    MessageUtility.sendMessage(ClientManage.socket, message);
//                    checkerBoardView = ManageViewAssembly.checkerBoardView;

                }
            }
        });
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void logOutEvent(){
        logOutButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                CustomDialog_Confim confim = new CustomDialog_Confim("退出","是否确认登出账号");
                if (confim.isComfir()) {
                    MessageUtility.close(socket);
                    loginView.setVisible(true);
                }else {
                    setVisible(true);
                }
            }
        });
    }
    public void exitTheGameButtonEvent() {
        exitTheGameButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomDialog_Confim customDialog = new CustomDialog_Confim("look me","确认退出吗");
                if (customDialog.isComfir()){
                    MessageUtility.close(socket);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                System.exit(0);
            }
        });
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) {
        new GameView();
    }
}
