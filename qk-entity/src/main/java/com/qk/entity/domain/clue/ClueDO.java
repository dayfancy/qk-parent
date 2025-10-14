package com.qk.entity.domain.clue;

import com.qk.entity.Clue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: RightSquare
 * @Date: 2025/10/14 11:12
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClueDO extends Clue {
    private String assignName;
}
