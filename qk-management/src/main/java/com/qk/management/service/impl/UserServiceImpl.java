package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.common.enums.ParamEnum;
import com.qk.common.exception.CommonException;
import com.qk.dto.user.UserDTO;
import com.qk.entity.User;
import com.qk.entity.domain.user.UserDO;
import com.qk.management.mapper.UserMapper;
import com.qk.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/7 20:41
 * @Description: Impl class
 */
@Service
public class UserServiceImpl implements UserService {
    private static final String DEFAULT_PASSWORD_SUFIX = "123";
    @Autowired
    private UserMapper userMapper;

    @Override
    public void delete(List<Integer> ids) {
        // Parameter Checking null
        boolean isNotEmpty = CollectionUtil.isNotEmpty(ids);
        if (!isNotEmpty){
            CommonException.throwCommonException(ParamEnum.PARAM_ERROR);
        }
        userMapper.delete(ids);
    }

    @Override
    public List<UserDO> selectByRole(String roleLabel) {
        userMapper.selectByRole(roleLabel);
        return userMapper.selectByRole(roleLabel);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public void update(User user) {
        // Parameter Checking null
        boolean hasNull = BeanUtil.hasNullField(user, "id","password","deptId", "roleId", "image", "remark","createTime","updateTime");
        if (hasNull) {
            CommonException.throwCommonException(ParamEnum.PARAM_ERROR);
        }
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void addUser(User user) {
        boolean hasNull = BeanUtil.hasNullField(user, "id","password","deptId", "roleId", "image", "remark","createTime","updateTime");
        if (hasNull) {
            CommonException.throwCommonException(ParamEnum.PARAM_ERROR);
        }
        // merge digest password

        String rawPassword = new StringBuilder(user.getUsername()).append(DEFAULT_PASSWORD_SUFIX).toString();
        String encodedPassword = DigestUtils.md5DigestAsHex(rawPassword.getBytes());
        user.setPassword(encodedPassword);
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
