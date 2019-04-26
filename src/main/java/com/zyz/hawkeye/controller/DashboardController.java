package com.zyz.hawkeye.controller;

import com.zyz.hawkeye.http.DashboardVO;
import com.zyz.hawkeye.http.HawkeyeResponse;
import com.zyz.hawkeye.http.metric.ChartVO;
import com.zyz.hawkeye.service.ChartService;
import com.zyz.hawkeye.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hawkeye/api/dashboards")
public class DashboardController {

    @Autowired
    private ChartService chartService;

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/{dashboardId}/charts")
    public HawkeyeResponse<List<ChartVO>> listCharts(@PathVariable("dashboardId") Integer dashboardId) {
        return HawkeyeResponse.success(chartService.listByDashboardId(dashboardId));
    }

    @GetMapping("/{dashboardId}")
    public HawkeyeResponse<DashboardVO> get(@PathVariable("dashboardId") Integer dashboardId) {
        return HawkeyeResponse.success(dashboardService.get(dashboardId));
    }

    @PutMapping("/{dashboardId}/layout")
    public HawkeyeResponse<Integer> updateLayout(@PathVariable("dashboardId") Integer dashboardId, @RequestBody String content) {
        return HawkeyeResponse.success(dashboardService.updateLayout(dashboardId, content));
    }

    @DeleteMapping("/{dashboardId}/charts/{chartId}")
    public HawkeyeResponse<Integer> removeChart(@PathVariable("dashboardId") Integer dashboardId, @PathVariable("chartId") Integer chartId) {
        return HawkeyeResponse.success(chartService.delete(dashboardId, chartId));
    }




}
