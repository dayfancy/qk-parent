package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.entity.Role;
import com.qk.management.mapper.RoleMapper;
import com.qk.management.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public void deleteById(Integer id) {
        roleMapper.deleteById(id);
    }

    @Override
    public void add(Role role) {
        boolean hasNull = BeanUtil.hasNullField(role, "id", "remark", "createTime", "updateTime");
        if (hasNull) {
            throw new RuntimeException("请填写完整信息");
        }
        role.setUpdateTime(LocalDateTime.now());
        role.setCreateTime(LocalDateTime.now());
        roleMapper.insert(role);
    }

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
