package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.dto.user.UserDTO;
import com.qk.entity.User;
import com.qk.entity.domain.user.UserDO;

import java.util.List;

public interface UserService {

    void delete(List<Integer> ids);

    List<UserDO> selectByRole(String roleLabel);

    User selectById(Integer id);

    void update(User user);

    void addUser(User user);

    PageResult<UserDO> selectByPage(UserDTO dto);
}
