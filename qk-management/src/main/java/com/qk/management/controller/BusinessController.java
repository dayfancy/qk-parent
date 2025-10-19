package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.business.BusinessAddDTO;
import com.qk.dto.business.BusinessFollowDTO;
import com.qk.dto.business.BusinessListDTO;
import com.qk.dto.business.BusinessPoolPageDTO;
import com.qk.entity.Business;
import com.qk.management.service.BusinessService;
import com.qk.vo.business.BusinessListVO;
import com.qk.vo.business.BusinessSelectByIdVO;
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
    private BusinessService businessService;

    @GetMapping("/pool")
    public Result selectPoolPage(BusinessPoolPageDTO dto){
        PageResult<Business> pageResult = businessService.selectPoolPage(dto);
        return Result.success(pageResult);
    }


    @PutMapping("back/{id}")
    public Result backToSeaPool(@PathVariable Integer id) {
        businessService.back(id);
        return Result.success();
    }

    //转客户
    @PostMapping("/toCustomer/{id}")
    public Result toCustomer(@PathVariable Integer id) {
        businessService.toCustomer(id);
        return Result.success();
    }

    @PutMapping
    public Result followBusiness(@RequestBody BusinessFollowDTO dto) {
        businessService.followBusiness(dto);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectBusinessById(@PathVariable Integer id) {
        BusinessSelectByIdVO vo = businessService.selectBusinessById(id);
        return Result.success(vo);
    }


    @PutMapping("/assign/{businessId}/{userId}")
    public Result assignBusiness(@PathVariable Integer businessId, @PathVariable Integer userId) {
        businessService.assignBusiness(businessId, userId);
        return Result.success();
    }


    @PostMapping
    public Result add(@RequestBody BusinessAddDTO dto) {
        businessService.addBusiness(dto);
        return Result.success();
    }

    @GetMapping
    public Result selectListByPage(BusinessListDTO dto) {
        PageResult<BusinessListVO> pageResult = businessService.selectListByPage(dto);
        return Result.success(pageResult);
    }
}
