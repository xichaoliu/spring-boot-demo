package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
/**
 * 配置cors跨域
 * 
 * 返回新的CorsFilter
 */
// @Configuration
public class CorsConfig {
  private CorsConfiguration buildConfig() {
      //1.添加CORS配置信息
      CorsConfiguration corsConfiguration = new CorsConfiguration();
      corsConfiguration.addAllowedOrigin("*"); // 1
      corsConfiguration.addAllowedHeader("*"); // 2
      corsConfiguration.addAllowedMethod("*"); // 3
      return corsConfiguration;
  }

  @Bean
  public CorsFilter corsFilter() {
       //2.添加映射路径
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", buildConfig()); // 4
      return new CorsFilter(source);
  }
}