package com.qk.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.qk")
@ServletComponentScan
@EnableScheduling
public class QkManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(QkManagementApplication.class, args);
    }
}
