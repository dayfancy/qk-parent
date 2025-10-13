package com.qk.management.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.qk.entity.Course;
import com.qk.management.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class CourseServiceImplTests {
    @Autowired
    private CourseService courseService;

    @Test
    void testPage(){
        IPage<Course> page = new PageDTO<>(2,2);
        IPage<Course> result = courseService.page(page);
        // 获取总记录数
        long total = result.getTotal();
        System.out.println(total);
        long pages = result.getPages();
        System.out.println(pages);
        List<Course> records = result.getRecords();
        for (Course record : records) {
            System.out.println(record);
        }
    }






    @Test
    void testDeleteById() {
        courseService.deleteById(18);
    }
    @Test
    void testUpdate() {
        courseService.update(Course.builder().id(18).name("AIsssss").description("无").updateTime(LocalDateTime.now())
                .createTime(LocalDateTime.now())
                .price(299).
                subject(1).
                target(2).
                build());
    }

    @Test
    void testSave(){
        courseService.save(Course.builder().name("AI").description("无").updateTime(LocalDateTime.now())
                        .createTime(LocalDateTime.now())
                        .price(299).
                        subject(1).
                        target(2).
                build());
    }

    @Test
    void testSelectBySubject() {
        courseService.selectBySubject(1).forEach(System.out::println);
    }
    @Test
    void testSelectAll() {
        courseService.selectAll().forEach(System.out::println);
    }

}