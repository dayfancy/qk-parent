package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.domain.clue.ClueCountDO;
import com.qk.dto.clue.ClueListDTO;
import com.qk.entity.Clue;
import com.qk.entity.domain.clue.ClueDO;

import java.util.List;

public interface ClueMapper extends BaseMapper<Clue> {
    List<ClueDO> selectByPage(ClueListDTO dto);

    ClueCountDO selectCountClue();
}
