package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Role;
import com.qk.management.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/9 10:27
 * @Description: Role Controller to client side
 */
@Slf4j
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @GetMapping("/list")
    public Result selectAll(){
        List<Role> res = roleService.selectAll();
        return Result.success(res);
    }

    @PutMapping
    public Result update(@RequestBody Role role){
        roleService.update(role);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        Role role =  roleService.selectById(id);
        return Result.success(role);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
    roleService.deleteById(id);
    return Result.success();
    }
    @PostMapping
    public Result add(@RequestBody Role role){
        roleService.add(role);
        return Result.success();
    }

    @GetMapping
    public Result page(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "label", required = false) String label,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        PageResult<Role> pageResult = roleService.page(name, label, page, pageSize);
        return Result.success(pageResult);
    }
}
