package com.qk.vo.business;

import com.qk.entity.Business;
import com.qk.entity.BusinessTrackRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/16 21:43
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessSelectByIdVO extends Business {
    private List<BusinessTrackRecord> trackRecords;
}
