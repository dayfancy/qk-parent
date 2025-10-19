package com.qk.entity.domain.clue;

import com.qk.entity.Clue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: RightSquare
 * @Date: 2025/10/14 11:12
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClueDO extends Clue {
    private String assignName;
}
