package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.constant.ClueStatusConstants;
import com.qk.common.enums.ParamEnum;
import com.qk.common.exception.CommonException;
import com.qk.dto.clue.ClueDTO;
import com.qk.entity.Clue;
import com.qk.management.mapper.ClueMapper;
import com.qk.management.service.ClueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author: RightSquare
 * @Date: 2025/10/13 20:58
 * @Description:
 */
@Slf4j
@Service
@SuppressWarnings("all")
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue> implements ClueService {
    @Autowired
    private ClueMapper clueMapper;
    @Override
    public void add(ClueDTO dto) {
        //1.Param Checking
        if(ObjectUtil.isEmpty(dto.getPhone())||ObjectUtil.isEmpty(dto.getChannel())){
            CommonException.throwCommonException(ParamEnum.PARAM_ERROR);
        }
        //DTO to bean
        Clue clue = BeanUtil.copyProperties(dto, Clue.class);
        clue.setCreateTime(LocalDateTime.now());
        clue.setUpdateTime(LocalDateTime.now());
        clue.setStatus(ClueStatusConstants.WAIT_ALLOCATION);
        clueMapper.insert(clue);
        //this.save( clue);

    }
}
