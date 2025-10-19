package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.domain.business.BusinessCountDO;
import com.github.pagehelper.Page;
import com.qk.dto.business.BusinessListDTO;
import com.qk.entity.Business;
import com.qk.vo.business.BusinessListVO;

public interface BusinessMapper extends BaseMapper<Business> {
    BusinessCountDO selectCountBusiness();

    Page<BusinessListVO> selectListByPage(Page<BusinessListVO> page, BusinessListDTO dto);
}
