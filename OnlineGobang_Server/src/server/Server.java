package server;

import database.dao.UserDAO;
import message.User;
import message.Message;
import message.MessageType;
import utility.MessageUtility;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/01/20:15
 * @Description: 为人所不为, 能人所不能
 */
public class Server extends Thread{
    private ServerSocket serverSocket;

    {
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        new Server().start();
    }


    @Override
    public void run() {
        while (true){
            try {
                System.out.println("服务器在8888端口监听");
                Socket socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message)ois.readObject();
                //注册
                if (message.getMessageType().equals(MessageType.REGISTER)){
                    User user = message.getUser();
                    UserDAO userDAO = new UserDAO();
                    Message message1 = new Message();
                    if (!userDAO.isExist(user)){
                        message1.setMessageType(MessageType.FAIL_REGISTER);
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        //将数据发送到服务器端进行验证
                        oos.writeObject(message1);
                    }else {
                        message1.setMessageType(MessageType.SUCCEED_REGISTER);
                        System.out.println("用户:"+user.getName()+"注册成功!!!");
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        //将数据发送到服务器端进行验证
                        oos.writeObject(message1);
                    }
                    socket.close();

                    //登录
                }else if (message.getMessageType().equals(MessageType.LOG_IN)){
                    System.out.println("登录");
                    User user = message.getUser();
                    UserDAO userDAO = new UserDAO();
                    Message message1 = new Message();
                    if (userDAO.isLegitimate(user)){

                        if (ManageClientThreads.isOnline(user.getName())){
                            System.out.println("登录失败");
                            message1.setMessageType(MessageType.REPEAT_LOGIN);
                            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                            //将数据发送到服务器端进行验证
                            oos.writeObject(message1);
                            socket.close();
                            System.out.println(ManageClientThreads.getAllOnlineUser("jack"));
                        }else {
                            message1.setMessageType(MessageType.SUCCEED_LOG_IN);
                            //启动线程保持与客户端连接
                            ServerConnectServerThread serverConnectServerThread = new ServerConnectServerThread(user.getName(), socket);
                            serverConnectServerThread.start();
                            //将线程加入集合，方便管理
                            ManageClientThreads.addThread(user.getName(), serverConnectServerThread);
                            ManageClientThreads.addFreeUser(user.getName());
                            System.out.println(user.getName() + "登录成功");
                            System.out.println(ManageClientThreads.getAllOnlineUser("jack"));
                            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                            oos.writeObject(message1);
                        }
                    }else {
                        System.out.println("登录失败");
                        message1.setMessageType(MessageType.FAIL_LOG_IN);
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        //将数据发送到服务器端进行验证
                        oos.writeObject(message1);
                        socket.close();
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
