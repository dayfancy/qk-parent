package com.qk.management.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.entity.Course;
import com.qk.management.mapper.CourseMapper;
import com.qk.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/9 14:23
 * @Description: Course Service Impl
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public PageResult<Course> selectByPage(String name, Integer subject, Integer target, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Course> courses = courseMapper.selectByPage(name, subject, target);
        Page<Course> pageList = (Page<Course>) courses;
        return PageResult.<Course>builder()
                .total(pageList.getTotal())
                .rows(pageList.getResult())
                .build();
    }
}
