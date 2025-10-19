package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.dto.activity.ActivityDTO;
import com.qk.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {


    List<Activity> listByPage(ActivityDTO dto);


}
