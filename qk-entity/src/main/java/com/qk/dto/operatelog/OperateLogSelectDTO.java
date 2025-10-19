package com.qk.dto.operatelog;

import com.qk.dto.PageDTO;
import lombok.*;

/**
 * @Author: RightSquare
 * @Date: 2025/10/19 12:11
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OperateLogSelectDTO extends PageDTO {
    private String operateUserName;
}
