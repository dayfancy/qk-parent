package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Course;
import com.qk.management.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/9 14:22
 * @Description: CourseController to client side
 */
@Slf4j
@RestController
@RequestMapping(path = "/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @GetMapping("/list")
    public Result selectAll(){
        List<Course> res = courseService.selectAll();
        return Result.success(res);
    }
    @PutMapping
    public Result update(@RequestBody Course course){
        courseService.update(course);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        Course course = courseService.selectById(id);
        return Result.success(course);
    }
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        courseService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Course course){
        courseService.add(course);
        return Result.success();
    }

    @GetMapping
    public Result page(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                       @RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "subject", required = false) Integer subject,
                       @RequestParam(value = "target", required = false) Integer target
    ) {
        PageResult<Course> pageResult = courseService.selectByPage(name, subject, target, page, pageSize);
        return Result.success(pageResult);
    }
}
