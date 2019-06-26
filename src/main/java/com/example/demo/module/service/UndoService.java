package com.example.demo.module.service;

import com.example.demo.module.entity.User;

public interface UndoService {
  User query(Integer id);
  public Boolean addUser(User user);
  public void removeUsr(Integer id);
}