package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.entity.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/9 14:25
 * @Description: CourseMapper to DateBase
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    @Select("select * from course where subject = #{subject}")
    List<Course> selectBySubject(Integer subjcet);

    @Select("select * from course")
    List<Course> selectAll();

    void update(Course course);

    @Select("select * from course where id = #{id}")
    Course selectById(Integer id);

    @Delete("delete from course where id = #{id}")
    void deleteById(Integer id);

    void add(Course course);

    List<Course> selectByPage(String name, Integer subject, Integer target);
}
