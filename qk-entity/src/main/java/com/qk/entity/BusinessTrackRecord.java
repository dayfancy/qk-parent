package com.qk.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 商机跟进记录实体类
 */
@Data
@TableName("business_track_record")
public class BusinessTrackRecord {
    private Integer id; // 跟进记录id, 主键
    private Integer businessId; // 商机id，关联商机id
    private Integer userId; // 跟进人id，关联用户id
    private Integer trackStatus; // 跟进状态, 1:接通, 2:拒绝, 3:无人接听
    private String keyItems; // 沟通重点
    private LocalDateTime nextTime; // 下次跟进时间
    private String record; // 沟通纪要
    private LocalDateTime createTime; // 创建时间

    //扩展 - 跟进人姓名
    @TableField(exist = false)
    private String assignName;
}