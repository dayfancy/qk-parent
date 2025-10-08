package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.user.UserDTO;
import com.qk.entity.domain.user.UserDO;
import com.qk.management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public Result list(UserDTO dto){
       PageResult<UserDO> pageResult = userService.selectByPage(dto);
        return Result.success(pageResult);
    }
}
