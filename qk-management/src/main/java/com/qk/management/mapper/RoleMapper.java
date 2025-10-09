package com.qk.management.mapper;

import com.qk.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/9 10:31
 * @Description: Role Mapper to database
 */
@Mapper
public interface RoleMapper {

    void insert(Role role);

    List<Role> selectByPage(String name, String label);
}
