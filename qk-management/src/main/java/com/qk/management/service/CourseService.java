package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.entity.Course;

public interface CourseService {
    Course selectById(Integer id);

    void deleteById(Integer id);

    void add(Course course);

    PageResult<Course> selectByPage(String name, Integer subject, Integer target, Integer page, Integer pageSize);
}
