package com.example.demo.module.entity;

public class User {
    private int id;
    private String usrname;
    private String passowrd;

    public Integer getId() {
        return id;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}