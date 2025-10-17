package com.qk.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.dto.business.BusinessAddDTO;
import com.qk.dto.business.BusinessListDTO;
import com.qk.entity.Business;
import com.qk.vo.business.BusinessListVO;
import com.qk.vo.business.BusinessSelectByIdVO;

public interface BusinessService extends IService<Business> {
    BusinessSelectByIdVO selectBusinessById(Integer id);

    void assignBusiness(Integer businessId, Integer userId);

    void addBusiness(BusinessAddDTO dto);

    PageResult<BusinessListVO> selectListByPage(BusinessListDTO dto);

}
