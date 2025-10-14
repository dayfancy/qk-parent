package com.qk.vo.clue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: RightSquare
 * @Date: 2025/10/14 20:10
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClueTrackRecordVO {
    // 意向学科，1:ai智能应用开发(java), 2:ai大模型开发(python)，3:ai鸿蒙开发，4:ai大数据，5:ai嵌入式，6:ai测试，7:ai运维
    private Integer subject;
    // 意向等级, 1:近期学习、2:打算学习(考虑中)、3:进行了解、4:打酱油
    private Integer level;
    // 跟进记录
    private String record;
    // 下次跟进时间
    private LocalDateTime nextTime;
    // 跟进人姓名
    private String assignName;
}
