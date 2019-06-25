package com.example.demo.module.entity;

public class User {
    private int id;
    private String usrname;

    public Integer getId() {
        return id;
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