package com.qk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 线索跟进记录实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClueTrackRecord {
    // 跟进记录id, 主键
    private Integer id;
    // 线索id，关联线索id
    private Integer clueId;
    // 跟进人id，关联用户id
    private Integer userId;
    // 意向学科，1:ai智能应用开发(java), 2:ai大模型开发(python)，3:ai鸿蒙开发，4:ai大数据，5:ai嵌入式，6:ai测试，7:ai运维
    private Integer subject;
    // 意向等级, 1:近期学习、2:打算学习(考虑中)、3:进行了解、4:打酱油
    private Integer level;
    // 跟进记录
    private String record;
    // 下次跟进时间
    private LocalDateTime nextTime;
    // 跟进类型, 1:正常跟进、0:伪线索
    private Integer type;
    // 伪线索原因, 1:空号、2:停机、3:竞品、4:无法联系、5:其他
    private Integer falseReason;
    // 创建时间
    private LocalDateTime createTime;
}