package com.qk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 部门实体类
 * 对应数据库表 dept
 */
@Data
public class Dept {

    /**
     * 部门id，主键
     */
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 状态：0-停用，1-正常
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}