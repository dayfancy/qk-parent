package com.qk.vo.portal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: RightSquare
 * @Date: 2025/10/15 21:23
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortalVO {
    private Integer clueTotal; // 总线索数
    private Integer clueWaitAllot; // 待分配线索数量
    private Integer clueWaitFollow; // 待跟进线索数量
    private Integer clueFollowing; // 跟进中线索数量
    private Integer clueFalse; // 伪线索数量
    private Integer clueConvertBusiness; // 转商机线索数量

    private Integer businessTotal; // 总商机数
    private Integer businessWaitAllot; // 待分配商机数量
    private Integer businessWaitFollow; // 待跟进商机数量
    private Integer businessFollowing; // 跟进中商机数量
    private Integer businessFalse; // 回收商机数量
    private Integer businessConvertCustomer; // 转客户商机数量

}
