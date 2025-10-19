package com.qk.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.dto.clue.*;
import com.qk.entity.Clue;
import com.qk.entity.domain.clue.ClueDO;
import com.qk.vo.clue.CluePoolVO;
import com.qk.vo.clue.ClueVO;

public interface ClueService extends IService<Clue> {




    PageResult<CluePoolVO> selectCluePool(CluePoorDTO dto);

    void updateClueAndRecordById(Integer id, ClueFalseDTO dto);

    void toBusiness(Integer id);

    void updateClueInfoById(UpdateClueInfoDTO dto);

    ClueVO selectClueInfoById(Integer Id);

    void add(ClueDTO dto);

    PageResult<ClueDO> selectByPage(ClueListDTO dto);
}
