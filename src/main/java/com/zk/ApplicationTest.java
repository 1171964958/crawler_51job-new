package com.zk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling//开启定时任务
@MapperScan(basePackages="com.zk.dao")
@Configuration
public class ApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class,args);
    }
}
