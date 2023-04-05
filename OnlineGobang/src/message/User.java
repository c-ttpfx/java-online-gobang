package message;

import java.io.Serializable;
import java.util.Date;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/01/19:50
 * @Description: 为人所不为, 能人所不能
 */
public class User implements Serializable {
    private Integer id;
    private String name;
    private String password;
    private String sex;
    private Date register_date;

    public User(){}

    public User(Integer id, String name, String password, String sex, Date register_date) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.register_date = register_date;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
