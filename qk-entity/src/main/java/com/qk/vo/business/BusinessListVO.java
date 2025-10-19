package com.qk.vo.business;

import com.qk.entity.Business;
import lombok.*;

/**
 * @Author: RightSquare
 * @Date: 2025/10/16 20:18
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BusinessListVO extends Business {
    private String assignName;
}
