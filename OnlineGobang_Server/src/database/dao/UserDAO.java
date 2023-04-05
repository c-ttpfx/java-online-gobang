package database.dao;

import message.User;


/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/01/20:13
 * @Description: 为人所不为, 能人所不能
 */
public class UserDAO extends BasicDAO<User>{
    public boolean isExist(User user){
        String sql = "select * from user where name = ?";
        System.out.println(user.getName());
        User user1 = querySingle(sql, User.class, user.getName());
        System.out.println(user1);
        if (user1 == null){
            sql = "insert into user values(null,?,?,?,now())";
            int k = update(sql,user.getName(),user.getPassword(),user.getSex());
            System.out.println("注册成功"+k);
            return k > 0;
        }
        System.out.println("注册不成功");
        return false;
    }
    public boolean isLegitimate(User user){
        String sql = "select * from user where name = ? and password = ?";
        User user1 = super.querySingle(sql, User.class, user.getName(), user.getPassword());
        if (user1 == null) {
            return false;
        }else {
            return true;
        }
    }
    public User getUser(String name){
        String sql = "select * from user where name = ?";
        System.out.println(name);
        User user1 = querySingle(sql, User.class,name);
        return user1;
    }
}
