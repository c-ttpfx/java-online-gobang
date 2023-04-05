package view;

import Utility.ImageUtility;
import clientUserService.ClientUserServer;
import message.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/01/11:17
 * @Description: 为人所不为, 能人所不能
 */
public class RegisterView extends JFrame {
    private User user = new User();
    private String title = "为人所不为,能人所不能";
    private JLabel titleLable;
    private JLabel nameLable;
    private JLabel passwordLable;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton returnButton;
    private SpringLayout springLayout;
    private JPanel jPanel;
    private Container contentPane;
    private String sex = "男";
    private ButtonGroup buttonGroup;
    private JRadioButton male;
    private JRadioButton female;
    private JLabel gender;
    private StartView startView;
    private RegisterView registerView;

    public void setRegisterView(RegisterView registerView) {
        this.registerView = registerView;
    }

    public RegisterView(StartView startView) {
        super("注册界面");
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
        jPanel.add(gender);
        jPanel.add(male);
        jPanel.add(female);
        jPanel.add(registerButton);
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
        springLayout.putConstraint(SpringLayout.NORTH, nameLable, 80, SpringLayout.NORTH, jPanel);

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
        //性别选项
        springLayout.putConstraint(SpringLayout.WEST, gender, 0,
                SpringLayout.WEST, passwordLable);
        springLayout.putConstraint(SpringLayout.NORTH, gender, 60,
                SpringLayout.NORTH, passwordLable);
        //性别按钮
        springLayout.putConstraint(SpringLayout.WEST, male, 60,
                SpringLayout.EAST, gender);
        springLayout.putConstraint(SpringLayout.NORTH, male, 0,
                SpringLayout.NORTH, gender);
        springLayout.putConstraint(SpringLayout.WEST, female, 40,
                SpringLayout.EAST, male);
        springLayout.putConstraint(SpringLayout.NORTH, female, 0,
                SpringLayout.NORTH, male);
        //按钮
        springLayout.putConstraint(SpringLayout.WEST, registerButton, 150,
                SpringLayout.WEST, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, registerButton, 100,
                SpringLayout.NORTH, gender);
        springLayout.putConstraint(SpringLayout.EAST, returnButton, -150,
                SpringLayout.EAST, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, returnButton, 0,
                SpringLayout.NORTH, registerButton);

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

        //性别选项框
        buttonGroup = new ButtonGroup();
        ImageIcon imageIcon1 = ImageUtility.getImageIcon("images/男.jpg");
        ImageIcon imageIcon2 = ImageUtility.getImageIcon("images/女.jpg");
        male = new JRadioButton("男",imageIcon1,true);
        male.setBackground(Color.CYAN);
        female = new JRadioButton("女",imageIcon2);
        buttonGroup.add(male);
        buttonGroup.add(female);
        gender = new JLabel("性   别");
        gender.setFont(font);
        Font font1 = new Font("宋体", Font.PLAIN, 30);
        male.setFont(font1);
        female.setFont(font1);

        Dimension sexButtonSize = new Dimension(100, 50);

        male.setPreferredSize(sexButtonSize);
        female.setPreferredSize(sexButtonSize);
//        male.setBackground(Color.gray);
//        female.setBackground(Color.pink);
        // 注意,单选按钮组不能添加进容器
        buttonGroup.add(male);
        buttonGroup.add(female);

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
        registerButton = new JButton("注册");
        returnButton = new JButton("返回");
        registerButton.setFont(buttonFont);
        returnButton.setFont(buttonFont);
        Dimension preferredSize = new Dimension(250, 50);
        registerButton.setPreferredSize(preferredSize);
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
        registerButtonEvent();
        returnButtonEvent();
        sexEvent();
    }

    public void registerButtonEvent() {
        registerButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setName(nameField.getText());
                user.setPassword(new String(passwordField.getPassword()));
                user.setSex(sex);
                ClientUserServer clientUserServer = new ClientUserServer(user);
                boolean register = clientUserServer.checkRegister();
                if (!register){
                    JOptionPane.showMessageDialog(null, "用户名不合法,注册失败!!!",
                            "注册失败",JOptionPane.ERROR_MESSAGE);
//                    String inputValue = JOptionPane.showInputDialog("请输入你给我金额");
                }else {
                    JOptionPane.showMessageDialog(null, "恭喜您,注册成功! 快去登录吧",
                            "注册成功",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println("注册信息为:"+user.getName()+" "+user.getPassword()+" "+sex);
            }
        });
    }
    public void sexEvent(){
        AbstractAction action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == female) {
                    female.setBackground(Color.pink);
                    male.setBackground(null);
                    sex = "女";
                }else {
                    male.setBackground(Color.CYAN);
                    female.setBackground(null);
                    sex = "男";
                }
            }
        };
        male.addActionListener(action);
        female.addActionListener(action);
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
