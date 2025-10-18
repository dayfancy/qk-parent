package com.qk.management.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.qk.common.PageResult;
import com.qk.common.constant.BusinessStatusConstants;
import com.qk.common.enums.ParamEnum;
import com.qk.common.exception.CommonException;
import com.qk.common.util.CurrentUserContextHolders;
import com.qk.dto.business.BusinessAddDTO;
import com.qk.dto.business.BusinessFollowDTO;
import com.qk.dto.business.BusinessListDTO;
import com.qk.entity.Business;
import com.qk.entity.BusinessTrackRecord;
import com.qk.entity.Customer;
import com.qk.entity.User;
import com.qk.management.mapper.BusinessMapper;
import com.qk.management.mapper.BusinessTrackRecordMapper;
import com.qk.management.mapper.CustomerMapper;
import com.qk.management.mapper.UserMapper;
import com.qk.management.service.BusinessService;
import com.qk.vo.business.BusinessListVO;
import com.qk.vo.business.BusinessSelectByIdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/15 11:27
 * @Description:
 */
@Service
@SuppressWarnings("all")
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private BusinessTrackRecordMapper businessTrackRecordMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void back(Integer id) {
        Business business = this.baseMapper.selectById(id);
        business.setStatus(BusinessStatusConstants.RECYCLE);
        business.setUpdateTime(LocalDateTime.now());
        this.baseMapper.updateById(business);
    }

    @Override
    @Transactional
    public void toCustomer(Integer id) {
        Business business = this.baseMapper.selectById(id);
        //状态改为转客户
        business.setStatus(BusinessStatusConstants.TRANSFORM_CUSTOMER);
        //修改时间改为现在
        business.setUpdateTime(LocalDateTime.now());
        this.baseMapper.updateById(business);
        //把同名的属性拷贝到客户表
        Customer customer = BeanUtil.copyProperties(business, Customer.class);
        customer.setId(null);
        customer.setBusinessId(id);
        customer.setCreateTime(LocalDateTime.now());
        customer.setUpdateTime(LocalDateTime.now());
        customerMapper.insert(customer);
    }

    @Transactional
    @Override
    public void followBusiness(BusinessFollowDTO dto) {
        //更新两张表 要事务管理
        //创建一个商机对象 把dto 的拷贝给商机对象
        Business business = BeanUtil.copyProperties(dto, Business.class);
        //判断是不是第一次跟进 是第一次跟进的话 状态要改为待跟进
        if (business.getStatus() == BusinessStatusConstants.WAIT_FOLLOW_UP) {
            business.setStatus(BusinessStatusConstants.FOLLOW_UP);
        }
        business.setUpdateTime(LocalDateTime.now());
        this.baseMapper.updateById(business);
        //添加一条商机的跟进记录
        List<String> keyItems = dto.getKeyItems();
        String string = String.join(",", keyItems);
        BusinessTrackRecord businessTrackRecord = BeanUtil.copyProperties(dto, BusinessTrackRecord.class);
        businessTrackRecord.setTrackStatus(dto.getTrackStatus());
        businessTrackRecord.setBusinessId(business.getId());
        businessTrackRecord.setKeyItems(string);
        businessTrackRecord.setNextTime(dto.getNextTime());
        businessTrackRecord.setRecord(dto.getRecord());
        businessTrackRecord.setCreateTime(LocalDateTime.now());
        businessTrackRecord.setUserId(CurrentUserContextHolders.get());
        businessTrackRecordMapper.insert(businessTrackRecord);

    }

    @Override
    public BusinessSelectByIdVO selectBusinessById(Integer id) {
        //1.查询商机表  返回商机对象
        Business business = this.baseMapper.selectById(id);
        //2.查询商机跟进记录表 返回商机跟进记录对象
        List<BusinessTrackRecord> businessTrackRecords = businessTrackRecordMapper.selectList(Wrappers.lambdaQuery(BusinessTrackRecord.class)
                        .eq(BusinessTrackRecord::getBusinessId, id))
                .stream()
                .map(item -> {
                    User user = userMapper.selectById(item.getUserId());
                    item.setAssignName(user.getName());
                    return item;
                }).toList();
        //3.把两个对象组合成VO对象
        BusinessSelectByIdVO businessSelectByIdVO = BeanUtil.copyProperties(business, BusinessSelectByIdVO.class);
        //把集合businessTrackRecords组装到businessSelectByIdVO对象的trackRecords属性
        businessSelectByIdVO.setTrackRecords(businessTrackRecords);


        return businessSelectByIdVO;
    }

    @Override
    public void assignBusiness(Integer businessId, Integer userId) {
        Business business = this.baseMapper.selectById(businessId);
        business.setUserId(userId);
        business.setUpdateTime(LocalDateTime.now());
        business.setStatus(BusinessStatusConstants.WAIT_FOLLOW_UP);
        this.baseMapper.updateById(business);
    }

    @Override
    public void addBusiness(BusinessAddDTO dto) {
        boolean hasNull = BeanUtil.hasNullField(dto, "gender", "age", "wechat", "qq", "subject", "courseId", "degree", "jobStatus", "channel", "remark");
        if (hasNull) {
            CommonException.throwCommonException(ParamEnum.PARAM_ERROR);
        }
        Business business = BeanUtil.copyProperties(dto, Business.class);
        business.setCreateTime(LocalDateTime.now());
        business.setUpdateTime(LocalDateTime.now());
        this.baseMapper.insert(business);
    }

    @Override
    public PageResult<BusinessListVO> selectListByPage(BusinessListDTO dto) {
        Page<BusinessListVO> page = new Page<>(dto.getPage(), dto.getPageSize());
        Page<BusinessListVO> pageResult = businessMapper.selectListByPage(page, dto);
        return PageResult.<BusinessListVO>builder()
                .total(pageResult.getTotal())
                .rows(pageResult.getResult())
                .build();
    }
}
