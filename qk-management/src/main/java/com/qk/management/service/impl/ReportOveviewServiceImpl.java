package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.qk.domain.business.BusinessCountDO;
import com.qk.domain.clue.ClueCountDO;
import com.qk.management.mapper.BusinessMapper;
import com.qk.management.mapper.ClueMapper;
import com.qk.management.service.ReportOveviewService;
import com.qk.vo.portal.PortalVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: RightSquare
 * @Date: 2025/10/15 21:26
 * @Description:
 */
@Slf4j
@Service
@SuppressWarnings("all")
public class ReportOveviewServiceImpl implements ReportOveviewService {
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private BusinessMapper businessMapper;
    @Override
    public PortalVO reportOverview() {
        //1.从clue表中查出数据返回给ClueCountDO
         ClueCountDO clueCountDO = clueMapper.selectCountClue();
        //2.从business表中查出数据返回给BusinessCountDO
        BusinessCountDO businessCountDO = businessMapper.selectCountBusiness();
        //3.数据封装到VO
        PortalVO vo = BeanUtil.copyProperties(clueCountDO, PortalVO.class);
        BeanUtil.copyProperties(businessCountDO, vo);
        return vo;
    }
}
