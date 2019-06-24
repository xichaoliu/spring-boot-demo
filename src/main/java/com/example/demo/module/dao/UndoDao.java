package com.example.demo.module.dao;

import com.example.demo.module.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Repository
public interface UndoDao {
    public User query(Integer id);
}