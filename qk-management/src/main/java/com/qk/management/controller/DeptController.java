package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.entity.Dept;
import com.qk.management.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @PostMapping
    public Result addDept(@RequestBody Dept dept) {
        deptService.addDept(dept);
        return Result.success();
    }

}