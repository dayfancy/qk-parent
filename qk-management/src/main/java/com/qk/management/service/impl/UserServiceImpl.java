package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.common.enums.ParamEnum;
import com.qk.common.exception.CommonException;
import com.qk.common.util.JwtUtil;
import com.qk.dto.user.UserDTO;
import com.qk.dto.user.UserLoginDTO;
import com.qk.entity.Role;
import com.qk.entity.User;
import com.qk.entity.domain.user.UserDO;
import com.qk.management.mapper.UserMapper;
import com.qk.management.service.UserService;
import com.qk.vo.user.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // 登录的业务逻辑
    @Override
    public UserLoginVO login(UserLoginDTO dto) {
        //1. Parameter Checking null
        boolean hasNull = BeanUtil.hasNullField(dto);
        if (hasNull) {
            CommonException.throwCommonException(ParamEnum.PARAM_ERROR);
        }
        //2.username->select datebase
        User dbuser = userMapper.selectByUserName(dto.getUsername());
        //3.1 Data Validity Check
        if (ObjectUtil.isNull(dbuser)) {
            CommonException.throwCommonException(ParamEnum.LOGIN_USER_NOT_EXIST);
        }
        //3.2 Validation Check username and password ->digest
        String userPassword = DigestUtils.md5DigestAsHex(dto.getPassword().getBytes());
        //4.psword->digest
        if (!ObjectUtil.equal(dbuser.getPassword(), userPassword)) {
            CommonException.throwCommonException(ParamEnum.LOGIN_USER_PASSWORD_ERROR);
        }
        Role role = userMapper.getRoleLabelById(dbuser.getRoleId());
        // create claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", dbuser.getId());
        claims.put("username", dbuser.getUsername());


        //5.return
        return UserLoginVO.builder()
                .id(dbuser.getId())
                .username(dbuser.getUsername())
                .name(dbuser.getName())
                .image(dbuser.getImage())
                .roleLabel(role.getLabel())
                .token(JwtUtil.generateToken(claims))
                .build();
    }

    @Override
    public void delete(List<Integer> ids) {
        // Parameter Checking null
        boolean isNotEmpty = CollectionUtil.isNotEmpty(ids);
        if (!isNotEmpty) {
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
        boolean hasNull = BeanUtil.hasNullField(user, "id", "password", "deptId", "roleId", "image", "remark", "createTime", "updateTime");
        if (hasNull) {
            CommonException.throwCommonException(ParamEnum.PARAM_ERROR);
        }
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void addUser(User user) {
        boolean hasNull = BeanUtil.hasNullField(user, "id", "password", "deptId", "roleId", "image", "remark", "createTime", "updateTime");
        if (hasNull) {
            CommonException.throwCommonException(ParamEnum.PARAM_ERROR);
        }
        // merge digest password

        String rawPassword = new StringBuilder(user.getUsername()).append(DEFAULT_PASSWORD_SUFIX).toString();
        String encodedPassword = DigestUtils.md5DigestAsHex(rawPassword.getBytes());
        user.setPassword(encodedPassword);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insertById(user);
    }

    @Override
    public PageResult<UserDO> selectByPage(UserDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List<UserDO> list = userMapper.selectByPage(dto);
        //1.从数据库中查出来手机号
        list.forEach(item -> {
            //2.处理手机号
            //3.装进去
            item.setPhone(item.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
        });
        Page<UserDO> page = (Page<UserDO>) list;
        return PageResult.<UserDO>builder()
                .total(page.getTotal())
                .rows(page.getResult())
                .build();
    }
}
