package com.qk.management;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.qk")
@MapperScan(basePackages = "com.qk.management.mapper")
@ServletComponentScan
public class QkManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(QkManagementApplication.class, args);
    }
}
