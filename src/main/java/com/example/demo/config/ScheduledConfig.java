package com.example.demo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

// @Configuration
public class ScheduledConfig {
  @Value("${activemq.queueName}")
  private String queueName;

  @Value("${activemq.topicName}")
  private String topicName;

  @Value("${activemq.user}")
  private String usrName;

  @Value("${activemq.password}")
  private  String password;

  @Value("${activemq.broker-url}")
  private  String brokerUrl;

  @Bean
  public ActiveMQQueue queue() {
      return new ActiveMQQueue(queueName);
  }
  @Bean
  public ActiveMQConnectionFactory connectionFactory() {
      return new ActiveMQConnectionFactory(usrName, password, brokerUrl);
  }

  @Bean
  public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory connectionFactory){
      DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
      bean.setConnectionFactory(connectionFactory);
      return bean;
  }
  
  @Bean
  public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory connectionFactory){
      DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
      //设置为发布订阅方式, 默认情况下使用的生产消费者方式
      // bean.setPubSubDomain(true);
      bean.setConnectionFactory(connectionFactory);
      return bean;
  }
}