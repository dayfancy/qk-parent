package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.entity.Role;

public interface RoleService {
    void update(Role role);

    Role selectById(Integer id);

    void deleteById(Integer id);

    void add(Role role);

    PageResult<Role> page(String name, String label, Integer page, Integer pageSize);
}
