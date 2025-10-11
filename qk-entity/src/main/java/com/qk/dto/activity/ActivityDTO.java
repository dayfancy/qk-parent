package com.qk.dto.activity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: RightSquare
 * @Date: 2025/10/11 19:43
 * @Description: Activity Date Transfer Object    /to client side/
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {
    private Integer channel; //渠道来源，1:线上活动, 2:推广介绍
    private Integer type; //活动类型，1:课程折扣, 2:代金券
    private Integer status;//状态(1: 未开始, 2: 进行中, 3: 已结束)
    private Integer page = 1; // 页码
    private Integer pageSize = 10; // 每页条数

}
