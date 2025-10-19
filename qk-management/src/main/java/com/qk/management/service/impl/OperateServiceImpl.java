package com.qk.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.dto.operatelog.OperateLogSelectDTO;
import com.qk.entity.OperateLog;
import com.qk.management.mapper.OperateMapper;
import com.qk.management.service.OperateService;
import com.qk.vo.operatelog.OperateLogSelectVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/17 20:03
 * @Description:
 */
@Slf4j
@Service
@SuppressWarnings("all")
public class OperateServiceImpl extends ServiceImpl<OperateMapper, OperateLog> implements OperateService {
    @Autowired
    private OperateMapper operateMapper;

    @Override
    public PageResult<OperateLogSelectVO> selectLogByPage(OperateLogSelectDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
       List<OperateLogSelectVO> list = operateMapper.selectLogByPage(dto);
       Page<OperateLogSelectVO> page = (Page<OperateLogSelectVO>)list;
       return PageResult.<OperateLogSelectVO>builder()
               .total(page.getTotal())
               .rows(page.getResult())
               .build();
    }
}
