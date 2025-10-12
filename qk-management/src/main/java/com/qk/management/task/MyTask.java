package com.qk.management.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: RightSquare
 * @Date: 2025/10/12 17:26
 * @Description:
 */
@SuppressWarnings("all")
@Component
public class MyTask {
//    @Scheduled(cron = "0/1 * * * * ? ")
    public void clean(){
        System.out.println("定时任务执行了..." + LocalDateTime.now());
    }
}
