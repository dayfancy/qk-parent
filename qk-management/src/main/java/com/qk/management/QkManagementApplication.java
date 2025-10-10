package com.qk.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.qk")
public class QkManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(QkManagementApplication.class, args);
    }
}
