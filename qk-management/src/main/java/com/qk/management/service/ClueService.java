package com.qk.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.dto.clue.ClueDTO;
import com.qk.entity.Clue;

public interface ClueService extends IService<Clue> {
    void add(ClueDTO dto);
}
