package Utility;

import message.Message;
import message.MessageType;
import message.User;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/01/22:27
 * @Description: 为人所不为, 能人所不能
 */
public class MessageUtility {
    public static void close(Socket socket){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Message message = new Message();
            message.setMessageType(MessageType.CLOSE);
            oos.writeObject(message);
            System.out.println("关闭报文发送成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void close(Socket socket,String rival){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Message message = new Message();
            message.setGetter(rival);
            message.setMessageType(MessageType.CLOSE);
            oos.writeObject(message);
            System.out.println("关闭报文发送成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void sendMessage(Socket socket,Message message){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //将数据发送到服务器端进行验证
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Message getMessage(Socket socket){
        Message message = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            message = (Message)ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }
}
