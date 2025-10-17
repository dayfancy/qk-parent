package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.common.constant.ClueStatusConstants;
import com.qk.dto.clue.ClueDTO;
import com.qk.dto.clue.ClueListDTO;
import com.qk.dto.clue.UpdateClueInfoDTO;
import com.qk.entity.Clue;
import com.qk.entity.domain.clue.ClueDO;
import com.qk.management.aop.annotation.LogAnno;
import com.qk.management.service.ClueService;
import com.qk.vo.clue.ClueVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @LogAnno
    @PutMapping("/toBusiness/{id}")
    public Result toBusiness(@PathVariable Integer id){
        clueService.toBusiness(id);
        return Result.success();
    }

    @LogAnno
    @PutMapping
    public Result updateClueInfoById(@RequestBody UpdateClueInfoDTO dto) {
        clueService.updateClueInfoById(dto);
        return Result.success();
    }


    @GetMapping("/{id}")
    public Result selectClueInfoById(@PathVariable Integer id){
         ClueVO clueVO = clueService.selectClueInfoById(id);
        return Result.success(clueVO);
    }
    @LogAnno
    @PutMapping("/assign/{clueId}/{userId}")
    public Result updateById(@PathVariable Integer clueId, @PathVariable Integer userId){
        clueService.updateById(Clue.builder()
                        .id(clueId)
                        .userId(userId)
                        .updateTime(LocalDateTime.now())
                        .status(ClueStatusConstants.WAIT_FOLLOW_UP)
                .build());
        return Result.success();
    }


    @GetMapping
    public Result page(ClueListDTO dto) {
        PageResult<ClueDO> pageResult = clueService.selectByPage(dto);
        return Result.success(pageResult);
    }

    @LogAnno
    @PostMapping
    public Result add(@RequestBody ClueDTO dto) {
        clueService.add(dto);
        return Result.success();
    }
}
