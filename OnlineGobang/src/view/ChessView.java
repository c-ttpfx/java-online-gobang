package view;

import Utility.ImageUtility;
import Utility.MessageUtility;
import clientUserService.ClientManage;
import data.ChessDate;
import message.Message;
import message.MessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/02/19:14
 * @Description: 为人所不为, 能人所不能
 */
public class ChessView extends JPanel {
    public static JButton[][] jButtons;
    private Image image;
    private Socket socket;
    private JPanel panel;

    public ChessView(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void paint(Graphics g) {
//        settingCheckJPanel();
        g.drawImage(image, 0, 0, this);
        drawChessboardPieces(g);
    }

    public ChessView() {
        settingCheckJPanel();

    }

    public void settingCheckJPanel() {
        this.setBackground(Color.RED);
        SpringLayout springLayout = new SpringLayout();
        panel = new JPanel();
        ManageViewAssembly.chessButton = panel;
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                repaint();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }
        });

        //获取背景图片路径
        ImageIcon imageIcon = ImageUtility.getImageIcon("images/棋盘.jpg");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(920, 920, Image.SCALE_DEFAULT));
        image = imageIcon.getImage();

        setLayout(springLayout);
        //指定面板的布局为GridLayout，15行15列，间隙为10
        panel.setLayout(new GridLayout(15, 15, 10, 10));
        initialJButton(15, 15);
        for (int i = 0; i < jButtons.length; i++) {
            for (int j = 0; j < jButtons[0].length; j++) {
                panel.add(jButtons[i][j]);
            }
        }
        SpringLayout.Constraints chess = springLayout.getConstraints(panel);
        chess.setX(Spring.constant(20));
        chess.setY(Spring.constant(20));
        add(panel);
    }


//    public static void main(String[] args) {
//        JFrame jFrame = new JFrame();
//        jFrame.setSize(1000, 1000);
//        ChessView chessView = new ChessView();
//        jFrame.add(chessView);
//        jFrame.setVisible(true);
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }

    public JButton getJButton(int width, int height) {
        JButton jButton = new JButton("");
        jButton.setPreferredSize(new Dimension(width, height));
        jButton.setBackground(Color.CYAN);
        jButton.setVisible(true);
        jButton.setOpaque(false);
        jButton.setContentAreaFilled(false);
        jButton.setBorderPainted(false);
        setEnabled(false);
        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton.setVisible(false);

                ChessDate.updateTable(jButton.getX() / 60, jButton.getY() / 60, ChessDate.color);
                panel.setVisible(false);

                Message mess = new Message(ClientManage.user.getName(),
                        ClientManage.rivalUser.getName(), jButton.getX() / 60, jButton.getY() / 60,
                        MessageType.PLAY_CHESS_NEXT, ChessDate.color);
                MessageUtility.sendMessage(ClientManage.socket, mess);
                int color = ChessDate.color == message.Color.BLACK ? 2 : 1;
                System.out.println("颜色  "+color);
                if (ChessDate.checkerBoard.judge(ChessDate.getTable(),color)){
                    mess.setMessageType(MessageType.GAME_OVER_VICTORY);
                    MessageUtility.sendMessage(ClientManage.socket,mess);
                }
                System.out.println(("设置  " + jButton.getX() / 60) + " " + (jButton.getY() / 60) + " ");
            }
        });
        return jButton;
    }

    //    public static void main(String[] args) {
//        JFrame jFrame = new JFrame();
//        jFrame.add(new ChessView());
//        jFrame.setSize(1000,1000);
//        jFrame.setVisible(true);
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    }
    public void initialJButton(int rows, int cols) {
        jButtons = new JButton[rows][cols];
        for (int i = 0; i < jButtons.length; i++) {
            for (int j = 0; j < jButtons[0].length; j++) {
                jButtons[i][j] = getJButton(50, 50);
            }
        }
        ManageViewAssembly.jButtons = jButtons;
    }

    public void drawChessboardPieces(Graphics graphics) {
        int[][] table = ChessDate.getTable();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j] == 2) {
//                    System.out.println("画  "+i*60+" "+j*60);
                    graphics.setColor(Color.BLACK);
                    graphics.fillOval(i * 60 + 20, j * 60 + 20, 50, 50);
                } else if (table[i][j] == 1) {
                    graphics.setColor(Color.WHITE);
                    graphics.fillOval(i * 60 + 20, j * 60 + 20, 50, 50);
                }
            }
        }
    }
}
