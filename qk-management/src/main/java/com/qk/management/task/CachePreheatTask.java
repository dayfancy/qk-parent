package com.qk.management.task;

import com.qk.management.service.ReportOveviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: RightSquare
 * @Date: 2025/10/19 18:08
 * @Description: 缓存预热任务类
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class CachePreheatTask implements CommandLineRunner {
    @Autowired
    private ReportOveviewService reportOveviewService;
    @Override
    public void run(String... args) throws Exception {
        reportOveviewService.reportOverview();
        log.info("------------------缓存预热完成---------------------");
    }
}
