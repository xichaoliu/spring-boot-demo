package com.example.demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UndoLogAspect {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  @Pointcut("@annotation(com.example.demo.annotation.Login)")
  public void logPointCut() {}

  @Before("logPointCut()")
  public void before() {
    logger.info("请求进入");
  }
}