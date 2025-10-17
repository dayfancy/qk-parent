package com.qk.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.entity.OperateLog;
import com.qk.management.mapper.OperateMapper;
import com.qk.management.service.OperateService;
import org.springframework.stereotype.Service;

/**
 * @Author: RightSquare
 * @Date: 2025/10/17 20:03
 * @Description:
 */
@Service
public class OperateServiceImpl extends ServiceImpl<OperateMapper, OperateLog> implements OperateService {
}
