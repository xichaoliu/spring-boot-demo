package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.interfaces.Claim;
import com.example.demo.annotation.Login;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.*;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.server.ServletServerHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.stereotype.Service;

@Component
@Service("authorizationInterceptor")
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
   @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        JwtUtil jwt = new JwtUtil();
        Login annotation;
        if (handler instanceof HandlerMethod) {
          annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        } else {
          return true;
        }
        if (annotation == null) {
          return true;
        }
        String token = request.getHeader("token");
        logger.info("token"+token);
        if (token.isEmpty()) {
          return false;
          // throw new Exception("token不能为空");
        }
        String usname = jwt.parseToken(token);
        if (!usname.equals("admin")) {
          response.reset();
          response.setHeader("Access-Control-Allow-Credentials", "true");
          response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, token");
          response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
          response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
          response.setHeader("Access-Control-Max-Age", "3600");
        HttpOutputMessage httpOutputMessage = new ServletServerHttpResponse(response);
        ResponseResultForm responseResult = new ResponseResultForm(401,false,"token过期,请重新登陆",null);
        mappingJackson2HttpMessageConverter.write(responseResult, MediaType.APPLICATION_JSON_UTF8, httpOutputMessage);
        return false;
        }
        return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
  }
}