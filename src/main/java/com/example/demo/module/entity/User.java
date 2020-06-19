package com.example.demo.module.entity;

import com.alibaba.excel.annotation.ExcelProperty;

public class User {
    // private int id;
    @ExcelProperty(value = "姓名" ,index = 0)
    private String usrname;
    @ExcelProperty(value = "密码" ,index = 1)
    private String password;

  
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }
    // public Integer getId() {
    //     return id;
    // }

    // public void setId(Integer id) {
    //     this.id = id;
    // }
}