package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.dto.user.UserDTO;
import com.qk.entity.User;
import com.qk.entity.domain.user.UserDO;

public interface UserService {

    void addUser(User user);

    PageResult<UserDO> selectByPage(UserDTO dto);
}
