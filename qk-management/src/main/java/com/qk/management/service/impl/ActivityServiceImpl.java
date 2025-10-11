package com.qk.management.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.common.enums.ParamEnum;
import com.qk.common.exception.CommonException;
import com.qk.dto.activity.ActivityDTO;
import com.qk.entity.Activity;
import com.qk.management.mapper.ActivityMapper;
import com.qk.management.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public void deleteById(Integer id) {
        activityMapper.deleteById(id);
    }

    @Override
    public void add(Activity activity) {
        //1.Param Checking
        boolean hasNull = BeanUtil.hasNullField(activity, "id","discount", "voucher","createTime", "updateTime");
        if (hasNull) {
            CommonException.throwCommonException(ParamEnum.PARAM_ERROR);
        }
        activity.setUpdateTime(LocalDateTime.now());
        activity.setCreateTime(LocalDateTime.now());
        activityMapper.insert(activity);
    }

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
