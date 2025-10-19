package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.customer.CustomerListDTO;
import com.qk.management.service.CustomerService;
import com.qk.vo.customer.CustomerListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: RightSquare
 * @Date: 2025/10/18 21:16
 * @Description:
 */
@RestController
@RequestMapping("/customers")
@SuppressWarnings("all")
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @GetMapping
    public Result selectListByPage(CustomerListDTO dto){
       PageResult<CustomerListVO> pageResult = customerService.selectListByPage(dto);
        return Result.success(pageResult);
    }


}
