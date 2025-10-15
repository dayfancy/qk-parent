package com.qk.domain.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: RightSquare
 * @Date: 2025/10/15 21:31
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessCountDO {

    private Integer businessTotal; // 总商机数
    private Integer businessWaitAllot; // 待分配商机数量
    private Integer businessWaitFollow; // 待跟进商机数量
    private Integer businessFollowing; // 跟进中商机数量
    private Integer businessFalse; // 回收商机数量
    private Integer businessConvertCustomer; // 转客户商机数量
}
