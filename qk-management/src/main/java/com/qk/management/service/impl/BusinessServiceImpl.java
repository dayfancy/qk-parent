package com.qk.management.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.qk.common.PageResult;
import com.qk.common.constant.BusinessStatusConstants;
import com.qk.common.enums.ParamEnum;
import com.qk.common.exception.CommonException;
import com.qk.dto.business.BusinessAddDTO;
import com.qk.dto.business.BusinessListDTO;
import com.qk.entity.Business;
import com.qk.entity.BusinessTrackRecord;
import com.qk.entity.User;
import com.qk.management.mapper.BusinessMapper;
import com.qk.management.mapper.BusinessTrackRecordMapper;
import com.qk.management.mapper.UserMapper;
import com.qk.management.service.BusinessService;
import com.qk.vo.business.BusinessListVO;
import com.qk.vo.business.BusinessSelectByIdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public BusinessSelectByIdVO selectBusinessById(Integer id) {
        //1.查询商机表  返回商机对象
        Business business = this.baseMapper.selectById(id);
        //2.查询商机跟进记录表 返回商机跟进记录对象
        List<BusinessTrackRecord> businessTrackRecords = businessTrackRecordMapper.selectList(Wrappers.lambdaQuery(BusinessTrackRecord.class)
                .eq(BusinessTrackRecord::getBusinessId, id))
                .stream()
                .map(item ->{
                   User user =  userMapper.selectById(item.getUserId());
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
