package com.wingcfly.uber;

public class User {
    public String email;
    public String password;
    public String phonenumber;
    public String humanname;

    public User(){}

    public User(String email, String password, String phonenumber, String humanname) {
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        this.humanname = humanname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getHumanname() {
        return humanname;
    }

    public void setHumanname(String humanname) {
        this.humanname = humanname;
    }
}
