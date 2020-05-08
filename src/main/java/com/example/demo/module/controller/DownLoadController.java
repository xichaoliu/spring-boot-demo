package com.example.demo.module.controller;

import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.annotation.Login;
import com.example.demo.annotation.UndoLog;
import com.example.demo.util.ConvertUrlToBase64;
import com.example.demo.util.JwtUtil;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@RestController
@RequestMapping(value="/sys")
public class DownLoadController {
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
  // @Login
  @GetMapping("downloadByUrl")
  public void handleUrl(HttpServletRequest request, HttpServletResponse response, @RequestParam("filename") String filename, @RequestParam("url") String url) throws Exception {
    // JwtUtil jwt = new JwtUtil();
    // 通过url获取输入流
    InputStream in = getInputStream(url);
    // 将输入流转为二进制数据
    byte[] data = readInputStream(in);
    // 输出
    responseFile(request, response, data, filename);
  }
  public InputStream getInputStream(String imgURL) throws Exception {

    InputStream inStream = null;
    try {  
        // 创建URL  
        URL url = new URL(imgURL);  
        // 创建链接  
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
        conn.setRequestMethod("GET");  
        conn.setConnectTimeout(10 * 1000);  

        if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            logger.info("---连接失败---");
        // return "fail";//连接失败/链接失效/图片不存在
        }
        // 获取图片信息
        inStream = conn.getInputStream();  
    } catch (IOException e) {
        e.printStackTrace();  
    }        
    return inStream;   
}

  public byte[] readInputStream(InputStream instream) throws Exception {
    byte[] data = new byte[1024];   
    ByteArrayOutputStream outPut = new ByteArrayOutputStream();
    int len = 0;

    while ((len=instream.read(data))!= -1) {
        outPut.write(data, 0,len);
    }
    instream.close();
    return outPut.toByteArray();

  }
  public void responseFile(HttpServletRequest request, HttpServletResponse response, byte[] data, String filename) throws Exception {
    HttpStatus statusCode = HttpStatus.OK;
    HttpHeaders headers = new HttpHeaders();
    filename =filename+".jpg";

    response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename,"UTF-8"));


    // 将字节流写入Response
    OutputStream toClient = response.getOutputStream();
    toClient.write(data);
    toClient.flush();
  }

} 