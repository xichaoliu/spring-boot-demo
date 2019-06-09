package com.example.demo.module.controller;
import java.io.File;

import com.example.demo.annotation.UndoLog;
import com.example.demo.common.util.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @UndoLog
  @ResponseBody
  // @CrossOrigin
  @RequestMapping(value="/upload", method = RequestMethod.POST)
  public R handleUpload( @RequestParam("file") MultipartFile file) {
      R r = new R();
    if (!file.isEmpty()) {
      logger.info("成功上传文件");

      //上传目录地址
    String uploadDir = "F:/2-TestDemo/eclipse-workspace/uploadTest/";
    logger.info("保存路径:  "+uploadDir);
    File dir = new File(uploadDir);
    if (!dir.exists()) {
      dir.mkdir();
    }
    // 上传文件名
    String filename = file.getOriginalFilename();
    logger.info("上传文件名:  "+filename);
    // 服务器保存的文件对象
    File serverFile = new File(uploadDir+filename);
    try {
      file.transferTo(serverFile);    
      r.setCode(0);
      r.setMsg(uploadDir+filename);
    } catch (Exception e) {
      // 异常处理
      r.setCode(1);
      r.setMsg("上传失败");
    }
      // return "成功上传文件";
    } else {
      r.setCode(1);
      r.setMsg("上传文件为空");
    }
    return r;
  }
}