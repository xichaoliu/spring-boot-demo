package com.example.demo.module.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.module.models.User;
import com.example.demo.util.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/sys")
public class LoginController {
private final Logger logger = LoggerFactory.getLogger(this.getClass());
  @PostMapping("login")
  public Object login(@RequestBody User user) {
    JwtUtil jwt = new JwtUtil();
    Map<String, Object> map = new HashMap<>();
    logger.info("密码："+user.getPassword());
    String psw = user.getPassword();
    if (!psw.equals("123") ) {
      map.put("code", 500);
      map.put("msg","密码错误");
    } else {
      String token = jwt.generateToken(user);
      map.put("code", 0);
      map.put("msg","登录成功");
      map.put("token",token);
    }
    return map;
  }
}