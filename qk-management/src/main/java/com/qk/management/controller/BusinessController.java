package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.business.BusinessAddDTO;
import com.qk.dto.business.BusinessListDTO;
import com.qk.management.service.BusinessService;
import com.qk.vo.business.BusinessListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: RightSquare
 * @Date: 2025/10/15 11:28
 * @Description:
 */
@RestController
@RequestMapping("/businesses")
@SuppressWarnings("all")
public class BusinessController {
    @Autowired
    private BusinessService BusinessService;

    @PostMapping
    public Result add(@RequestBody BusinessAddDTO dto){
        BusinessService.addBusiness(dto);
        return Result.success();
    }

    @GetMapping
    public Result selectListByPage(BusinessListDTO dto) {
        PageResult<BusinessListVO> pageResult = BusinessService.selectListByPage(dto);
        return Result.success(pageResult);
    }
}
