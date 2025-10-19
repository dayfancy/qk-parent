package com.qk.dto.business;

import com.qk.dto.PageDTO;
import lombok.*;

/**
 * @Author: RightSquare
 * @Date: 2025/10/16 20:16
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BusinessListDTO extends PageDTO {
    private Integer businessId; // 商机id, 主键
    private String name; // 客户姓名
    private String phone; // 手机号
    private Integer status; // 商机状态，1:待分配, 2:待跟进, 3:跟进中, 4:回收, 5:转客户
    private String assignName;//归属人姓名

}
