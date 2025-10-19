package com.qk.dto.business;

import com.qk.entity.Business;
import lombok.*;

/**
 * @Author: RightSquare
 * @Date: 2025/10/19 9:19
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessPoolPageDTO extends Business {
    private Integer page = 1;
    private Integer pageSize = 10;
    private Integer businessId;
}
