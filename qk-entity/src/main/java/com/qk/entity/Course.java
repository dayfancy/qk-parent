package com.qk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: RightSquare
 * @Date: 2025/10/9 14:21
 * @Description: Course Entity
 */
@Data
public class Course {
    private Integer id; //课程id，主键
    private Integer subject; //课程学科，1:AI智能应用开发(Java), 2:AI大模型开发(Python)，3:AI鸿蒙开发，4:AI大数据，5:AI嵌入式，6:AI测试，7:AI运维
    private String name; //课程名称
    private Integer price; //课程价格（元）
    private Integer target; //适用人群, 1:小白学员, 2:中级程序员
    private String description; //课程介绍
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime; //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; //修改时间

}
