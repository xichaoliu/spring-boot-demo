package com.example.demo.module.dao;

import java.util.List;

import com.example.demo.module.entity.User;

// import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * mapper接口
 */
public interface UndoDao {
    public User query(@Param("id") Integer id);
    public Boolean addUser(List<User> user);
    public void removeUsr(@Param("id") Integer id);
}