package com.qk.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 分页结果封装类
 * 用于封装分页查询的结果，包含总记录数和当前页的数据列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResult<T> {
    /**
     * 总记录数
     */
    private long total;

    /**
     * 当前页的数据列表
     */
    private List<T> rows;

}