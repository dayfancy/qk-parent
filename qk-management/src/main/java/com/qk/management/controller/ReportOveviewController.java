package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.management.service.ReportOveviewService;
import com.qk.vo.portal.PortalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: RightSquare
 * @Date: 2025/10/15 21:19
 * @Description:
 */
@RestController
@RequestMapping
@SuppressWarnings("all")
public class ReportOveviewController {

    @Autowired
    private ReportOveviewService reportOveviewService;

    @GetMapping("/report/overview")
    public Result reportOverview() {
       PortalVO vo =  reportOveviewService.reportOverviewWithCache();
        return Result.success(vo);
    }
}
