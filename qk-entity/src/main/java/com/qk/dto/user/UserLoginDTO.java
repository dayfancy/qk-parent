package com.qk.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: RightSquare
 * @Date: 2025/10/10 16:40
 * @Description: request DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    private String username;
    private String password;
}
