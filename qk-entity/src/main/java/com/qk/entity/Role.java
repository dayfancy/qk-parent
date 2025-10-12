package com.qk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: RightSquare
 * @Date: 2025/10/9 10:25
 * @Description: Role Entity
 */
@Data
public class Role {
    private Integer id; // 角色id，主键
    private String name; // 角色名称
    private String label; // 角色标识
    private String remark; // 备注
    private LocalDateTime createTime; // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; // 修改时间
}
