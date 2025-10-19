package com.qk.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.entity.Customer;
import com.qk.management.mapper.CustomerMapper;
import com.qk.management.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: RightSquare
 * @Date: 2025/10/18 21:17
 * @Description:
 */
@Slf4j
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
}
