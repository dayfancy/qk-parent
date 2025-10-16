package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.qk.dto.clue.ClueListDTO;
import com.qk.dto.clue.CluePoorDTO;
import com.qk.entity.Clue;
import com.qk.entity.domain.clue.ClueDO;
import com.qk.vo.clue.CluePoolVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClueMapper extends BaseMapper<Clue> {
    List<ClueDO> selectByPage(ClueListDTO dto);


    Page<CluePoolVO> selectCluePool(Page<CluePoolVO> pages, CluePoorDTO dto);
}
