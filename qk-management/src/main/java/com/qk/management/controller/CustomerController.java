package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.customer.CustomerAddDTO;
import com.qk.dto.customer.CustomerListDTO;
import com.qk.entity.Customer;
import com.qk.management.service.CustomerService;
import com.qk.vo.customer.CustomerListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public Result updateCustomer(@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
        return Result.success();
    }


    @GetMapping("/{id}")
    public Result selectCustomerById(@PathVariable Integer id) {
       Customer customer = customerService.selectCustomerById(id);
        return Result.success(customer);
    }

    @PostMapping
    public Result addCustomer(@RequestBody CustomerAddDTO dto) {
        customerService.addCustomer(dto);
        return Result.success();
    }


    @GetMapping
    public Result selectListByPage(CustomerListDTO dto){
       PageResult<CustomerListVO> pageResult = customerService.selectListByPage(dto);
        return Result.success(pageResult);
    }


}
