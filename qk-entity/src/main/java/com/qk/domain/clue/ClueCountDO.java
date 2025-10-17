package com.qk.domain.clue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: RightSquare
 * @Date: 2025/10/15 21:32
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClueCountDO {
    private Integer clueTotal; // 总线索数
    private Integer clueWaitAllot; // 待分配线索数量
    private Integer clueWaitFollow; // 待跟进线索数量
    private Integer clueFollowing; // 跟进中线索数量
    private Integer clueFalse; // 伪线索数量
    private Integer clueConvertBusiness; // 转商机线索数量
}
