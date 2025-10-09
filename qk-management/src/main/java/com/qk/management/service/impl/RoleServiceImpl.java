package com.qk.management.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.entity.Role;
import com.qk.management.mapper.RoleMapper;
import com.qk.management.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/9 10:30
 * @Description: Role Impl class
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public PageResult<Role> page(String name, String label, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
       List<Role> list = roleMapper.selectByPage(name, label);
       Page<Role> pageList = (Page<Role>) list;
       return PageResult.<Role>builder()
               .total(pageList.getTotal())
               .rows(pageList.getResult())
               .build();
    }
}
