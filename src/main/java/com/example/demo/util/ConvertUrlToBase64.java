package com.example.demo.util;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Decoder.BASE64Encoder;
public class ConvertUrlToBase64 {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  HttpServletRequest request;

    HttpServletResponse response;
  
  public void downloadFile(String imgURL) throws Exception {

            InputStream inStream;
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
                readInputStream(inStream);
            } catch (IOException e) {
                e.printStackTrace();  
            }           
    }

    public void readInputStream(InputStream instream) throws Exception {
        byte[] data = new byte[1024];   
        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        int len = 0;

        while ((len=instream.read(data))!= -1) {
            outPut.write(data, 0,len);
        }
        instream.close();

        responseFile(request, response, outPut.toByteArray());
       
    }
    public void responseFile(HttpServletRequest request, HttpServletResponse response, byte[] data) throws Exception {
        HttpStatus statusCode = HttpStatus.OK;
        HttpHeaders headers = new HttpHeaders();
        String filename = "测试图片.jpg";
   
        	// response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename,"UTF-8"));


        // 将字节流写入Response
        OutputStream toClient = response.getOutputStream();
        toClient.write(data);
        toClient.flush();
    }
 }