package com.qk.management.service;

import com.qk.vo.portal.PortalVO;
@SuppressWarnings("unused")
public interface ReportOveviewService {

    PortalVO reportOverviewWithCache();

    PortalVO reportOverview();
}
