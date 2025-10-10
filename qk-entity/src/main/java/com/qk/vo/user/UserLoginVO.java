package com.qk.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: RightSquare
 * @Date: 2025/10/10 16:42
 * @Description: UserLoginVO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO {
    private Integer id;
    private String username;
    private String name;
    private String image;
    private String roleLabel;
    // 令牌
    private String token;
}
