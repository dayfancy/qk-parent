package com.qk.dto.clue;

import com.qk.dto.PageDTO;
import lombok.*;

/**
 * @Author: RightSquare
 * @Date: 2025/10/16 17:50
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CluePoorDTO extends PageDTO{
    private Integer clueId;
    private String phone;
    private Integer channel;
}
