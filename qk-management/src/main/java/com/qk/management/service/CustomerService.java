package com.qk.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.dto.customer.CustomerAddDTO;
import com.qk.dto.customer.CustomerListDTO;
import com.qk.entity.Customer;
import com.qk.vo.customer.CustomerListVO;

public interface CustomerService extends IService<Customer> {

    Customer selectCustomerById(Integer id);

    void addCustomer(CustomerAddDTO dto);

    PageResult<CustomerListVO> selectListByPage(CustomerListDTO dto);
}
