package message;

import message.Color;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/01/20:39
 * @Description: 为人所不为, 能人所不能
 */
public class Message implements Serializable {
    private String sender;
    private String getter;
    private Date senderTime;
    private int[][] table;
    private int nextX;
    private int nextY;
    private String messageType;
    private User user;
    private Color color;
    private Set<String> set;
    private static final long serialVersionUID =1L;

    public Message(String getter, String messageType) {
        this.getter = getter;
        this.messageType = messageType;
    }

    public Message(String messageType, Set<String> set) {
        this.messageType = messageType;
        this.set = set;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Message(int nextX, int nextY, String messageType, Color color) {
        this.nextX = nextX;
        this.nextY = nextY;
        this.messageType = messageType;
        this.color = color;
    }

    public Message(String sender, String getter, int nextX, int nextY, String messageType, Color color) {
        this.sender = sender;
        this.getter = getter;
        this.nextX = nextX;
        this.nextY = nextY;
        this.messageType = messageType;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Message(){}
    public Message(String sender, String messageType, User user) {
        this.sender = sender;
        this.messageType = messageType;
        this.user = user;
    }

    public Message(User user,String messageType) {
        this.user = user;
        this.messageType = messageType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public Date getSenderTime() {
        return senderTime;
    }

    public void setSenderTime(Date senderTime) {
        this.senderTime = senderTime;
    }

    public int[][] getTable() {
        return table;
    }

    public void setTable(int[][] table) {
        this.table = table;
    }

    public int getNextX() {
        return nextX;
    }

    public void setNextX(int nextX) {
        this.nextX = nextX;
    }

    public int getNextY() {
        return nextY;
    }

    public void setNextY(int nextY) {
        this.nextY = nextY;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;
    {
        senderTime = new Date();
    }
    public Message(String sender, String getter, String messageType) {
        this.sender = sender;
        this.getter = getter;
        this.messageType = messageType;
    }

    public Message(String sender, String getter, String messageType, String content) {
        this.sender = sender;
        this.getter = getter;
        this.messageType = messageType;
        this.content = content;
    }

    public Message(String sender, String getter, int[][] table, int nextX,
                   int nextY, String messageType) {
        this.sender = sender;
        this.getter = getter;
        this.table = table;
        this.nextX = nextX;
        this.nextY = nextY;
        this.messageType = messageType;
    }

    public Message(String sender, String getter, Date senderTime, int[][] table,
                   int nextX, int nextY, String messageType, String content) {
        this.sender = sender;
        this.getter = getter;
        this.senderTime = senderTime;
        this.table = table;
        this.nextX = nextX;
        this.nextY = nextY;
        this.messageType = messageType;
        this.content = content;
    }

    public Message(String messageType) {
        this.messageType = messageType;
    }
}
