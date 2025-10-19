package com.qk.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.dto.operatelog.OperateLogSelectDTO;
import com.qk.entity.OperateLog;
import com.qk.vo.operatelog.OperateLogSelectVO;

public interface OperateService extends IService<OperateLog> {

    PageResult<OperateLogSelectVO> selectLogByPage(OperateLogSelectDTO dto);
}
