package com.qk.management.service.impl;

import com.qk.common.PageResult;
import com.qk.entity.Dept;
import com.qk.management.mapper.DeptMapper;
import com.qk.management.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> selectAll() {
       return deptMapper.selectAll();
    }

    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.selectById(id);
    }

    @Override
    public PageResult<Dept> page(String name, Integer status, Integer page, Integer pageSize) {
        Integer total = deptMapper.count(name, status);

        Integer offset = (page - 1) * pageSize;
        List<Dept> rows = deptMapper.selectByPage(name, status, offset, pageSize);
        return PageResult.<Dept>builder()
                .total(total)
                .rows(rows)
                .build();
    }

    @Override
    public void addDept(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);

    }
}