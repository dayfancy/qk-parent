package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> selectAll();

    void update(Role role);

    Role selectById(Integer id);

    void deleteById(Integer id);

    void add(Role role);

    PageResult<Role> page(String name, String label, Integer page, Integer pageSize);
}
