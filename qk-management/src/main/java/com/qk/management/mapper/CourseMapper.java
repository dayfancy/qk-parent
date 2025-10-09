package com.qk.management.mapper;

import com.qk.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/9 14:25
 * @Description: CourseMapper to DateBase
 */
@Mapper
public interface CourseMapper {

    void add(Course course);

    List<Course> selectByPage(String name, Integer subject, Integer target);
}
