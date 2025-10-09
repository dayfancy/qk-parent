package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.entity.Role;

public interface RoleService {
    PageResult<Role> page(String name, String label, Integer page, Integer pageSize);
}
