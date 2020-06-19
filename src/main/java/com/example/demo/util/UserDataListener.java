package com.example.demo.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.example.demo.module.entity.User;
import com.example.demo.module.service.UndoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDataListener extends AnalysisEventListener<User> {

    private static final Logger logger = LoggerFactory.getLogger(UserDataListener.class);
   /**
    * 每隔5条存储数据库，实际使用可以3000条，然后清理list，方便回收
    */
    private static final int BATCH_COUNT = 5;
    List<User> list = new ArrayList<User>();

    private UndoService undoService;

    public UserDataListener(UndoService undoService) {
        this.undoService = undoService;
    }
    /**
     * 每一条数据解析都会调用此方法
     */
    @Override
    public void invoke(User user, AnalysisContext context) {
        logger.info("解析到一条数据： {}", JSON.toJSONString(user));
        list.add(user);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }
    /**
    *所有数据解析完成后调用
    */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        logger.info("所有数据解析完成");
    }
    /**
     * 数据存储数据库
     */
    private void saveData() {
        logger.info("excel解析{}条数据，开始存储数据库", list.size());
        for (User user : list) {
            System.out.printf("INFO: usrname [%s] password [%s]", user.getUsrname(), user.getPassword());
        }
        undoService.addUser(list);
        logger.info("存储数据库成功");
    }
}