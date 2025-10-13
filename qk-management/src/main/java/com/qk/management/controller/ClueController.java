package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.dto.clue.ClueDTO;
import com.qk.management.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public Result add(@RequestBody ClueDTO dto) {
        clueService.add(dto);
        return Result.success();
    }
}
