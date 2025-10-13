package com.qk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Clue {
    // 线索id, 主键
    private Integer id;
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
    // 归属人id，关联用户id
    private Integer userId;
    // 线索状态，1:待分配, 2:跟进中, 3:伪线索, 4:转为商机
    private Integer status;
    // 意向学科，1:ai智能应用开发(java), 2:ai大模型开发(python)，3:ai鸿蒙开发，4:ai大数据，5:ai嵌入式，6:ai测试，7:ai运维
    private Integer subject;
    // 意向等级, 1:近期学习、2:打算学习(考虑中)、3:进行了解、4:打酱油
    private Integer level;
    // 下次跟进时间
    private LocalDateTime nextTime;
    // 创建时间
    private LocalDateTime createTime;
    // 修改时间
    private LocalDateTime updateTime;
}