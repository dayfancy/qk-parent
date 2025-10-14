package com.qk.dto.clue;


import com.qk.dto.PageDTO;
import lombok.*;

/**
 * @Author: RightSquare
 * @Date: 2025/10/14 11:04
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClueListDTO extends PageDTO {
    // 线索id
    private Integer clueId;
    // 手机号
    private String  phone;
    // 状态
    private Integer status;
    // 渠道来源，1:线上活动, 2:推广介绍
    private Integer channel;
    // 归属人
    private String assignName;

}
