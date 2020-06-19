package com.example.demo.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

public class AuditLogUtil {
    final static Logger logger = LoggerFactory.getLogger(AuditLogUtil.class);

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void alterAnnotationOn(Method method, String destination) throws Exception {
        method.setAccessible(true);
        logger.info("执行的方法： {}", method.getName());
        boolean methodHasAnno = method.isAnnotationPresent(JmsListener.class);
        if (methodHasAnno) {
            JmsListener methodAnno = method.getAnnotation(JmsListener.class);
            InvocationHandler h  = Proxy.getInvocationHandler(methodAnno);

            Field hField = h.getClass().getDeclaredField("memberValues");

            hField.setAccessible(true);
            Map<String, Object> memberValues = (Map) hField.get(h);
			logger.info("修改操作事件类型由={}改为={}",methodAnno.destination(), destination);
			memberValues.put("destination", destination);
        }
    }
}