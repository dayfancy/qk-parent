package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.entity.Dept;

import java.util.List;

public interface DeptService {


    List<Dept> selectAll();

    void deleteById(Integer id);

    void update(Dept dept);

    Dept getById(Integer id);

    PageResult<Dept> page(String name, Integer status, Integer page, Integer pageSize);

    void addDept(Dept dept);
}