package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.dto.customer.CustomerListDTO;
import com.qk.entity.Customer;
import com.qk.vo.customer.CustomerListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
    List<CustomerListVO> selectListByPage(CustomerListDTO dto);
}
