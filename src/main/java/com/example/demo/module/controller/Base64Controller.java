package com.example.demo.module.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.annotation.Login;
import com.example.demo.annotation.UndoLog;
import com.example.demo.util.ConvertUrlToBase64;
import com.example.demo.util.JwtUtil;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@RestController
@RequestMapping(value="/util")
public class Base64Controller {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  // @UndoLog
  // // @ResponseBody
  // @PostMapping("base")
  // public void handleUrl(HttpServletRequest request, HttpServletResponse respons,@RequestBody Map<String,String> param) throws Exception {
  //       ConvertUrlToBase64 ctb = new ConvertUrlToBase64();
  //       // JSONObject jso = new JSONObject();
  //       String url = param.get("url");
  //       logger.info("url:" + url);
  //       ctb.downloadFile(url);    
  // }
  @Login
  @PostMapping("base")
  public Object handleUrl(@RequestBody Map<String,String> param) {
    // JwtUtil jwt = new JwtUtil();
    return param;
  }
}