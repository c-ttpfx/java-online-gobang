package clientUserService;

import Utility.MessageUtility;
import data.ChessDate;
import message.Message;
import message.MessageType;
import view.CheckerBoardView;
import view.ChessView;
import view.ManageViewAssembly;


import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/01/23:02
 * @Description: 为人所不为, 能人所不能
 */
public class ClientConnectServiceThread extends Thread {
    private Socket socket;

    public ClientConnectServiceThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message)ois.readObject();
                //成功关闭
                if (message.getMessageType().equals(MessageType.SUCCEED_CLOSE)){
                    socket.close();
                    System.out.println("socket关闭成功");
                    break;
                    //下一步棋
                }else if (message.getMessageType().equals(MessageType.PLAY_CHESS_NEXT_SUCCEED)){
                    ChessDate.updateTable(message.getNextX(),message.getNextY(),message.getColor());
                    System.out.println("对方走"+message.getNextX()+" "+message.getNextY());
                    ManageViewAssembly.jButtons[message.getNextY()][message.getNextX()].setVisible(false);
//                    ChessView.jButtons[message.getNextX()][message.getNextY()].setVisible(false);
                    ManageViewAssembly.chessButton.setVisible(true);
                    //个人信息
                }else if (message.getMessageType().equals(MessageType.GIVE_MYSELF_INFO_SUCCEED)){
                    ClientManage.user = message.getUser();
                    //对手信息
                }else if (message.getMessageType().equals(MessageType.GIVE_RIVAL_INFO_SUCCEED)){
                    ClientManage.rivalUser = message.getUser();
                    //在线列表
                }else if (message.getMessageType().equals(MessageType.GIVE_ONLINE_USER_LIST_SUCCEED)){
                    ClientManage.onlineUserList = message.getSet();
                    System.out.println(ClientManage.onlineUserList.toString());
                    //棋子颜色
                }else if (message.getMessageType().equals(MessageType.SET_PRICE_COLOR)){
                    System.out.println("我是"+message.getColor());
                    ChessDate.color = message.getColor();
                    //游戏开始
                }else if (message.getMessageType().equals(MessageType.PLAY_GAME_SUCCEED)) {
                    System.out.println("对方同意");
                    ManageViewAssembly.wait = false;
                    System.out.println(ManageViewAssembly.wait);
                    JOptionPane.showMessageDialog(null, "对方同意开始游戏！", "消息提示"
                            , JOptionPane.WARNING_MESSAGE);
                    ManageViewAssembly.isPlayGame = true;
                    if (ManageViewAssembly.isPlayGame) {
                        message.setMessageType(MessageType.GAME_START);
                        MessageUtility.sendMessage(ClientManage.socket,message);
                        //开始游戏
                        message.setMessageType(MessageType.SET_PRICE_COLOR);
                        MessageUtility.sendMessage(ClientManage.socket, message);
                        ManageViewAssembly.gameView.setVisible(false);
                        ManageViewAssembly.checkerBoardView = new CheckerBoardView();
                        ManageViewAssembly.checkerBoardView.setVisible(true);
                    }
                    //对手拒绝游戏
                }else if (message.getMessageType().equals(MessageType.PLAY_GAME_FAIL)){
                    System.out.println("对方不同意");
                    JOptionPane.showMessageDialog(null, "对方拒绝和您游戏！", "消息提示"
                            , JOptionPane.ERROR_MESSAGE);
                    //对手同意开始
                }else if (message.getMessageType().equals(MessageType.PLAY_GAME)) {
                    invite(message);
                    //对手逃跑或者关闭窗口
                }else if (message.getMessageType().equals(MessageType.PLAY_GAME_RIVAL_CLOSE)){
                    ManageViewAssembly.gameView.setVisible(true);
                    ManageViewAssembly.checkerBoardView.setVisible(false);
                    JOptionPane.showMessageDialog(null, "对方认输,您获得胜利！",
                            "消息提示", JOptionPane.WARNING_MESSAGE);
                    ChessDate.reset();
                    message.setMessageType(MessageType.GAME_OVER);
                    MessageUtility.sendMessage(ClientManage.socket,message);
                }else if (message.getMessageType().equals(MessageType.GAME_OVER_VICTORY)){
                    JOptionPane.showMessageDialog(null, "恭喜您,获得胜利！",
                            "消息提示", JOptionPane.WARNING_MESSAGE);
                    ChessDate.reset();
                    ManageViewAssembly.gameView.setVisible(true);
                    ManageViewAssembly.checkerBoardView.setVisible(false);
                    message.setMessageType(MessageType.GAME_OVER);
                    MessageUtility.sendMessage(ClientManage.socket,message);
                }else if (message.getMessageType().equals(MessageType.GAME_OVER_FAIL)){
                    JOptionPane.showMessageDialog(null, "你输掉了游戏！",
                            "消息提示", JOptionPane.WARNING_MESSAGE);
                    ChessDate.reset();
                    ManageViewAssembly.gameView.setVisible(true);
                    ManageViewAssembly.checkerBoardView.setVisible(false);
                    message.setMessageType(MessageType.GAME_OVER);
                    MessageUtility.sendMessage(ClientManage.socket,message);
                }else if (message.getMessageType().equals(MessageType.ALL_USER_STATE)){
                    ClientManage.onlineUserStateList = message.getSet();
                }else if (message.getMessageType().equals(MessageType.FREE_USER)){
                    ClientManage.freeUserList = message.getSet();
                    ClientManage.freeUserList.remove(ClientManage.user.getName());
                }else if (message.getMessageType().equals(MessageType.GAME_USER)){
                    ClientManage.inGameUserList = message.getSet();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "游戏异常!!!!",
                        "消息提示", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void invite(Message mess){
        int userOption =  JOptionPane.showConfirmDialog(null,
                "接受"+mess.getSender()+"游戏邀请吗？",
                "提示",JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE);
        Message message;
        if (userOption == JOptionPane.OK_OPTION){
            ManageViewAssembly.gameView.setVisible(false);
            ManageViewAssembly.checkerBoardView = new CheckerBoardView();
            ManageViewAssembly.checkerBoardView.setVisible(true);
            ManageViewAssembly.chessButton.setVisible(false);
            message = new Message(mess.getGetter(),mess.getSender(),MessageType.SET_PRICE_COLOR);
            MessageUtility.sendMessage(socket,message);
            message.setMessageType(MessageType.GAME_START);
            MessageUtility.sendMessage(ClientManage.socket,message);
            message.setMessageType(MessageType.PLAY_GAME_SUCCEED);
        }else {
            message = new Message(mess.getGetter(),mess.getSender(),MessageType.PLAY_GAME_FAIL);
        }
        MessageUtility.sendMessage(socket,message);
        message.setMessageType(MessageType.GIVE_RIVAL_INFO);
        MessageUtility.sendMessage(socket,message);

    }
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
