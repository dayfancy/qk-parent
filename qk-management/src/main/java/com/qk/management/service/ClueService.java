package com.qk.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.dto.clue.ClueDTO;
import com.qk.dto.clue.ClueListDTO;
import com.qk.entity.Clue;
import com.qk.entity.domain.clue.ClueDO;

public interface ClueService extends IService<Clue> {
    void add(ClueDTO dto);

    PageResult<ClueDO> selectByPage(ClueListDTO dto);
}
