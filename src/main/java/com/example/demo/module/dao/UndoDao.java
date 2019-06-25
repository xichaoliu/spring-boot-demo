package com.example.demo.module.dao;

import com.example.demo.module.entity.User;

// import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * mapper接口
 */
public interface UndoDao {
    public User query(@Param("id") Integer id);
}