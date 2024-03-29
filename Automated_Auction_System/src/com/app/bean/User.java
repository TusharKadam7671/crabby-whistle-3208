package com.app.bean;

public class User {

    private int userid;
    private String username;
    private String password;

    private Type type;

    public User() {

    }

    public User(int userid, String username, String password, Type type) {
        super();
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", type=" + type + "]";
    }

}
