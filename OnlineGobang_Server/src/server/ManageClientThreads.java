package server;

import javax.print.attribute.HashDocAttributeSet;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/01/20:27
 * @Description: 为人所不为, 能人所不能
 */
public class ManageClientThreads {
    private static HashMap<String,ServerConnectServerThread> hm = new HashMap<>();
    private static HashSet<String > inGame = new HashSet();
    private static HashSet<String > free = new HashSet();

    public static void addThread(String username, ServerConnectServerThread serverConnectServerThread){
        hm.put(username, serverConnectServerThread);
    }
    public static void removeThread(String username){
        hm.remove(username);
    }
    public static HashSet<String> getAllOnlineUser(String name){
        Set<String> set = hm.keySet();
        HashSet<String> hashSet = new HashSet(set);
        hashSet.remove(name);
        return hashSet;
    }
    public static ServerConnectServerThread getServerConnectServerThread(String userName){
        return hm.get(userName);
    }
    public static boolean isOnline(String username){
        return hm.containsKey(username);
    }

    public static HashSet<String> getSoOnlineUserStatus(){
        HashSet<String> hashSet = new HashSet();
        for (String s : free) {
            s = s + " [空闲]";
            hashSet.add(s);
        }
        for (String s : inGame) {
            s = s + " [游戏中]";
            hashSet.add(s);
        }
        return hashSet;
    }

    public static HashSet<String> getAllFreeUser(){
        return new HashSet<>(free);
    }
    public static HashSet<String> getInGameUser(){
        return new HashSet<>(free);
    }
    public static void addInGameUser(String userName){
        inGame.add(userName);
    }
    public static void removeInGameUser(String userName){
        inGame.remove(userName);
    }
    public static void addFreeUser(String userName){
        free.add(userName);
    }
    public static void removeFreeUser(String userName){
        free.remove(userName);
    }
}
