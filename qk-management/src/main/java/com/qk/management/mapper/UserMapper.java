package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.dto.user.UserDTO;
import com.qk.entity.Role;
import com.qk.entity.User;
import com.qk.entity.domain.user.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/7 20:41
 * @Description: Dao
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    @Select("select * from role where id = #{roleId}")
    Role getRoleLabelById(Integer roleId);
    @Select("select * from user where username = #{username}")
    User selectByUserName(String username);

    void delete(List<Integer> ids);

    List<UserDO> selectByRole(String roleLabel);

    @Select("select * from user where id = #{id}")
    User selectById(Integer id);

    void update(User user);

    void insertById(User user);

    List<UserDO> selectByPage(UserDTO dto);
}
