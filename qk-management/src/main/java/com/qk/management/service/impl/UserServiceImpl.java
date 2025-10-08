package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.dto.user.UserDTO;
import com.qk.entity.User;
import com.qk.entity.domain.user.UserDO;
import com.qk.management.mapper.UserMapper;
import com.qk.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public void addUser(User user) {
        boolean hasNull = BeanUtil.hasNullField(user, "id","password","deptId", "roleId", "image", "remark","createTime","updateTime");
        if (hasNull) {
            throw new RuntimeException("请填写完整信息");
        }
        user.setPassword(user.getUsername()+"123");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

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
