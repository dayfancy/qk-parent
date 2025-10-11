package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.dto.activity.ActivityDTO;
import com.qk.entity.Activity;

import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/11 19:28
 * @Description: ActivityService
 */
public interface ActivityService {
    List<Activity> getByType(Integer type);

    void update(Activity activity);

    Activity getById(Integer id);

    void deleteById(Integer id);

    void add(Activity activity);

    PageResult<Activity> listByPage(ActivityDTO dto);
}
