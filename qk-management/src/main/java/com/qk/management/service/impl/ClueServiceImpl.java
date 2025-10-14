package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.common.constant.ClueStatusConstants;
import com.qk.common.enums.ParamEnum;
import com.qk.common.exception.CommonException;
import com.qk.dto.clue.ClueDTO;
import com.qk.dto.clue.ClueListDTO;
import com.qk.entity.Clue;
import com.qk.entity.ClueTrackRecord;
import com.qk.entity.User;
import com.qk.entity.domain.clue.ClueDO;
import com.qk.management.mapper.ClueMapper;
import com.qk.management.mapper.ClueTrackRecordMapper;
import com.qk.management.mapper.UserMapper;
import com.qk.management.service.ClueService;
import com.qk.vo.clue.ClueTrackRecordVO;
import com.qk.vo.clue.ClueVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/13 20:58
 * @Description:
 */
@Slf4j
@Service
@SuppressWarnings("all")
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue> implements ClueService {
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ClueTrackRecordMapper clueTrackRecordMapper;

    @Override
    public PageResult<ClueDO> selectByPage(ClueListDTO dto) {
        PageHelper.startPage(dto.getPage(),dto.getPageSize());
        List<ClueDO> list = clueMapper.selectByPage(dto);
        Page<ClueDO> listPage = (Page<ClueDO>)list;
        return PageResult.<ClueDO>builder()
                .total(listPage.getTotal())
                .rows(listPage.getResult())
                .build();
    }

    @Override
    public ClueVO selectClueInfoById(Integer clueId) {
        //clueId -> table clue -> clueVO
        Clue clue = this.baseMapper.selectById(clueId);
        //according to ID select username
        Integer userId = clue.getUserId();
        User user = userMapper.selectById(userId);
        List<ClueTrackRecord> clueTrackRecords = clueTrackRecordMapper.selectList(Wrappers.lambdaQuery(ClueTrackRecord.class)
                .in(ClueTrackRecord::getClueId, clueId));
        //组装数据
        ClueVO clueVO = BeanUtil.copyProperties(clue, ClueVO.class);
        List<ClueTrackRecordVO> list = clueTrackRecords.stream().map(item -> {
            ClueTrackRecordVO vo = BeanUtil.copyProperties(item, ClueTrackRecordVO.class);
            vo.setAssignName(user.getName());
            return vo;
        }).toList();
        clueVO.setTrackRecords(list);
        return clueVO;
    }

    @Override
    public void add(ClueDTO dto) {
        //1.Param Checking
        if(ObjectUtil.isEmpty(dto.getPhone())||ObjectUtil.isEmpty(dto.getChannel())){
            CommonException.throwCommonException(ParamEnum.PARAM_ERROR);
        }
        //DTO to bean
        Clue clue = BeanUtil.copyProperties(dto, Clue.class);
        clue.setCreateTime(LocalDateTime.now());
        clue.setUpdateTime(LocalDateTime.now());
        clue.setStatus(ClueStatusConstants.WAIT_ALLOCATION);
        clueMapper.insert(clue);
        //this.save(clue);

    }


}
