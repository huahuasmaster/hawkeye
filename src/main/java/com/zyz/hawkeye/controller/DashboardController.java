package com.zyz.hawkeye.controller;

import com.zyz.hawkeye.enums.metric.DataSourceType;
import com.zyz.hawkeye.http.HawkeyeResponse;
import com.zyz.hawkeye.http.metric.ChartVO;
import com.zyz.hawkeye.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hawkeye/api/dashboards")
public class DashboardController {

    @Autowired
    private ChartService chartService;

    @GetMapping("/{dashboardId}/charts")
    public HawkeyeResponse<List<ChartVO>> listCharts(@PathVariable("dashboardId") Integer dashboardId) {
        return HawkeyeResponse.success(chartService.listByDashboardId(dashboardId));
    }



}
