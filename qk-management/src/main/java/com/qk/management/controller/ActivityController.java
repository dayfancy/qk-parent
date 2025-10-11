package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.activity.ActivityDTO;
import com.qk.entity.Activity;
import com.qk.management.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: RightSquare
 * @Date: 2025/10/11 19:26
 * @Description: ActivityController
 */
@Slf4j
@RestController
@RequestMapping("/activities")
@SuppressWarnings("all")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
    Activity activity = activityService.getById(id);
    return Result.success(activity);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
    activityService.deleteById(id);
    return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Activity activity){
    activityService.add(activity);
    return Result.success();
    }

    @GetMapping
    public Result listByPage(ActivityDTO dto){
       PageResult<Activity> pageResult = activityService.listByPage(dto);
       return Result.success(pageResult);
    }
}
