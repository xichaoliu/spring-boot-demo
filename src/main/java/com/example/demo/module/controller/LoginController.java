package com.example.demo.module.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/sys")
public class LoginController {

  @PostMapping("login")
  public Object login() {
    Map<String, Object> map = new HashMap<>();
    map.put("code", 0);
    return map;
  }
}