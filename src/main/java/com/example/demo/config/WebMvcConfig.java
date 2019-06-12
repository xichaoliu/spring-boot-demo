package com.example.demo.config;

import com.example.intercepor.AuthorizationInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 配置跨域
 * 重写WebMvcConfigurer
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  @Autowired
  private  AuthorizationInterceptor authorizationInterceptor;
 
 
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**");
  }
  @Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")
    	.allowedOrigins("*")
    	.allowedHeaders("*")
        .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
        .allowCredentials(false).maxAge(3600);
    }
  // @Bean
  // public WebMvcConfigurer corsConfigurer() {
  //   return new WebMvcConfigurer() {
  //     @Override
  //     public void addCorsMappings(CorsRegistry registry) {
  //       // 添加映射路径
  //       registry.addMapping("/**")
  //       //放行哪些原始域
  //       .allowedOrigins("*")
  //       //是否发送Cookie信息
  //       .allowCredentials(true)
  //       //放行哪些原始域(请求方式)
  //       .allowedMethods("GET","POST", "PUT", "DELETE")
  //       //放行哪些原始域(头部信息)
  //       .allowedHeaders("*")
  //       //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
  //       .exposedHeaders("Header1", "Header2");
  //     }
  //   };
  // }
}