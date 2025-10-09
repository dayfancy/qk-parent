package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.user.UserDTO;
import com.qk.entity.User;
import com.qk.entity.domain.user.UserDO;
import com.qk.management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: RightSquare
 * @Date: 2025/10/7 20:39
 * @Description: UserController
 */
@Slf4j
@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        User user = userService.selectById(id);
        return Result.success(user);
    }
    @PostMapping
    public Result add(@RequestBody User user){
        userService.addUser(user);
        return Result.success();
    }

    @GetMapping
    public Result list(UserDTO dto){
       PageResult<UserDO> pageResult = userService.selectByPage(dto);
        return Result.success(pageResult);
    }
}
