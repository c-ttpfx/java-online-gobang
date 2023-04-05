package clientUserService;

import Utility.MessageUtility;
import message.Message;
import message.MessageType;
import message.User;

import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/01/22:52
 * @Description: 为人所不为, 能人所不能
 */
public class ClientUserServer {
    private User user;
    private Socket socket;



    public ClientUserServer(User user){
        this.user = user;
    }
    public int checkLogIn(){
        /*
         *   0表示服务器同意登录,1表示重复登录,2表示用户名或者密码错误
        */
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("src\\server.properties"));
            // 获取服务器的ip和端口
            String ip = properties.getProperty("ip");
            String port = properties.getProperty("port");

            socket = new Socket(ip, Integer.parseInt(port));
            Message messageLogIn = new Message(user, MessageType.LOG_IN);
            MessageUtility.sendMessage(socket,messageLogIn);

            //服务器端返回验证消息
            Message message = MessageUtility.getMessage(socket);
//            System.out.println(message.getMessageType());
            if (message.getMessageType().equals(MessageType.SUCCEED_LOG_IN)) {
                System.out.println("验证成功");
                ClientManage.user = user;
                //启动该用户线程
                ClientConnectServiceThread clientConnectServiceThread = new ClientConnectServiceThread(socket);
                clientConnectServiceThread.start();
                return 0;
            } else if (message.getMessageType().equals(MessageType.REPEAT_LOGIN)){
                //登录失败,将Socket关闭
                socket.close();
                return 1;
            }else {
                socket.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 2;
    }

    public boolean checkRegister(){
        try {
            socket = new Socket(InetAddress.getLocalHost(),8888);
            ClientManage.socket = socket;
            Message messageRegister = new Message(user, MessageType.REGISTER);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //将数据发送到服务器端进行验证
            oos.writeObject(messageRegister);

            //服务器端返回验证消息
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();

            if (message.getMessageType().equals(MessageType.SUCCEED_REGISTER)) {
                return true;
            } else {
                //登录失败,将Socket关闭
                socket.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
