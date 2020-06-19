package com.example.demo.module.controller.activemq;

import java.util.UUID;

import javax.jms.Queue;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {
  @Autowired
  private JmsMessagingTemplate jmsMessagingTemplate;

  @Autowired
  private Queue queue;

  @RequestMapping("/sendMsg")
  public void send(@Param(value="msg") String msg) {
    this.jmsMessagingTemplate.convertAndSend(queue, msg);
  }
  // @Scheduled(fixedDelay = 2000)
  public void produceMsgScheduled() {
    jmsMessagingTemplate.convertAndSend(queue,"***" + UUID.randomUUID());
  }
}