package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Dept;
import com.qk.management.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;


    @GetMapping
    public Result page(@RequestParam(name = "name", required = false) String name,
                       @RequestParam(name = "status", required = false) Integer status,
                       @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageResult<Dept> pageResult = deptService.page(name, status, page, pageSize);
        return Result.success(pageResult);
    }


    @PostMapping
    public Result addDept(@RequestBody Dept dept) {
        deptService.addDept(dept);
        return Result.success();
    }

}