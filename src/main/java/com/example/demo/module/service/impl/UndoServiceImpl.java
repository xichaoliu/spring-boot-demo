package com.example.demo.module.service.impl;

import javax.annotation.Resource;

import com.example.demo.module.dao.UndoDao;
import com.example.demo.module.entity.User;
import com.example.demo.module.service.UndoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("undoService")
public class UndoServiceImpl implements UndoService {
  @Autowired
  private UndoDao undoDao;
  @Override
  public User query(Integer id) {
    return  undoDao.query(id);
  }
}