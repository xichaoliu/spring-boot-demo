package com.example.demo.job;

import com.example.demo.module.service.UndoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

// @Component
public class UndoTask {
  @Autowired
  private UndoService undoService;
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  @Scheduled(cron="*/5 * * * * ?")
  protected void doTask() {
    logger.info("---启动定时任务，查询数据---");
    // undoService.query();
    logger.info("--查询结束--");
  }
}