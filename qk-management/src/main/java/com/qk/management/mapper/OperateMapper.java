package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.entity.OperateLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: RightSquare
 * @Date: 2025/10/17 20:05
 * @Description:
 */
@Mapper
public interface OperateMapper extends BaseMapper<OperateLog> {
}
