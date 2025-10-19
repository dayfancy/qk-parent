package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.dto.operatelog.OperateLogSelectDTO;
import com.qk.entity.OperateLog;
import com.qk.vo.operatelog.OperateLogSelectVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/17 20:05
 * @Description:
 */
@Mapper
public interface OperateMapper extends BaseMapper<OperateLog> {

    List<OperateLogSelectVO> selectLogByPage(OperateLogSelectDTO dto);
}
