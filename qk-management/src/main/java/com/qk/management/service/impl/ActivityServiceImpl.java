package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.dto.activity.ActivityDTO;
import com.qk.entity.Activity;
import com.qk.management.mapper.ActivityMapper;
import com.qk.management.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/11 19:30
 * @Description: implementation
 */
@Service
@SuppressWarnings("all")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Override
    public PageResult<Activity> listByPage(ActivityDTO dto) {
        //1.Param Checking not
        //2.page
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        //3.query -> mapper
        List<Activity> list = activityMapper.listByPage(dto);
        //4.Encapsulation
        Page<Activity> pageList = (Page<Activity>) list;
        //5.Return
       return  PageResult.<Activity>builder()
                .total(pageList.getTotal())
                .rows(pageList.getResult())
                .build();
    }
}
