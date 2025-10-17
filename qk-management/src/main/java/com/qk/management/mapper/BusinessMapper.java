package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.domain.business.BusinessCountDO;
import com.qk.entity.Business;

public interface BusinessMapper extends BaseMapper<Business> {
    BusinessCountDO selectCountBusiness();
}
