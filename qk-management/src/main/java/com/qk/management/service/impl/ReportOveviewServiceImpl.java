package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.qk.domain.business.BusinessCountDO;
import com.qk.domain.clue.ClueCountDO;
import com.qk.management.mapper.BusinessMapper;
import com.qk.management.mapper.ClueMapper;
import com.qk.management.service.ReportOveviewService;
import com.qk.vo.portal.PortalVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: RightSquare
 * @Date: 2025/10/15 21:26
 * @Description:
 * 1.选用Redis的数据类型
 * 2.从Redis查询缓存 如果存在缓存命中 则返回缓存数据
 * 3.不存在缓存 说明是第一次查询 则查询数据库
 * 3.1并从数据库中查询的数据往Redis中缓存
 * 3.2返回数据
 */
@Slf4j
@Service
@SuppressWarnings("all")
public class ReportOveviewServiceImpl implements ReportOveviewService {
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private StringRedisTemplate redisClient;
    //定义 常量缓存
    private static final String CACHE_PORTAL_KEY_PREFIX = "qk:portal:";


    @Override
    public PortalVO reportOverviewWithCache() {
        //1.定义缓存key
        String redisKey = CACHE_PORTAL_KEY_PREFIX + "reportOverview";
        //2.1从redis中查询缓存 缓存命中
        String jsonStr = null;
        try {
            jsonStr = redisClient.opsForValue().get(redisKey);
            if (ObjectUtil.isNotEmpty(jsonStr)){
               return JSONUtil.toBean(jsonStr, PortalVO.class);
            }
        } catch (Exception e) {
            log.info("从redis中查询缓存失败", e);
        }


        //2.2 缓存没命中 则查询数据库
                    //1.从clue表中查出数据返回给ClueCountDO
        ClueCountDO clueCountDO = clueMapper.selectCountClue();
                    //2.从business表中查出数据返回给BusinessCountDO
        BusinessCountDO businessCountDO = businessMapper.selectCountBusiness();
                //3.数据封装到VO
        PortalVO vo = BeanUtil.copyProperties(clueCountDO, PortalVO.class);
        BeanUtil.copyProperties(businessCountDO, vo);


        try {
            //从数据库查询的数据添加到Redis中
            String json = JSONUtil.toJsonStr(vo);
            redisClient.opsForValue().set(redisKey, json);
        } catch (Exception e) {
            log.info("从数据库查询的数据添加到Redis中失败", e);
        }

        return vo;
    }

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
