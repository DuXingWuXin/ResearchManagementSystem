package Model.entity;

import java.util.Objects;

public class User
{
    // 用户账号
    private String userid;
    // 用户密码
    private String password;
    // 用户角色
    private int user_role;

    // userid 的 get set 函数
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }

    // password 的 get set 函数
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // user_role 的 get set函数
    public void setUser_role(int role) {this.user_role = role;}
    public int getUser_role() {return this.user_role;}

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userid, user.userid) && Objects.equals(password, user.password) && Objects.equals(user_role, user.user_role);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userid, password, user_role);
    }

    @Override
    public String toString()
    {
        return "User {" +
                "userid = " + this.userid + '\'' +
                "password = " + this.password + '\'' +
                "user_role = " + this.user_role +
                '}';
    }

}
