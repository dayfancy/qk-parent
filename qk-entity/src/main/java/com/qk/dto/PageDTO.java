package com.qk.dto;

import lombok.*;

/**
 * @Author: RightSquare
 * @Date: 2025/10/14 11:10
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO {
    private Integer page;
    private Integer pageSize;
}
