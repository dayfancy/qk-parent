package com.qk.management.mapper;

import com.qk.entity.Dept;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {

    Integer count(String name, Integer status);

    List<Dept> selectByPage(String name, Integer status, Integer offset, Integer pageSize);

    @Insert("insert into dept(name,status,create_time,update_time) values(#{name},#{status},#{createTime},#{updateTime})")
    void insert(Dept dept);
}