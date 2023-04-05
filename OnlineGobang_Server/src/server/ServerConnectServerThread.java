package server;

import database.dao.UserDAO;
import message.Color;
import message.Message;
import message.MessageType;
import utility.JDBCUtility;
import utility.MessageUtility;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/01/20:28
 * @Description: 为人所不为, 能人所不能
 */
public class ServerConnectServerThread extends Thread{
    private String username;
    private Socket socket;
    private UserDAO userDAO = new UserDAO();
    public ServerConnectServerThread(String username,Socket socket){
        this.username = username;
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        while (true){
            try {
//                System.out.println(username+"socket监听中");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message mess = (Message)ois.readObject();
                System.out.println(mess.getMessageType());
                if (mess.getMessageType().equals(MessageType.CLOSE)){
                    ManageClientThreads.removeThread(username);
                    System.out.println(username+"  socket正常关闭");
//                    mess.setMessageType(MessageType.SUCCEED_CLOSE);
//                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//                    //将数据发送到服务器端进行验证
//                    oos.writeObject(mess);
//                    oos.close();
                    socket.close();
                    rivalClose(mess.getGetter());
                    break;
                    //游戏开始
                }else if (mess.getMessageType().equals(MessageType.PLAY_GAME)){
                    MessageUtility.sendMessage(ManageClientThreads.
                            getServerConnectServerThread(mess.getGetter()).socket,mess);
                    //游戏开始成功
                }else if (mess.getMessageType().equals(MessageType.PLAY_GAME_SUCCEED)){
                    MessageUtility.sendMessage(ManageClientThreads.
                            getServerConnectServerThread(mess.getGetter()).socket,mess);
                    //游戏开始失败
                }else if (mess.getMessageType().equals(MessageType.PLAY_GAME_FAIL)){
                    MessageUtility.sendMessage(ManageClientThreads.
                            getServerConnectServerThread(mess.getGetter()).socket,mess);
                    //下一步棋
                }else if (mess.getMessageType().equals(MessageType.PLAY_CHESS_NEXT)){
                    mess.setMessageType(MessageType.PLAY_CHESS_NEXT_SUCCEED);
                    Socket socket1 = ManageClientThreads.
                            getServerConnectServerThread(mess.getGetter()).socket;
                    if (socket1 == null) {
                        System.out.println("获胜");
                        mess.setMessageType(MessageType.PLAY_GAME_RIVAL_CLOSE);
                        MessageUtility.sendMessage(socket,mess);
                    }else {
                        MessageUtility.sendMessage(socket1, mess);
                    }
                    //发送用户个人信息
                }else if (mess.getMessageType().equals(MessageType.GIVE_MYSELF_INFO)){
                    mess.setMessageType(MessageType.GIVE_MYSELF_INFO_SUCCEED);
                    mess.setUser(userDAO.getUser(mess.getSender()));
                    MessageUtility.sendMessage(socket,mess);
                    //发送用户对手信息
                }else if (mess.getMessageType().equals(MessageType.GIVE_RIVAL_INFO)){
                    mess.setMessageType(MessageType.GIVE_RIVAL_INFO_SUCCEED);
                    mess.setUser(userDAO.getUser(mess.getGetter()));
                    MessageUtility.sendMessage(socket,mess);
                    //发送在线列表
                }else if (mess.getMessageType().equals(MessageType.GIVE_ONLINE_USER_LIST)){
                    mess.setMessageType(MessageType.GIVE_ONLINE_USER_LIST_SUCCEED);
                    mess.setSet(ManageClientThreads.getAllOnlineUser(mess.getSender()));
                    System.out.println(mess.getSet().toString());
                    MessageUtility.sendMessage(socket,mess);
                    //发送游戏双方的棋子颜色
                }else if (mess.getMessageType().equals(MessageType.SET_PRICE_COLOR)){
                    mess.setColor(Color.BLACK);
                    MessageUtility.sendMessage(socket,mess);
                    mess.setColor(Color.WHITE);
                    MessageUtility.sendMessage(ManageClientThreads.
                                    getServerConnectServerThread(mess.getGetter()).socket,mess);
                    //游戏正常结束
                }else if (mess.getMessageType().equals(MessageType.GAME_OVER_VICTORY)){
                    MessageUtility.sendMessage(socket,mess);
                    mess.setMessageType(MessageType.GAME_OVER_FAIL);
                    MessageUtility.sendMessage(ManageClientThreads.
                            getServerConnectServerThread(mess.getGetter()).socket,mess);
                }else if (mess.getMessageType().equals(MessageType.FREE_USER)){
                    mess.setSet(ManageClientThreads.getAllFreeUser());
                    MessageUtility.sendMessage(socket,mess);
                }else if (mess.getMessageType().equals(MessageType.GAME_USER)){
                    mess.setSet(ManageClientThreads.getInGameUser());
                    MessageUtility.sendMessage(socket,mess);
                }else if (mess.getMessageType().equals(MessageType.ALL_USER_STATE)){
                    mess.setSet(ManageClientThreads.getSoOnlineUserStatus());
                    MessageUtility.sendMessage(socket,mess);
                }else if (mess.getMessageType().equals(MessageType.GAME_START)){
                    ManageClientThreads.addInGameUser(username);
                    ManageClientThreads.removeFreeUser(username);
                }else if (mess.getMessageType().equals(MessageType.GAME_OVER)){
                    ManageClientThreads.addFreeUser(username);
                    ManageClientThreads.removeInGameUser(username);
                }
            } catch (Exception e) {
                try {
                    ManageClientThreads.removeThread(username);
                    ManageClientThreads.removeFreeUser(username);
                    ManageClientThreads.removeInGameUser(username);
                    System.out.println(username+"  socket异常关闭");
                    socket.close();
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public void rivalClose(String rivalName){
        Socket socket = ManageClientThreads.getServerConnectServerThread(rivalName).socket;
        Message message = new Message(MessageType.PLAY_GAME_RIVAL_CLOSE);
        MessageUtility.sendMessage(socket,message);
    }
}
