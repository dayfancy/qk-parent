package com.qk.management.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.qk.entity.Clue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ClueServiceTests {
    @Autowired
    private ClueService clueService;

    @Test
    public void contextLoads() {
        PageDTO<Clue> result = clueService.page(new PageDTO<>(1, 10));
        List<Clue> records = result.getRecords();
        for (Clue record : records) {
            System.out.println(record);
        }
    }

}