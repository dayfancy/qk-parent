package com.qk.management.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.dto.user.UserDTO;
import com.qk.entity.domain.user.UserDO;
import com.qk.management.mapper.UserMapper;
import com.qk.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/7 20:41
 * @Description: Impl class
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult<UserDO> selectByPage(UserDTO dto) {
        PageHelper.startPage(dto.getPage(),dto.getPageSize());
        List<UserDO> list = userMapper.selectByPage(dto);
        Page<UserDO> page = (Page<UserDO>) list;
        return PageResult.<UserDO>builder()
                .total(page.getTotal())
                .rows(page.getResult())
                .build();
    }
}
