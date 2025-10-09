package com.qk.management.mapper;

import com.qk.entity.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select * from dept")
    List<Dept> selectAll();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    void update(Dept dept);

    @Select("select * from dept where id = #{id}")
    Dept selectById(Integer id);

    Integer count(String name, Integer status);

    List<Dept> selectByPage(String name, Integer status, Integer offset, Integer pageSize);

    @Insert("insert into dept(name,status,create_time,update_time) values(#{name},#{status},#{createTime},#{updateTime})")
    void insert(Dept dept);
}