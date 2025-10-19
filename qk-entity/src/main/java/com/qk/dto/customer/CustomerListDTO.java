package com.qk.dto.customer;

import com.qk.dto.PageDTO;
import lombok.*;

/**
 * @Author: RightSquare
 * @Date: 2025/10/19 11:07
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerListDTO extends PageDTO {
    private String phone; // 手机号
    private String name; // 客户姓名
    private Integer channel; // 渠道来源，1:线上活动, 2:推广介绍
    private Integer subject; // 意向学科，1:AI智能应用开发(java), 2:AI大模型开发(python)，3:AI鸿蒙开发，4:AI大数据，5:AI嵌入式，6:AI测试，7:AI运维
}
