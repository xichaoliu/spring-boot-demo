package com.example.demo.module.controller;

import com.example.demo.annotation.UndoLog;
import com.example.demo.module.entity.User;
import com.example.demo.module.service.UndoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/module")
public class UndoController {
  @Autowired
  private UndoService undoService;
  @UndoLog
  @GetMapping("queryUsr")
  public User undo(@RequestParam("id") Integer id) {
    return undoService.query(id);
  }
  @PostMapping("addUsr")
  public Object addUsr(@RequestBody User usr){
      undoService.addUser(usr);
      return "成功";
  }
}