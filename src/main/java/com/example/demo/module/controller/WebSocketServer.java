package com.example.demo.module.controller;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.jms.Message;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
@ServerEndpoint(value="/webSocket/{sId}")
public class WebSocketServer {

    @PostConstruct
    public void init() {
        logger.info("---webSocket 加载---");
    }
    // private final Logger logger = LoggerFactory.getLogger(this.getClass()); // error: this cannot be used in static context
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    // 静态变量，用来纪录当前在线连接数
    private static final AtomicInteger OnlineCount = new AtomicInteger(0);
    // concurrent包的线程安全set，用来存放每个客户端对应的Session对象
    private static CopyOnWriteArraySet<Session> SessionSet = new CopyOnWriteArraySet<Session>();
    /**
     * activemq 消费者
     */
    @JmsListener(destination="publish.queue", containerFactory="jmsListenerContainerQueue")
    public static void receiveAndForwardMessage(String message) {
        BroadCastMessage(message);
    }

    /**
     * 群发消息
     * @param message
     */
    public static void BroadCastMessage(String message) {
        for(Session session : SessionSet) {
           if(session.isOpen()) {
                SendMessage(session, message);
           }
        }
    }
    /**
     * 发送消息
     * @param session
     * @param message
     */
    public static void SendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)",message,session.getId()));
        } catch (IOException e) {
            logger.error("发送消息出错：{}", e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * 建立连接
     * @param session
     * @param sId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value="sId") String sId) {
        SessionSet.add(session);
        int cnt = OnlineCount.incrementAndGet(); // 在线数加1;
        logger.info("有链接加入，当前连接数为：{}", cnt);
        SendMessage(session, "连接成功");
    }
    /**
     * 关闭连接
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        SessionSet.remove(session);
        int cnt = OnlineCount.decrementAndGet();
        logger.info("有连接关闭，当前连接数为：{}", cnt);
    }
    /**
     * 接受消息
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("来自客户端的消息：{}", message);
        SendMessage(session, "收到消息，消息内容：" + message);
    }
    /**
     * 发生错误
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误, {}, Session ID: {}", error.getMessage(), session.getId());
        error.printStackTrace();
    }
}
