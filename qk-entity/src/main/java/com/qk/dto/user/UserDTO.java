package com.qk.dto.user;

import lombok.Data;

/**
 * @Author: RightSquare
 * @Date: 2025/10/7 20:38
 * @Description: User Data Transfer Object
 */
@Data
public class UserDTO {

    private String name; // 姓名
    private Integer status; // 状态
    private String phone; // 手机
    private Integer deptId; // 部门
    //TODO 后期处理
    private Integer page = 1; // 页码
    private Integer pageSize = 10; // 每页条数
}
