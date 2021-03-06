package com.example.demo.module.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.example.demo.module.dao.UndoDao;
import com.example.demo.module.entity.User;
import com.example.demo.module.service.UndoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("undoService")
public class UndoServiceImpl implements UndoService {
  @Autowired
  private UndoDao undoDao;
  @Override
  public User query(Integer id) {
    return  undoDao.query(id);
  }

  @Transactional
  public Boolean addUser(List<User> user) {
    return undoDao.addUser(user);
  }
  public void removeUsr(Integer id) {
    undoDao.removeUsr(id);
  }
}