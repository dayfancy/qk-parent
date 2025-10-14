package com.qk.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.dto.clue.ClueDTO;
import com.qk.dto.clue.ClueListDTO;
import com.qk.entity.Clue;
import com.qk.entity.domain.clue.ClueDO;
import com.qk.vo.clue.ClueVO;

public interface ClueService extends IService<Clue> {
    ClueVO selectClueInfoById(Integer Id);

    void add(ClueDTO dto);

    PageResult<ClueDO> selectByPage(ClueListDTO dto);
}
