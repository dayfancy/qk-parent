package com.qk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: RightSquare
 * @Date: 2025/10/11 17:25
 * @Description: Activity Entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    private Integer id; //活动ID，主键
    private Integer channel; //渠道来源，1:线上活动, 2:推广介绍
    private String name; //活动名称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime; //开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime; //结束时间
    private String description; //活动简介
    private Integer type; //活动类型，1:课程折扣, 2:代金券
    private Double discount; //课程折扣
    private Integer voucher; //代金券金额（元）
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime; //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; //修改时间

}
