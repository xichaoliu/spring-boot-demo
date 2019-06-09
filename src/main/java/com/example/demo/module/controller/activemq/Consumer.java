package com.example.demo.module.controller.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

// @Component
public class Consumer {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @JmsListener(destination="publish.queue", containerFactory="jmsListenerContainerQueue")
  public void receiveQueue(String text) {
    logger.info("--收到消息："+text+"---");
  }
}