package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Dept;
import com.qk.management.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
@SuppressWarnings("all")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @GetMapping("/list")
    public Result selectAll(){
        List<Dept> res = deptService.selectAll();
        return Result.success(res);
    }
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
//        int i = 1/0;
        log.info("删除部门id:{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门:{}",dept);
         deptService.update(dept);
         return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询部门id:{}",id);
       Dept dept = deptService.getById(id);
        return Result.success(dept);
    }


    @GetMapping
    public Result page(@RequestParam(name = "name", required = false) String name,
                       @RequestParam(name = "status", required = false) Integer status,
                       @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageResult<Dept> pageResult = deptService.page(name, status, page, pageSize);
        log.info("分页查询部门,参数:name:{},status:{},page:{},pageSize:{}", name, status, page, pageSize);
        log.info("-----------------------部门列表的方法-----------------------------");
        return Result.success(pageResult);
    }


    @PostMapping
    public Result addDept(@RequestBody Dept dept) {
        log.info("添加部门:{}",dept);
        deptService.addDept(dept);
        return Result.success();
    }

}