package com.example.demo.module.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.example.demo.annotation.UndoLog;
import com.example.demo.common.util.R;
import com.example.demo.module.entity.User;
import com.example.demo.module.service.UndoService;
import com.example.demo.util.UserDataListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import Decoder.BASE64Encoder;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
@Controller
public class FileUploadController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private UndoService undoService;
  @UndoLog
  // @ResponseBody
  // @CrossOrigin
  @RequestMapping(value="/upload", method = RequestMethod.POST)
  public R handleUpload( @RequestParam("file") MultipartFile file) {
    return saveFileOnLocal(file);
  }
  @UndoLog
  @ResponseBody
  @RequestMapping(value="/excelUpload", method = RequestMethod.POST)
  public R handleExcelUpload( @RequestParam("file") MultipartFile file) {
    R r = new R();
    try {
      EasyExcel.read(file.getInputStream(), User.class,  new UserDataListener(undoService)).sheet().doRead();
      r.setCode(200);
      r.setMsg("上传成功");
    } catch (Exception e) {
      e.printStackTrace();
      //TODO: handle exception
      r.setCode(500);
      r.setMsg("上传错误");
    }
    return r;
  }
  /**
   * 
   * @param file 文件字节流
   * @return
   */
  public R saveFileOnLocal(MultipartFile file) {
      R r = new R();
      if (!file.isEmpty()) {
        logger.info("成功上传文件");

        //上传目录地址
      // String uploadDir = "F:/2-TestDemo/eclipse-workspace/uploadTest/";
      String uploadDir = System.getProperty("user.dir")+'/'; // 文件目录
      logger.info("保存路径:  "+uploadDir);
      File dir = new File(uploadDir);
      if (!dir.exists() && dir.isDirectory()) {
        dir.mkdir();
      }
      // 上传文件名
      String filename = file.getOriginalFilename();
      logger.info("上传文件名:  "+filename);
      // 服务器保存的文件对象
      File serverFile = new File(uploadDir+filename);
      BufferedOutputStream bos = null;
      try {
        OutputStream os = new FileOutputStream(serverFile);
        bos = new BufferedOutputStream(os);
        byte[] bytes = commpressPicCycle(file, 1, 0.8);
        bos.write(bytes);
        r.setCode(0);
        r.setMsg(uploadDir+filename);
      } catch (Exception e) {
        // 异常处理
        r.setCode(1);
        r.setMsg("上传失败");
      }  finally {
        if (bos != null) {
          try {
              bos.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
        }
      }

        // return "成功上传文件";
      } else {
        r.setCode(1);
        r.setMsg("上传文件为空");
      }
      return r;
  }
  /**
     * 根据指定大小压缩图片
     *
     * @param multipartFile  源图片
     * @param desFileSize 指定图片大小，单位kb
     * @param accuracy     图片压缩质量比 0 - 1
     * @return 压缩质量后的图片字节数组
     */
  public byte[] commpressPicCycle(MultipartFile multipartFile, long desFileSize,double accuracy){
    try(ByteArrayOutputStream out = new ByteArrayOutputStream()){
        long fileSize = multipartFile.getSize();
        //判断大小,如果小于200k,不压缩,如果大于等于200k,压缩
        if(fileSize <= desFileSize * 200){
            return multipartFile.getBytes();
        }
        //计算宽高
        BufferedImage output = ImageIO.read(multipartFile.getInputStream());
        int imgWidth = output.getWidth();
        int imgHeight = output.getHeight();
        int desWidth = new BigDecimal(imgWidth).multiply(                         
                new BigDecimal(accuracy)).intValue();
        int desHeight = new BigDecimal(imgHeight).multiply(
                new BigDecimal(accuracy)).intValue();
        //循环压缩到200kb以下
        output = Thumbnails.of(output).size(desWidth, desHeight).asBufferedImage();
        String  base64 = imageToBase64(output);
        while (base64.length() - base64.length() / 8 * 2 > 204800) {
            output = Thumbnails.of(output).scale(0.9f).asBufferedImage();
            base64 = imageToBase64(output);
        }

        ImageIO.write(output, "jpg", out);
        return out.toByteArray();
    }catch (IOException e){
        e.printStackTrace();
    }
        return null;
  }

  public static String imageToBase64(BufferedImage bufferedImage) {
    BASE64Encoder encoder = new BASE64Encoder();
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
        ImageIO.write(bufferedImage, "jpg", baos);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return encoder.encode((baos.toByteArray()));
  }
}