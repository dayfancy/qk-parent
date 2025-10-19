package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.operatelog.OperateLogSelectDTO;
import com.qk.management.service.OperateService;
import com.qk.vo.operatelog.OperateLogSelectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: RightSquare
 * @Date: 2025/10/17 20:01
 * @Description:
 */
@RestController
@RequestMapping("/logs")
@SuppressWarnings("all")
public class OperateController {
    @Autowired
    private OperateService operateService;

    @GetMapping
    public Result selectLogByPage(OperateLogSelectDTO dto){
       PageResult<OperateLogSelectVO> pageResult = operateService.selectLogByPage(dto);
       return Result.success(pageResult);
    }
}
