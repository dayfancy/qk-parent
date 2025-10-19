package com.qk.dto.clue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: RightSquare
 * @Date: 2025/10/13 21:17
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClueDTO {

    // 手机号
    private String phone;
    // 渠道来源，1:线上活动, 2:推广介绍
    private Integer channel;
    // 活动信息，关联活动的id
    private Integer activityId;
    // 客户姓名
    private String name;
    // 性别，1:男, 2:女
    private Integer gender;
    // 年龄
    private Integer age;
    // 微信号
    private String wechat;
    // qq号
    private String qq;
}
