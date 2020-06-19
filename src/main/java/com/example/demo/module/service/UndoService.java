package com.example.demo.module.service;

import java.util.List;

import com.example.demo.module.entity.User;

public interface UndoService {
  User query(Integer id);
  public Boolean addUser(List<User> user);
  public void removeUsr(Integer id);
}