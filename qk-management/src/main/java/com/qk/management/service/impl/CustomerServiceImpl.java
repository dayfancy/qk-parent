package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.dto.customer.CustomerAddDTO;
import com.qk.dto.customer.CustomerListDTO;
import com.qk.entity.Customer;
import com.qk.management.mapper.CourseMapper;
import com.qk.management.mapper.CustomerMapper;
import com.qk.management.service.CustomerService;
import com.qk.vo.customer.CustomerListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/18 21:17
 * @Description:
 */
@Slf4j
@Service
@SuppressWarnings("all")
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public void updateCustomer(Customer customer) {
        customer.setUpdateTime(LocalDateTime.now());
        this.baseMapper.updateById(customer);
    }

    @Override
    public Customer selectCustomerById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public void addCustomer(CustomerAddDTO dto) {
        Customer customer = BeanUtil.copyProperties(dto, Customer.class);
        customer.setUpdateTime(LocalDateTime.now());
        customer.setCreateTime(LocalDateTime.now());
        this.baseMapper.insert(customer);
    }

    @Override
    public PageResult<CustomerListVO> selectListByPage(CustomerListDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
       List<CustomerListVO> list = this.baseMapper.selectListByPage(dto);
       Page<CustomerListVO> page = (Page<CustomerListVO>)list;
       return PageResult.<CustomerListVO>builder()
               .total(page.getTotal())
               .rows(page.getResult())
               .build();
    }
}
