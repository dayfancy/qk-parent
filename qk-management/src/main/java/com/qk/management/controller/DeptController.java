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
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        deptService.deleteById(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
         deptService.update(dept);
         return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
       Dept dept = deptService.getById(id);
        return Result.success(dept);
    }


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