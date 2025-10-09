package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.entity.Course;
import com.qk.management.mapper.CourseMapper;
import com.qk.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public Course selectById(Integer id) {
        return courseMapper.selectById(id);
    }

    @Override
    public void deleteById(Integer id) {
        courseMapper.deleteById(id);
    }

    @Override
    public void add(Course course) {
        // Parameter Checking
        boolean hasNull = BeanUtil.hasNullField(course, "id","description", "createTime", "updateTime");
        if (hasNull) {
            throw new RuntimeException("请填写完整信息");
        }
        course.setCreateTime(LocalDateTime.now());
        course.setUpdateTime(LocalDateTime.now());
        courseMapper.add(course);
    }

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
