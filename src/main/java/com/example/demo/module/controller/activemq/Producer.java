package com.example.demo.module.controller.activemq;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController
public class Producer {
  @Autowired
  private JmsMessagingTemplate jmsMessagingTemplate;

  @Autowired
  private Queue queue;

  @RequestMapping("/sendMsg")
  public void send(String msg) {
    this.jmsMessagingTemplate.convertAndSend(queue, msg);
  }
}