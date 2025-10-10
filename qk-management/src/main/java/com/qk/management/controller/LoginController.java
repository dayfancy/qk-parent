package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.dto.user.UserLoginDTO;
import com.qk.management.service.UserService;
import com.qk.vo.user.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: RightSquare
 * @Date: 2025/10/10 16:44
 * @Description: UserLogin Controller
 */
@RestController
@SuppressWarnings("all")
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDTO dto){
       UserLoginVO userLoginVO =  userService.login(dto);
       return Result.success(userLoginVO);
    }
}
