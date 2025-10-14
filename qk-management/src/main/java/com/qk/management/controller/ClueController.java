package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.clue.ClueDTO;
import com.qk.dto.clue.ClueListDTO;
import com.qk.entity.domain.clue.ClueDO;
import com.qk.management.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: RightSquare
 * @Date: 2025/10/13 21:15
 * @Description:
 */
@RestController
@RequestMapping("/clues")
@SuppressWarnings("all")
public class ClueController {
    @Autowired
    private ClueService clueService;


    @GetMapping
    public Result page(ClueListDTO dto) {
        PageResult<ClueDO> pageResult = clueService.selectByPage(dto);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result add(@RequestBody ClueDTO dto) {
        clueService.add(dto);
        return Result.success();
    }
}
