package com.qk.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CourseService extends IService<Course> {
    List<Course> selectBySubject(Integer subjcet);

    List<Course> selectAll();

    void update(Course course);

    Course selectById(Integer id);

    void deleteById(Integer id);

    void add(Course course);

    PageResult<Course> selectByPage(String name, Integer subject, Integer target, Integer page, Integer pageSize);
}
