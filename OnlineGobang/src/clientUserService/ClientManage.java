package clientUserService;

import message.User;

import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/03/0:21
 * @Description: 为人所不为, 能人所不能
 */
public class ClientManage {
    public static Socket socket;
    public static User user;
    public static Set<String> onlineUserList = new HashSet<String>(){{add("樱岛麻衣");add("亚丝娜");
        add("绘梨衣");}};
    public static User rivalUser;
    public static Set<String> onlineUserStateList;
    public static Set<String> freeUserList;
    public static Set<String> inGameUserList;
}
