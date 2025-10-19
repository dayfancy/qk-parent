package com.qk.management.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.common.enums.ParamEnum;
import com.qk.common.exception.CommonException;
import com.qk.dto.activity.ActivityDTO;
import com.qk.entity.Activity;
import com.qk.entity.Clue;
import com.qk.management.mapper.ActivityMapper;
import com.qk.management.mapper.ClueMapper;
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
    @Autowired
    private ClueMapper clueMapper;

    @Override
    public List<Activity> getByType(Integer type) {
        return activityMapper.selectList(new UpdateWrapper<Activity>().eq("type", type));
    }

    @Override
    public void update(Activity activity) {
        //1.Param Checking
        boolean hasNull = BeanUtil.hasNullField(activity, "id", "discount", "voucher", "createTime", "updateTime");
        if (hasNull) {
            CommonException.throwCommonException(ParamEnum.PARAM_ERROR);
        }
        //2.update
        activity.setUpdateTime(LocalDateTime.now());
        UpdateWrapper<Activity> activityUpdateWrapper = new UpdateWrapper<>();
        activityUpdateWrapper.set("channel", activity.getChannel())
                .set("name", activity.getName())
                .set("start_time", activity.getStartTime())
                .set("end_time", activity.getEndTime())
                .set("description", activity.getDescription())
                .set( "type", activity.getType())
                .set( "discount", activity.getDiscount())
                .set( "voucher", activity.getVoucher())
                .set( "create_time", activity.getCreateTime())
                .set( "update_time", activity.getUpdateTime());
        UpdateWrapper<Activity> id = activityUpdateWrapper.eq("id", activity.getId());
        activityMapper.update(id);
    }

    @Override
    public Activity getById(Integer id) {
        return activityMapper.selectById(id);
    }

    @Override
    public void deleteById(Integer id) {
        List<Clue> clues = clueMapper.selectList(Wrappers.lambdaQuery(Clue.class).eq(Clue::getActivityId, id));
        if (clues.size() > 0){
            CommonException.throwCommonException(ParamEnum.DELETE_ERROR);
        }else {
            activityMapper.deleteById(id);
        }
    }

    @Override
    public void add(Activity activity) {
        //1.Param Checking
        boolean hasNull = BeanUtil.hasNullField(activity, "id", "discount", "voucher", "createTime", "updateTime");
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
        return PageResult.<Activity>builder()
                .total(pageList.getTotal())
                .rows(pageList.getResult())
                .build();
    }
}
