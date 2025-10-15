package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.common.constant.ClueBusinessStatusConstants;
import com.qk.common.constant.ClueStatusConstants;
import com.qk.common.constant.ClueTrackRecordTypes;
import com.qk.common.enums.ParamEnum;
import com.qk.common.exception.CommonException;
import com.qk.common.util.CurrentUserContextHolders;
import com.qk.dto.clue.ClueDTO;
import com.qk.dto.clue.ClueListDTO;
import com.qk.dto.clue.UpdateClueInfoDTO;
import com.qk.entity.Business;
import com.qk.entity.Clue;
import com.qk.entity.ClueTrackRecord;
import com.qk.entity.User;
import com.qk.entity.domain.clue.ClueDO;
import com.qk.management.mapper.BusinessMapper;
import com.qk.management.mapper.ClueMapper;
import com.qk.management.mapper.ClueTrackRecordMapper;
import com.qk.management.mapper.UserMapper;
import com.qk.management.service.ClueService;
import com.qk.vo.clue.ClueTrackRecordVO;
import com.qk.vo.clue.ClueVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private BusinessMapper businessMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void toBusiness(Integer id) {
        //1.根据线索的Id查询线索
        Clue clue = clueMapper.selectById(id);
        //2.1修改线索的状态 转商机
        clue.setStatus(ClueStatusConstants.CONVERT_TO_BUSINESS);
        //2.2修改线索的更新时间
        clue.setUpdateTime(LocalDateTime.now());
        //3.把线索的数据更新
        clueMapper.updateById(clue);
        //4.把clue中同名的属性拷贝到business中
        Business business = BeanUtil.copyProperties(clue, Business.class);
        business.setId(null);
        business.setStatus(ClueBusinessStatusConstants.WAIT_ALLOCATION);
        business.setClueId(id);
        business.setNextTime(null);
        business.setCreateTime(LocalDateTime.now());
        business.setUpdateTime(LocalDateTime.now());
        //5.组装数据 插入business表
        businessMapper.insert( business);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateClueInfoById(UpdateClueInfoDTO dto) {
        Clue clue = BeanUtil.copyProperties(dto, Clue.class);
        clue.setUpdateTime(LocalDateTime.now());
        //线索跟进状态 如果是第一次被跟进状态 status要改成跟进中
        if(clue.getStatus() == ClueStatusConstants.WAIT_FOLLOW_UP){
            clue.setStatus(ClueStatusConstants.FOLLOW_UP);
        }
        //更新clue表的数据
        this.updateById(clue);
        //更新clue_track_record表数据
        ClueTrackRecord clueTrackRecord = BeanUtil.copyProperties(dto, ClueTrackRecord.class);
        clueTrackRecord.setCreateTime(LocalDateTime.now());
        clueTrackRecord.setId(null);
        clueTrackRecord.setClueId(clue.getId());
        clueTrackRecord.setType(ClueTrackRecordTypes.NORMAL);
        //设置跟进用户id的当前用户 此时当前用户的id还在拦截器中
        clueTrackRecord.setUserId(CurrentUserContextHolders.get());
        this.clueTrackRecordMapper.insert(clueTrackRecord);
    }

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
    public ClueVO selectClueInfoById(Integer Id) {
        //clueId -> table clue -> clueVO
        Clue clue = this.baseMapper.selectById(Id);
        //according to ID select username
        Integer userId = clue.getUserId();
        User user = userMapper.selectById(userId);
        List<ClueTrackRecord> clueTrackRecords = clueTrackRecordMapper.selectList(Wrappers.lambdaQuery(ClueTrackRecord.class)
                .in(ClueTrackRecord::getClueId, Id));
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
