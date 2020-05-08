package com.example.demo;

import com.example.demo.config.TaskThreadPoolConfig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableScheduling
@EnableJms
@EnableAsync
@EnableConfigurationProperties({TaskThreadPoolConfig.class} ) // 开启配置属性支持
@MapperScan("com.example.demo.module.dao")
public class SringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SringDemoApplication.class, args);
	}

}
