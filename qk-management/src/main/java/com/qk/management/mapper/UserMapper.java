package com.qk.management.mapper;

import com.qk.dto.user.UserDTO;
import com.qk.entity.domain.user.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/7 20:41
 * @Description: Dao
 */
@Mapper
public interface UserMapper {

    List<UserDO> selectByPage(UserDTO dto);
}
